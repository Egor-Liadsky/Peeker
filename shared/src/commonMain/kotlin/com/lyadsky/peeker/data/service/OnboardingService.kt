package com.lyadsky.peeker.data.service

import com.lyadsky.peeker.data.storage.OnboardingStorageRepository

class OnboardingService(
    private val onboardingStorageRepository: OnboardingStorageRepository
) {

    suspend fun setPassedOnboarding(value: Boolean) {
        onboardingStorageRepository.setPassedOnboarding(value)
    }

    suspend fun getPassedOnboarding(): Boolean = onboardingStorageRepository.getPassedOnboarding()
}