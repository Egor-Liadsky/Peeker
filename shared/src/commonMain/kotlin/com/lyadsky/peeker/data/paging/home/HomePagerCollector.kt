package com.lyadsky.peeker.data.paging.home

import com.lyadsky.peeker.BuildKonfig
import ru.astrainteractive.klibs.paging.DefaultPagingCollector
import ru.astrainteractive.klibs.paging.PagingCollector
import ru.astrainteractive.klibs.paging.data.PagedListDataSource
import ru.astrainteractive.klibs.paging.state.PagingState

class HomePagerCollector<T>(
    private val pager: PagedListDataSource<T, HomePageContext>
) : PagingCollector<T, HomePageContext> by DefaultPagingCollector(

    initialPagingStateFactory = {
        PagingState(
            pageContext = HomePageContext(BuildKonfig.PAGING_INITIAL_PAGE),
            items = emptyList(),
            pageSizeAtLeast = BuildKonfig.PAGING_OFFSET,
            isLastPage = false,
            isLoading = false,
            isFailure = false
        )
    },
    pager = pager,
    pageContextFactory = HomePageContext.Factory
)