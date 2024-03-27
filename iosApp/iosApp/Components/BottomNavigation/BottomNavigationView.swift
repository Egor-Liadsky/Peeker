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
    
        let isSelectedHome = activeChild is BottomNavigationComponentChild.HomeChild
        let isSelectedChat = activeChild is BottomNavigationComponentChild.ChatChild
        let isSelectedSettings = activeChild is BottomNavigationComponentChild.SettingsChild
        
        VStack {
            
            BottomNavigationChildrenView(child: activeChild)
                .frame(maxHeight: .infinity)
            
            HStack(alignment: .bottom, spacing: 16) {
                Spacer()

                Button(action: { component.onTabClicked(tab: MainNavTab.home) }) {
                    Label("Главная", systemImage:  "house")
                        .labelStyle(VerticalLabelStyle())
                        .opacity(isSelectedHome ? 1 : 0.5)
                        .foregroundStyle(isSelectedHome ? Color.BottomBar.selectedNavigationItem : Color.BottomBar.unselectedNavigationItem)
                }
                .frame(height: 50)
                
                Spacer()
                
                Button(action: { component.onTabClicked(tab: MainNavTab.chat) }) {
                    Label("Чат", systemImage: "message")
                        .labelStyle(VerticalLabelStyle())
                        .opacity(isSelectedChat ? 1 : 0.5)
                        .foregroundStyle(isSelectedChat ? Color.BottomBar.selectedNavigationItem : Color.BottomBar.unselectedNavigationItem)
                }
                .frame(height: 50)
                
                Spacer()
                    
                Button(action: { component.onTabClicked(tab: MainNavTab.settings) }) {
                    Label("Настройки", systemImage: "gearshape")
                        .labelStyle(VerticalLabelStyle())
                        .opacity(isSelectedSettings ? 1 : 0.5)
                        .foregroundStyle(isSelectedSettings ? Color.BottomBar.selectedNavigationItem : Color.BottomBar.unselectedNavigationItem)
                }
                .frame(height: 50)

                Spacer()
            }
            .overlay(Divider().foregroundStyle(Color.BottomBar.stroke).frame(height: 1), alignment: .top)
            .background(Color.BottomBar.background)
        }
    }
}

struct VerticalLabelStyle: LabelStyle {
    func makeBody(configuration: Configuration) -> some View {
        VStack(alignment: .center, spacing: 4) {
            
            configuration.icon
                .frame(width: 24, height: 24)
                
            configuration.title
                .font(.system(size: 10))
        }
    }
}

struct BottomNavigationChildrenView: View {
    
    let child: BottomNavigationComponentChild
        
    var body: some View {
        
        switch child {
        case let child as BottomNavigationComponentChild.HomeChild: HomeView(component: child.component)
        case let child as BottomNavigationComponentChild.ChatChild: ChatView(component: child.component)
        case let child as BottomNavigationComponentChild.SettingsChild: SettingsView(component: child.component)
        default: EmptyView()
        }
    }
}
