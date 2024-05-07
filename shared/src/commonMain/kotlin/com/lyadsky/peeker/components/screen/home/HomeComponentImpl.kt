package com.lyadsky.peeker.components.screen.home

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.router.slot.SlotNavigation
import com.arkivanov.decompose.router.slot.activate
import com.arkivanov.decompose.router.slot.childSlot
import com.arkivanov.decompose.router.slot.dismiss
import com.arkivanov.decompose.value.Value
import com.lyadsky.peeker.components.BaseComponent
import com.lyadsky.peeker.data.network.services.HomeService
import com.lyadsky.peeker.di.createSearchDialogComponent
import com.lyadsky.peeker.utils.ComponentFactory
import com.lyadsky.peeker.utils.LoadingState
import com.lyadsky.peeker.utils.exceptionHandleable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import org.koin.core.component.KoinComponent

class HomeComponentImpl(
    componentContext: ComponentContext,
    private val componentFactory: ComponentFactory,
    private val homeService: HomeService
) : HomeComponent, BaseComponent<HomeState>(componentContext, HomeState()), KoinComponent {

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
            componentFactory.createSearchDialogComponent(
                componentContext,
                searchTextFieldValueChanged = {
                    viewState = viewState.copy(searchTextField = it)
                },
                clearedSearchTextField = { viewState = viewState.copy(searchTextField = "") },
                onDismissed = { slotNavigation.dismiss() },
            )
        )

    override fun onSearchTextFieldClick() {
        slotNavigation.activate(SlotConfig.SearchDialog)
//        scope.launch(Dispatchers.IO) {
//            viewState = viewState.copy(marketsLoadingState = LoadingState.Loading)
//            val markets = homeService.getMarkets()
//            viewState = viewState.copy(
//                markets = markets,
//                marketsLoadingState = if (markets.isEmpty()) LoadingState.Error("empty") else LoadingState.Success
//            )
//        }
    }

    override fun onProductRefreshClick() {
        getProducts()
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
                    homeService.saveMarkets()
                    val markets = homeService.getMarkets()
                    viewState = viewState.copy(markets = markets)
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