//
//  BottomNavigationView.swift
//  iosApp
//
//  Created by Егор Лядский on 2024-03-26.
//  Copyright © 2024 Turtle Team. All rights reserved.
//

import SwiftUI
import shared

struct BottomNavigationView: View {
    
    let component: BottomNavigationComponent
    
    @StateValue
    private var childStack: ChildStack<AnyObject, BottomNavigationComponentChild>
        
    private var activeChild: BottomNavigationComponentChild { childStack.active.instance }
        
    init(component: BottomNavigationComponent) {
        self.component = component
        _childStack = StateValue(component.childStack)
    }
    
    var body: some View {
        VStack {
            BottomNavigationChildrenView(child: activeChild)
                .frame(maxHeight: .infinity)
                
            HStack(alignment: .bottom, spacing: 16) {
                Button(action: { component.onTabClicked(tab: MainNavTab.home) }) {
                    Label("Counters", systemImage: "123.rectangle")
                        .labelStyle(VerticalLabelStyle())
                        .opacity(activeChild is BottomNavigationComponentChild.HomeChild ? 1 : 0.5)
                }
                    
                Button(action: { component.onTabClicked(tab: MainNavTab.menu) }) {
                    Label("Multi-Pane", systemImage: "list.bullet")
                        .labelStyle(VerticalLabelStyle())
                        .opacity(activeChild is BottomNavigationComponentChild.MenuChild ? 1 : 0.5)
                }
            }
        }
    }
}

struct BottomNavigationChildrenView: View {
    
    let child: BottomNavigationComponentChild
        
    var body: some View {
        
        switch child {
        case let child as BottomNavigationComponentChild.HomeChild: HomeView(component: child.component)
        case let child as BottomNavigationComponentChild.MenuChild: MenuView(component: child.component)
        default: EmptyView()
        }
    }
}
