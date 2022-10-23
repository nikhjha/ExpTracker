package com.example.exptracker.viewmodels

import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exptracker.data.Currencies
import com.example.exptracker.data.Currency
import com.example.exptracker.data.CustomMonth
import com.example.exptracker.data.getAllMonths
import com.example.exptracker.datastore.UserPreference
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class UserDetailViewModel(private val userPreference: UserPreference) : ViewModel() {
    val budget = userPreference.getSelectedBudget
    val currency = userPreference.getSelectedCurrency


    private val _selectedMonth = MutableStateFlow(getAllMonths()[LocalDateTime.now().monthValue - 1])
    val selectedMonth = _selectedMonth.asStateFlow()



    fun updateBudget(newBudget: Float) {
        viewModelScope.launch {
            userPreference.setBudget(newBudget)
        }
    }

    fun updateCurrency(newCurrency: Currency){
        viewModelScope.launch {
            userPreference.setCurrency(newCurrency)
        }
    }

    fun updateMonth(newMonth: CustomMonth){
        _selectedMonth.value = newMonth
    }
}