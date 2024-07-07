package com.lyadsky.peeker.data.network.repository.home

import ru.astrainteractive.klibs.paging.context.PageContext

data class HomePageContext(val page: Int) : PageContext {

    object Factory : PageContext.Factory<HomePageContext> {

        override fun next(pageContext: HomePageContext): HomePageContext {
            return pageContext.copy(page = pageContext.page + 1)
        }
    }
}