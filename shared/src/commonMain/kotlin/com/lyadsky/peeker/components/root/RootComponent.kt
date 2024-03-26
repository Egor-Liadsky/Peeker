package com.lyadsky.peeker.components.root

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.lyadsky.peeker.components.aboutApp.AboutAppComponent
import com.lyadsky.peeker.components.bottomNavigation.BottomNavigationComponent

interface RootComponent{
    
    val childStack: Value<ChildStack<*, Child>>
    
    fun onBackClicked(toIndex: Int)
    
    sealed class Child {
        data class BottomNavigationChild(val component: BottomNavigationComponent) : Child()
        data class AboutAppChild(val component: AboutAppComponent): Child()
    }
}