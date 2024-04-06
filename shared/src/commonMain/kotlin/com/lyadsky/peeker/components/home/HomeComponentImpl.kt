package com.lyadsky.peeker.components.home

import com.arkivanov.decompose.ComponentContext
import com.lyadsky.peeker.components.BaseComponent
import com.lyadsky.peeker.components.home.HomeComponent
import com.lyadsky.peeker.data.network.repository.SearchRepository
import com.lyadsky.peeker.utils.LoadingState
import com.lyadsky.peeker.utils.exceptionHandleable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class HomeComponentImpl(
    componentContext: ComponentContext,
    private val navigateToAboutAppComponent: () -> Unit
) : HomeComponent, BaseComponent<HomeState>(componentContext, HomeState()), KoinComponent {

    private val searchRepository: SearchRepository by inject()

    init {
        getProducts()
    }

    override fun navigateToAboutApp() {
        navigateToAboutAppComponent()
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
}