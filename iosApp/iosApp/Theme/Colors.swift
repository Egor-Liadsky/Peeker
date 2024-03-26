//
//  Color.swift
//  iosApp
//
//  Created by Егор Лядский on 2024-03-26.
//  Copyright © 2024 Turtle Team. All rights reserved.
//

import SwiftUI

extension Color {
    
    struct Base {
        public static let white = Color(hex: "FFFFFF")
    }
    
    struct BottomBar {
        public static let selectedNavigationItem = Color(hex: "5D00F5")
        public static let unselectedNavigationItem = Color(hex: "9E9C9F").opacity(0.85)
        public static let background = Color(hex: "F9F9F9").opacity(0.94)
        public static let stroke = Color(hex: "EDEDED")
    }
}
