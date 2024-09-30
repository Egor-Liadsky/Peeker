package com.lyadsky.peeker.components.layout

import com.arkivanov.decompose.ComponentContext
import com.lyadsky.peeker.components.BaseComponent
import com.lyadsky.peeker.data.service.MarketService
import com.lyadsky.peeker.utils.LoadingState
import com.lyadsky.peeker.utils.exceptionHandleable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch

class FilterLayoutComponentImpl(
    componentContext: ComponentContext,
    private val marketService: MarketService,
    private val onApplyClicked: (priceFrom: String, priceTo: String) -> Unit
) : FilterLayoutComponent, BaseComponent<FilterLayoutState>(componentContext, FilterLayoutState()) {

    init {
        getMarkets()
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

    override fun onApplyClick() {
        onApplyClicked(viewState.rangeFromTextField, viewState.rangeToTextField)
    }

    override fun onMarketsRefreshClick() {
       getMarkets()
    }

    private fun getMarkets() {
        scope.launch(Dispatchers.IO) {
            exceptionHandleable(
                executionBlock = {
                    viewState = viewState.copy(marketsLoadingState = LoadingState.Loading)
                    val markets = marketService.getMarkets()
                    viewState = viewState.copy(
                        markets = markets,
                        marketsLoadingState = if (markets.isEmpty()) LoadingState.Error("empty") else LoadingState.Success // TODO понять почему тут Error
                    )
                },
                failureBlock = {
                    viewState =
                        viewState.copy(marketsLoadingState = LoadingState.Error(it.message.toString()))
                }
            )
        }
    }
}