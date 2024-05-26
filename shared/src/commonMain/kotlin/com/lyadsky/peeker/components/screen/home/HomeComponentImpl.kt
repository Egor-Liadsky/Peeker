package com.lyadsky.peeker.components.screen.home

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.childContext
import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.router.slot.SlotNavigation
import com.arkivanov.decompose.router.slot.activate
import com.arkivanov.decompose.router.slot.childSlot
import com.arkivanov.decompose.router.slot.dismiss
import com.arkivanov.decompose.value.Value
import com.lyadsky.peeker.components.BaseComponent
import com.lyadsky.peeker.data.network.services.HomeService
import com.lyadsky.peeker.di.components.createSearchDialogComponent
import com.lyadsky.peeker.utils.ComponentFactory
import com.lyadsky.peeker.utils.LoadingState
import com.lyadsky.peeker.utils.exceptionHandleable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable

class HomeComponentImpl(
    componentContext: ComponentContext,
    componentFactory: ComponentFactory,
    private val homeService: HomeService
) : HomeComponent, BaseComponent<HomeState>(componentContext, HomeState()) {

    private val slotNavigation = SlotNavigation<SlotConfig>()

    init {
        getData()
    }

    override val searchDialogComponent: HomeComponent.SlotChild = //TODO childContext надо заменить
        HomeComponent.SlotChild.SearchDialogChild(
            componentFactory.createSearchDialogComponent(
                componentContext = childContext(key = "SearchDialogComponent"),
                searchTextFieldValue = viewState.searchTextField,
                searchTextFieldValueChanged = { viewState = viewState.copy(searchTextField = it) },
                clearedSearchTextField = { viewState = viewState.copy(searchTextField = "") },
                onDismissed = { slotNavigation.dismiss() },
            )
        )

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
            is SlotConfig.SearchDialog -> searchDialogComponent
        }

    override fun onSearchTextFieldClick() {
        slotNavigation.activate(SlotConfig.SearchDialog(viewState.searchTextField))
    }

    override fun onProductRefreshClick() {
        getData()
    }

    private fun getData() {
        scope.launch(Dispatchers.IO) {
            exceptionHandleable(
                executionBlock = {
                    viewState = viewState.copy(productsLoadingState = LoadingState.Loading)
                    homeService.saveMarkets()
                    val products = homeService.getProducts()
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

    @Serializable
    private sealed interface SlotConfig {

        @Serializable
        data class SearchDialog(val searchTextFieldValue: String) : SlotConfig
    }
}