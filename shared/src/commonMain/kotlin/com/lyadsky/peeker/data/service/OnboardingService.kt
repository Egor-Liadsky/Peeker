package com.lyadsky.peeker.data.service

import com.lyadsky.peeker.data.storage.Storage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

class OnboardingService(
    private val storage: Storage
) {

    suspend fun setPassedOnboarding(value: Boolean) = withContext(Dispatchers.IO) {
        storage.setPassedOnboarding(value)
    }

    suspend fun getPassedOnboarding(): Boolean = withContext(Dispatchers.IO) {
        storage.getPassedOnboarding()
    }
}