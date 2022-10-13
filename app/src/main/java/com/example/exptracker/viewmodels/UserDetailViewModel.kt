package com.example.exptracker.viewmodels

import androidx.lifecycle.ViewModel
import com.example.exptracker.data.Currencies
import com.example.exptracker.data.Currency
import com.example.exptracker.data.CustomMonth
import com.example.exptracker.data.getAllMonths
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.LocalDateTime

class UserDetailViewModel : ViewModel() {
    private val _budget = MutableStateFlow(0.0f)
    val budget = _budget.asStateFlow()

    private val _currency = MutableStateFlow(Currencies[0])
    val currency = _currency.asStateFlow()

    private val _selectedMonth = MutableStateFlow(getAllMonths()[LocalDateTime.now().monthValue - 1])
    val selectedMonth = _selectedMonth.asStateFlow()

    fun updateBudget(newBudget: Float) {
        _budget.value = newBudget
    }

    fun updateCurrency(newCurrency: Currency){
        _currency.value = newCurrency
    }

    fun updateMonth(newMonth: CustomMonth){
        _selectedMonth.value = newMonth
    }
}