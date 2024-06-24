package com.lyadsky.peeker.components.dialog.searchDialog

import androidx.paging.Pager
import androidx.paging.PagingConfig
import app.cash.paging.PagingData
import app.cash.paging.PagingSource
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.childContext
import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.router.slot.SlotNavigation
import com.arkivanov.decompose.router.slot.activate
import com.arkivanov.decompose.router.slot.childSlot
import com.arkivanov.decompose.router.slot.dismiss
import com.arkivanov.decompose.value.Value
import com.lyadsky.peeker.BuildKonfig
import com.lyadsky.peeker.components.BaseComponent
import com.lyadsky.peeker.components.layout.FilterLayoutComponent
import com.lyadsky.peeker.data.network.repository.search.SearchProductRepositoryPagingSource
import com.lyadsky.peeker.data.network.service.SearchService
import com.lyadsky.peeker.di.components.createFilterBottomSheetComponent
import com.lyadsky.peeker.di.components.createFilterLayoutComponent
import com.lyadsky.peeker.di.components.createSortingBottomSheetComponent
import com.lyadsky.peeker.models.Product
import com.lyadsky.peeker.utils.ComponentFactory
import com.lyadsky.peeker.utils.EmptyType
import com.lyadsky.peeker.utils.LoadingState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf

class SearchDialogComponentImpl(
    componentContext: ComponentContext,
    componentFactory: ComponentFactory,
//    private val searchProductRepositoryPagingSource: SearchProductRepositoryPagingSource.Factory,
    private val searchService: SearchService,
    private val searchTextFieldValue: String,
    private val searchTextFieldValueChanged: (String) -> Unit,
    private val clearedSearchTextField: () -> Unit,
    private val onDismissed: () -> Unit,
) : SearchDialogComponent, BaseComponent<SearchDialogState>(componentContext, SearchDialogState()),
    KoinComponent {

    override val products: StateFlow<PagingData<Product>> = Pager(
        initialKey = BuildKonfig.PAGING_INITIAL_PAGE,
        config = PagingConfig(
            pageSize = BuildKonfig.PAGING_OFFSET,
            initialLoadSize = BuildKonfig.PAGING_OFFSET,
        ),
        pagingSourceFactory = {
            val searchProductRepositoryPagingSource: SearchProductRepositoryPagingSource by inject {
                parametersOf(
                    viewState.searchTextField
                )
            }
            return@Pager searchProductRepositoryPagingSource
        }
    ).flow
        .stateIn(scope, SharingStarted.Lazily, PagingData.empty())

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
                onSelectSortingType = {
                    if (viewState.searchTextField.isNotEmpty()) {
//                        getProducts(viewState.searchTextField) //TODO
                    }
                }, // TODO добавить фильтр
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
//                    getProducts(viewState.searchTextField) // TODO
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

    override fun onProductRefreshClick() {
//        getProducts(viewState.searchTextField) //TODO
    }

    override fun onDismissClick() {
        onDismissed()
    }

    override fun onSearchTextFieldValueChanged(value: String) {
        viewState = viewState.copy(searchTextField = value)
        searchTextFieldValueChanged(value)

        if (viewState.searchedProduct) {
            searchJob?.cancel()

            if (viewState.searchTextField.isNotEmpty()) {
                searchJob = scope.launch(Dispatchers.IO) {
                    delay(500)
//                    pagingSourceFactory().invalidate()
                }
            } else {
                viewState =
                    viewState.copy(productsLoadingState = LoadingState.Empty(EmptyType.EmptyTextField))
            }
        }
    }

    override fun onClearedSearchTextField() {
        clearedSearchTextField()
        viewState = viewState.copy(
            searchTextField = "",
            productsLoadingState = LoadingState.Empty(EmptyType.EmptyTextField)
        )
    }

    override fun onSortingButtonClick() {
        slotNavigation.activate(SlotConfig.SortingBottomSheet)
    }

    override fun onFilterButtonClick() {
        slotNavigation.activate(SlotConfig.FilterBottomSheet)
    }

//    private fun getProducts(value: String) {
//        scope.launch(Dispatchers.IO) {
//            exceptionHandleable(
//                executionBlock = {
//                    viewState = viewState.copy(productsLoadingState = LoadingState.Loading)
//                    val products = searchService.s(value)
//                    viewState = viewState.copy(
//                        products = products,
//                        productsLoadingState = LoadingState.Success
//                    )
//                },
//                failureBlock = {
//                    viewState =
//                        viewState.copy(productsLoadingState = LoadingState.Error(it.message.toString()))
//                }
//            )
//        }
//    }

    @Serializable
    private sealed interface SlotConfig {

        @Serializable
        data object SortingBottomSheet : SlotConfig

        @Serializable
        data object FilterBottomSheet : SlotConfig
    }
}