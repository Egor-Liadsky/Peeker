//
//  RootView.swift
//  iosApp
//
//  Created by Егор Лядский on 2024-03-26.
//  Copyright © 2024 Turtle Team. All rights reserved.
//

import SwiftUI
import shared

struct RootView: View {

    let component: RootComponent
    
    @StateValue
    private var childStack: ChildStack<AnyObject, RootComponentChild>
        
    private var activeChild: RootComponentChild { childStack.active.instance }
        
    init(component: RootComponent) {
        self.component = component
        _childStack = StateValue(component.childStack)
    }
    
    var body: some View {
        VStack {
            RootChildrenView(component: component)
                .frame(maxHeight: .infinity)
        }
    }
}

private struct RootChildrenView: View {
    
    let component: RootComponent
    
    var body: some View {
        StackView(
            stackValue: StateValue(component.childStack),
            getTitle: {
                switch $0 {
                case is RootComponentChild.BottomNavigationChild: return "BottomNavigation"
                case is RootComponentChild.AboutAppChild: return "AboutApp"
                default: return ""
                }
            },
            onBack: component.onBackClicked,
            childContent: {
                switch $0 {
                case let child as RootComponentChild.BottomNavigationChild: BottomNavigationView(component: child.component)
                case let child as RootComponentChild.AboutAppChild: AboutAppView(component: child.component)
                default: EmptyView()
                }
            }
        )
        
    }
}

struct VerticalLabelStyle: LabelStyle {
    func makeBody(configuration: Configuration) -> some View {
        VStack(alignment: .center, spacing: 8) {
            configuration.icon
            configuration.title
        }
    }
}
