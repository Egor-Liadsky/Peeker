package com.lyadsky.peeker.data.storage

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class Storage(private val dataStore: DataStore<Preferences>) {

    companion object {
        private val SEARCHED_PRODUCT = booleanPreferencesKey("SEARCHED_PRODUCT")
        private val PASSED_ONBOARDING = booleanPreferencesKey("PASSED_ONBOARDING")
    }

    private val searchedProduct: Flow<Boolean>
        get() = dataStore.data.map { prefs ->
            prefs[SEARCHED_PRODUCT] ?: false
        }

    private val passedOnboarding: Flow<Boolean>
        get() = dataStore.data.map { prefs ->
            prefs[PASSED_ONBOARDING] ?: false
        }

    suspend fun setSearchedProduct(value: Boolean) {
       dataStore.edit { storage ->
            storage[SEARCHED_PRODUCT] = value
        }
    }

    suspend fun getSearchedProduct(): Boolean {
        return searchedProduct.first()
    }

    suspend fun setPassedOnboarding(value: Boolean) {
        dataStore.edit { storage ->
            storage[PASSED_ONBOARDING] = value
        }
    }

    suspend fun getPassedOnboarding(): Boolean {
        return passedOnboarding.first()
    }
}