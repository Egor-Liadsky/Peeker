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
import com.lyadsky.peeker.data.paging.home.HomePaging
import com.lyadsky.peeker.data.service.OnboardingService
import com.lyadsky.peeker.di.components.createOnboardingComponent
import com.lyadsky.peeker.di.components.createSearchDialogComponent
import com.lyadsky.peeker.utils.ComponentFactory
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable

class HomeComponentImpl(
    componentContext: ComponentContext,
    private val componentFactory: ComponentFactory,
    private val homePaging: HomePaging,
    private val onboardingService: OnboardingService,
) : HomeComponent, BaseComponent<HomeState>(componentContext, HomeState()) {


    init {
        scope.launch {
            val isOnboardingPassed = onboardingService.getPassedOnboarding()
            if (!isOnboardingPassed) slotNavigation.activate(SlotConfig.OnboardingDialog)
        }
    }

    private val slotNavigation = SlotNavigation<SlotConfig>()

    override val searchDialogComponent: HomeComponent.SlotChild by lazy {
        HomeComponent.SlotChild.SearchDialogChild(
            componentFactory.createSearchDialogComponent(
                componentContext = childContext(key = "SearchDialogComponent"), //TODO childContext надо заменить
                searchTextFieldValue = viewState.searchTextField,
                searchTextFieldValueChanged = { viewState = viewState.copy(searchTextField = it) },
                clearedSearchTextField = { viewState = viewState.copy(searchTextField = "") },
                onDismissed = { slotNavigation.dismiss() },
            )
        )
    }

    private fun onboardingComponent(componentContext: ComponentContext): HomeComponent.SlotChild =
        HomeComponent.SlotChild.OnboardingDialogChild(
            componentFactory.createOnboardingComponent(
                componentContext = componentContext,
                onDismissed = { slotNavigation.dismiss() },
            )
        )

    override val pagingState = homePaging.pagingState

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
            is SlotConfig.OnboardingDialog -> onboardingComponent(componentContext)
        }

    override fun onSearchTextFieldClick() {
        slotNavigation.activate(SlotConfig.SearchDialog(viewState.searchTextField))
    }

    override suspend fun loadNextPage() {
        homePaging.loadNextPage()
    }

    override fun onProductsReloadClick() {
        scope.launch {
            homePaging.reload()
        }
    }

    override fun onRefresh() {
        scope.launch {
            viewState = viewState.copy(isRefreshing = true)
            homePaging.reload()
            viewState = viewState.copy(isRefreshing = false)
        }
    }

    @Serializable
    private sealed interface SlotConfig {

        @Serializable
        data class SearchDialog(val searchTextFieldValue: String) : SlotConfig

        @Serializable
        data object OnboardingDialog : SlotConfig
    }
}