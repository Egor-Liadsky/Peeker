//
//  ObservableValue.swift
//  iosApp
//
//  Created by Егор Лядский on 2024-03-26.
//  Copyright © 2024 Turtle Team. All rights reserved.
//

import SwiftUI
import shared

public class ObservableValue<T : AnyObject> : ObservableObject {
    @Published
    var value: T

    private var cancellation: Cancellation?
    
    init(_ value: Value<T>) {
        self.value = value.value
        self.cancellation = value.subscribe(observer_: { [weak self] value in self?.value = value })
    }

    deinit {
        cancellation?.cancel()
    }
}
