//
//  iOSApp.swift
//  iosApp
//
//  Created by Егор Лядский on 2024-03-26.
//  Copyright © 2024 Turtle Team. All rights reserved.
//

import SwiftUI
import shared

@main
struct iOSApp: App {
    @UIApplicationDelegateAdaptor(AppDelegate.self)
        var appDelegate: AppDelegate

        var body: some Scene {
            WindowGroup {
                RootView(component: appDelegate.root)
            }
        }
}

class AppDelegate: NSObject, UIApplicationDelegate {
    let root: RootComponent = RootComponentImpl(
        componentContext: DefaultComponentContext(lifecycle: ApplicationLifecycle())
    )
}
