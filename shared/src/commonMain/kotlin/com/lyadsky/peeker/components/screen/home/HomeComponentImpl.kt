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
import com.lyadsky.peeker.components.screen.home.HomeComponent
import com.lyadsky.peeker.components.screen.root.RootComponent
import com.lyadsky.peeker.components.screen.root.RootComponentImpl
import com.lyadsky.peeker.data.network.repository.SearchRepository
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
    private val navigateToAboutAppComponent: () -> Unit
) : HomeComponent, BaseComponent<HomeState>(componentContext, HomeState()), KoinComponent {

    private val searchRepository: SearchRepository by inject()

    private val slotNavigation = SlotNavigation<SlotConfig>()

    init {
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

    override fun navigateToAboutApp() {
        navigateToAboutAppComponent()
    }

    override fun onSearchTextFieldClick() {
        slotNavigation.activate(SlotConfig.SearchDialog)
    }

    override fun onSearchTextFieldValueChanged(value: String) {
        viewState = viewState.copy(searchTextField = value)
    }

    override fun onClearedSearchTextField() {
        viewState = viewState.copy(searchTextField = "")
    }

    private fun getProducts() {
        scope.launch(Dispatchers.IO) {
            exceptionHandleable(
                executionBlock = {
                    val products = searchRepository.getProducts("вино")
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
        data object SearchDialog : SlotConfig
    }
}