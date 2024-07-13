package com.lyadsky.peeker.data.storage

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class OnboardingStorageRepository(private val dataStore: DataStore<Preferences>) {

    companion object {
        private val PASSED_ONBOARDING = booleanPreferencesKey("PASSED_ONBOARDING")
    }

    private val passedOnboarding: Flow<Boolean>
        get() = dataStore.data.map { prefs ->
            prefs[PASSED_ONBOARDING] ?: false
        }

    suspend fun setPassedOnboarding(value: Boolean) = withContext(Dispatchers.IO) {
        dataStore.edit { storage ->
            storage[PASSED_ONBOARDING] = value
        }
    }

    suspend fun getPassedOnboarding(): Boolean = withContext(Dispatchers.IO) {
        passedOnboarding.first()
    }
}