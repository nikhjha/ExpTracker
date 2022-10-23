package com.example.exptracker.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.exptracker.data.Currency
import com.example.exptracker.data.getCurrency
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPreference(private val context: Context) {
    companion object {
        private val Context.datastore : DataStore<Preferences> by preferencesDataStore("userPreferences")
        val USER_CURRENCY_SELECTION = stringPreferencesKey("user_currency")
        val USER_BUDGET_SELECTION = floatPreferencesKey(name = "user_budget")
    }

    val getSelectedBudget : Flow<Float?> = context.datastore.data.map {
        preferences ->  preferences[USER_BUDGET_SELECTION] ?: 0f
    }

    val getSelectedCurrency : Flow<Currency?> = context.datastore.data.map {
        preferences -> getCurrency(preferences[USER_CURRENCY_SELECTION] ?: "1")
    }

    suspend fun setCurrency( currency: Currency){
        context.datastore.edit {
            it[USER_CURRENCY_SELECTION] = currency.id
        }
    }

    suspend fun setBudget( budget: Float){
        context.datastore.edit {
            it[USER_BUDGET_SELECTION] = budget
        }
    }
}