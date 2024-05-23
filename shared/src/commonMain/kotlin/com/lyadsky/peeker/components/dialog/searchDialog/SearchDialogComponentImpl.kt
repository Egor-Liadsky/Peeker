package com.lyadsky.peeker.components.dialog.searchDialog

import com.arkivanov.decompose.ComponentContext
import com.lyadsky.peeker.components.BaseComponent
import com.lyadsky.peeker.data.network.services.HomeService
import com.lyadsky.peeker.utils.EmptyType
import com.lyadsky.peeker.utils.LoadingState
import com.lyadsky.peeker.utils.exceptionHandleable
import kotlinx.coroutines.*

class SearchDialogComponentImpl(
    componentContext: ComponentContext,
    private val homeService: HomeService,
    private val searchTextFieldValue: String,
    private val searchTextFieldValueChanged: (String) -> Unit,
    private val clearedSearchTextField: () -> Unit,
    private val onDismissed: () -> Unit,
) : SearchDialogComponent, BaseComponent<SearchDialogState>(componentContext, SearchDialogState()) {

    private var searchJob: Job? = null

    init {
        scope.launch(Dispatchers.IO) {
            getMarkets()
            val isSearchedProduct = homeService.getSearchedProduct()
            viewState = viewState.copy(
                searchedProduct = isSearchedProduct,
                searchTextField = searchTextFieldValue
            )
        }
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

    override fun onSearchClick(value: String) {
        scope.launch(Dispatchers.IO) {
            getProducts(value)
            val isSearchedProduct = homeService.getSearchedProduct()
            if (!isSearchedProduct) {
                homeService.setSearchedProduct(true)
                viewState = viewState.copy(searchedProduct = true)
            }
        }
    }

    override fun onProductRefreshClick() {
        getProducts(viewState.searchTextField)
    }

    override fun onMarketsRefreshClick() {
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

    override fun onDismiss() {
        onDismissed()
    }

    override fun onSearchTextFieldValueChanged(value: String) {
        viewState = viewState.copy(searchTextField = value)
        searchTextFieldValueChanged(value)

        searchJob?.cancel()

        if (viewState.searchTextField.isNotEmpty()) {
            searchJob = scope.launch(Dispatchers.IO) {
                delay(500)
                getProducts(viewState.searchTextField)
            }
        } else {
            viewState = viewState.copy(productsLoadingState = LoadingState.Empty(EmptyType.EmptyTextField))
        }
    }

    override fun onClearedSearchTextField() {
        clearedSearchTextField()
        viewState = viewState.copy(searchTextField = "")
    }

    private fun getProducts(value: String) {
        scope.launch(Dispatchers.IO) {
            exceptionHandleable(
                executionBlock = {
                    viewState = viewState.copy(productsLoadingState = LoadingState.Loading)
                    val products = homeService.searchProducts(value)
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
            val markets = homeService.getMarkets()
            viewState = viewState.copy(
                markets = markets,
                marketsLoadingState = if (markets.isEmpty()) LoadingState.Error("empty") else LoadingState.Success
            )
        }
    }
}