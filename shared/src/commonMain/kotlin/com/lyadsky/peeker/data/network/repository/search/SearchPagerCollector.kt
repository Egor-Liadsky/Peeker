package com.lyadsky.peeker.data.network.repository.search

import com.lyadsky.peeker.BuildKonfig
import com.lyadsky.peeker.models.SortingType
import ru.astrainteractive.klibs.paging.DefaultPagingCollector
import ru.astrainteractive.klibs.paging.PagingCollector
import ru.astrainteractive.klibs.paging.data.PagedListDataSource
import ru.astrainteractive.klibs.paging.state.PagingState

class SearchPagerCollector<T>(
    private val pager: PagedListDataSource<T, SearchPageContext>,
    private val initialQuery: String = "",
    private val initialSortingType: SortingType = SortingType.Rating
) : PagingCollector<T, SearchPageContext> by DefaultPagingCollector(

    initialPagingStateFactory = {
        PagingState(
            pageContext = SearchPageContext(
                page = BuildKonfig.PAGING_INITIAL_PAGE,
                query = initialQuery,
                sortingType = initialSortingType
            ),
            items = emptyList(),
            pageSizeAtLeast = BuildKonfig.PAGING_OFFSET,
            isLastPage = false,
            isLoading = false,
            isFailure = false
        )
    },
    pager = pager,
    pageContextFactory = SearchPageContext.Factory
)