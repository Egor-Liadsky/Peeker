package com.lyadsky.peeker.components.dialog.searchDialog

import com.arkivanov.decompose.ComponentContext
import com.lyadsky.peeker.components.BaseComponent
import com.lyadsky.peeker.data.network.services.HomeService
import com.lyadsky.peeker.data.storage.Storage
import com.lyadsky.peeker.utils.LoadingState
import com.lyadsky.peeker.utils.exceptionHandleable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch

class SearchDialogComponentImpl(
    componentContext: ComponentContext,
    private val homeService: HomeService,
    private val storage: Storage,
    private val searchTextFieldValueChanged: (String) -> Unit,
    private val clearedSearchTextField: () -> Unit,
    private val onDismissed: () -> Unit,
) : SearchDialogComponent, BaseComponent<SearchDialogState>(componentContext, SearchDialogState()) {

    init {

        scope.launch(Dispatchers.IO) {
            getMarkets()
            val isSearchedProduct = storage.getSearchedProduct()
            viewState = viewState.copy(searchedProduct = isSearchedProduct)
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

    override fun onSearchClick() {
        scope.launch(Dispatchers.IO) {
            getProducts("")
            val isSearchedProduct = storage.getSearchedProduct()
            if (!isSearchedProduct) {
                storage.setSearchedProduct(true)
                viewState = viewState.copy(searchedProduct = true)
            }
        }
    }

    override fun onProductRefreshClick() {
        getProducts("")
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
        searchTextFieldValueChanged(value)
    }

    override fun onClearedSearchTextField() {
        clearedSearchTextField()
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