package com.lyadsky.peeker.components.dialog.searchDialog

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.childContext
import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.router.slot.SlotNavigation
import com.arkivanov.decompose.router.slot.activate
import com.arkivanov.decompose.router.slot.childSlot
import com.arkivanov.decompose.router.slot.dismiss
import com.arkivanov.decompose.value.Value
import com.lyadsky.peeker.components.BaseComponent
import com.lyadsky.peeker.components.layout.FilterLayoutComponent
import com.lyadsky.peeker.data.network.service.SearchService
import com.lyadsky.peeker.di.components.createFilterBottomSheetComponent
import com.lyadsky.peeker.di.components.createFilterLayoutComponent
import com.lyadsky.peeker.di.components.createSortingBottomSheetComponent
import com.lyadsky.peeker.utils.ComponentFactory
import com.lyadsky.peeker.utils.EmptyType
import com.lyadsky.peeker.utils.LoadingState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import org.koin.core.component.KoinComponent

class SearchDialogComponentImpl(
    componentContext: ComponentContext,
    componentFactory: ComponentFactory,
    private val searchService: SearchService,
    private val searchTextFieldValue: String,
    private val searchTextFieldValueChanged: (String) -> Unit,
    private val clearedSearchTextField: () -> Unit,
    private val onDismissed: () -> Unit,
) : SearchDialogComponent, BaseComponent<SearchDialogState>(componentContext, SearchDialogState()),
    KoinComponent {

    private var searchJob: Job? = null

    private val slotNavigation = SlotNavigation<SlotConfig>()

    init {
        scope.launch(Dispatchers.IO) {
            val isSearchedProduct = searchService.getSearchedProduct()
            viewState = viewState.copy(
                searchedProduct = isSearchedProduct,
                searchTextField = searchTextFieldValue
            )
        }
    }

    override val products = searchService.pagingState

    override val slotStack: Value<ChildSlot<*, SearchDialogComponent.SlotChild>> =
        childSlot(
            source = slotNavigation,
            serializer = SlotConfig.serializer(),
            childFactory = ::childFactory
        )

    override val sortingBottomSheetComponent: SearchDialogComponent.SlotChild =
        SearchDialogComponent.SlotChild.SortingBottomSheetChild(
            componentFactory.createSortingBottomSheetComponent(
                componentContext = childContext(key = "SortingBottomSheetComponent"),
                selectedSortingType = viewState.selectSortingType,
                onSelectSortingType = {
                    viewState = viewState.copy(selectSortingType = it)
                    if (viewState.searchTextField.isNotEmpty()) {
                        searchService.updateSortingType(it)
                        searchService.reset()
                    }
                },
                onDismiss = { slotNavigation.dismiss() }
            )
        )

    override val filterBottomSheetComponent: SearchDialogComponent.SlotChild =
        SearchDialogComponent.SlotChild.FilterBottomSheetChild(
            componentFactory.createFilterBottomSheetComponent(
                componentContext = childContext(key = "FilterBottomSheetComponent"),
                onDismiss = { slotNavigation.dismiss() }
            )
        )

    override val filterLayoutComponent: FilterLayoutComponent by lazy {
        componentFactory.createFilterLayoutComponent(
            componentContext = childContext(key = "FilterLayoutComponent"),
            onApplyClick = {
                scope.launch(Dispatchers.IO) {
                    searchService.setSearchedProduct(true)
                    viewState = viewState.copy(searchedProduct = true)
                    searchService.reset()
                }
            }
        )
    }

    private fun childFactory(
        config: SlotConfig,
        componentContext: ComponentContext
    ): SearchDialogComponent.SlotChild =
        when (config) {
            SlotConfig.FilterBottomSheet -> filterBottomSheetComponent
            SlotConfig.SortingBottomSheet -> sortingBottomSheetComponent
        }

    override fun onDismissClick() {
        onDismissed()
    }

    override suspend fun loadNextPage() {
        scope.launch(Dispatchers.IO) {
            searchService.loadNextPage()
        }
    }

    override fun onProductsReloadClick() {
        scope.launch(Dispatchers.IO) {
            searchService.reload()
        }
    }

    override fun onSearchTextFieldValueChanged(value: String) {
        viewState = viewState.copy(searchTextField = value)
        searchTextFieldValueChanged(value)

        if (viewState.searchedProduct) { //FIXME убрать и посмотреть использование
            searchJob?.cancel()

            searchJob = scope.launch(Dispatchers.IO) {
                if (viewState.searchTextField.length != 1) delay(500)
                searchService.updateQuery(viewState.searchTextField)
                scope.launch(Dispatchers.IO) {
                    searchService.reload()
                }
            }
        }
    }

    override fun onClearedSearchTextField() {
        if (viewState.searchTextField.isEmpty()) return
        clearedSearchTextField()
        viewState = viewState.copy(
            searchTextField = "",
            productsLoadingState = LoadingState.Empty(EmptyType.EmptyTextField)
        )
        searchService.updateQuery("")
        searchService.reset()
    }

    override fun onSortingButtonClick() {
        slotNavigation.activate(SlotConfig.SortingBottomSheet)
    }

    override fun onFilterButtonClick() {
        slotNavigation.activate(SlotConfig.FilterBottomSheet)
    }

    @Serializable
    private sealed interface SlotConfig {

        @Serializable
        data object SortingBottomSheet : SlotConfig

        @Serializable
        data object FilterBottomSheet : SlotConfig
    }
}