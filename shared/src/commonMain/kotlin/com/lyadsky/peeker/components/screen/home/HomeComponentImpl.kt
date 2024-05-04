package com.lyadsky.peeker.components.screen.home

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.router.slot.SlotNavigation
import com.arkivanov.decompose.router.slot.activate
import com.arkivanov.decompose.router.slot.childSlot
import com.arkivanov.decompose.router.slot.dismiss
import com.arkivanov.decompose.value.Value
import com.lyadsky.peeker.components.BaseComponent
import com.lyadsky.peeker.components.dialog.searchDialog.SearchDialogComponentImpl
import com.lyadsky.peeker.data.network.repository.HomeRepository
import com.lyadsky.peeker.data.network.services.HomeService
import com.lyadsky.peeker.utils.LoadingState
import com.lyadsky.peeker.utils.exceptionHandleable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class HomeComponentImpl(
    componentContext: ComponentContext,
) : HomeComponent, BaseComponent<HomeState>(componentContext, HomeState()), KoinComponent {

    private val homeService: HomeService by inject()

    private val slotNavigation = SlotNavigation<SlotConfig>()

    init {
        getMarkets()
        getProducts()
    }

    override val slotStack: Value<ChildSlot<*, HomeComponent.SlotChild>> =
        childSlot(
            source = slotNavigation,
            serializer = SlotConfig.serializer(),
            key = "SlotNavigation",
            childFactory = ::slotChildFactory
        )

    private fun slotChildFactory(
        config: SlotConfig,
        componentContext: ComponentContext
    ): HomeComponent.SlotChild =
        when (config) {
            SlotConfig.SearchDialog -> searchDialogComponent(componentContext)
        }

    private fun searchDialogComponent(componentContext: ComponentContext): HomeComponent.SlotChild =
        HomeComponent.SlotChild.SearchDialogChild(
            SearchDialogComponentImpl(
                componentContext,
                onDismissed = { slotNavigation.dismiss() }
            )
        )

    override fun onSearchTextFieldClick() {
        slotNavigation.activate(SlotConfig.SearchDialog)
        scope.launch(Dispatchers.IO) {
            viewState = viewState.copy(marketsLoadingState = LoadingState.Loading)
            val markets = homeService.getMarkets()
            viewState = viewState.copy(
                markets = markets,
                marketsLoadingState = if (markets.isEmpty()) LoadingState.Error("empty") else LoadingState.Success
            )
        }
    }

    override fun onSearchTextFieldValueChanged(value: String) {
        viewState = viewState.copy(searchTextField = value)
    }

    override fun onRangeFromTextFieldValueChanged(value: String) {
        viewState = viewState.copy(rangeFromTextField = value)
    }

    override fun onRangeToTextFieldValueChanged(value: String) {
        viewState = viewState.copy(rangeToTextField = value)
    }

    override fun onSearchAllMarketplacesCheckboxValueChanged() {
        viewState =
            viewState.copy(searchAllMarketplacesCheckbox = !viewState.searchAllMarketplacesCheckbox)
    }

    override fun onClearedSearchTextField() {
        viewState = viewState.copy(searchTextField = "")
    }

    override fun onSearchClick() {
        getProducts()
    }

    override fun onProductRefreshClick() {
        getProducts()
    }

    override fun onMarketsRefreshClick() {
        getMarkets()
    }

    private fun getProducts() {
        scope.launch(Dispatchers.IO) {
            exceptionHandleable(
                executionBlock = {
                    viewState = viewState.copy(productsLoadingState = LoadingState.Loading)
                    val products = homeService.searchProducts(viewState.searchTextField)
                    viewState = viewState.copy(
                        products = products,
                        productsLoadingState = LoadingState.Success
                    )
                },
                failureBlock = {
                    viewState =
                        viewState.copy(productsLoadingState = LoadingState.Error(it.message.toString()))
                }
            )
        }
    }

    private fun getMarkets() {
        scope.launch(Dispatchers.IO) {
            exceptionHandleable(
                executionBlock = {
                    viewState = viewState.copy(marketsLoadingState = LoadingState.Loading)
                    homeService.saveMarkets()
                    val markets = homeService.getMarkets()
                    viewState = viewState.copy(
                        markets = markets,
                        marketsLoadingState = LoadingState.Success
                    )
                },
                failureBlock = {
                    viewState =
                        viewState.copy(marketsLoadingState = LoadingState.Error(it.message.toString()))
                }
            )
        }
    }

    @Serializable
    private sealed interface SlotConfig {

        @Serializable
        data object SearchDialog : SlotConfig
    }
}