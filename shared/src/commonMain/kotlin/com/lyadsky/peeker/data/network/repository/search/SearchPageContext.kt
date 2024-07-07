package com.lyadsky.peeker.data.network.repository.search

import com.lyadsky.peeker.models.SortingType
import ru.astrainteractive.klibs.paging.context.PageContext

data class SearchPageContext(
    val page: Int,
    val query: String,
    val sortingType: SortingType
) : PageContext {

    object Factory : PageContext.Factory<SearchPageContext> {
        override fun next(pageContext: SearchPageContext): SearchPageContext {
            return pageContext.copy(page = pageContext.page + 1)
        }
    }
}