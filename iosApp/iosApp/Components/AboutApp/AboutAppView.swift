//
//  AboutAppView.swift
//  iosApp
//
//  Created by Егор Лядский on 2024-03-26.
//  Copyright © 2024 Turtle Team. All rights reserved.
//

import SwiftUI
import shared

struct AboutAppView: View {
    
    let component: AboutAppComponent
    
    var body: some View {
        Text("About App View")
        Button(action: { component.onBackButtonClick() }, label: { Text("Back") })
    }
}
