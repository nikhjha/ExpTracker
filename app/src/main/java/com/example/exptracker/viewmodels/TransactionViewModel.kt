package com.example.exptracker.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exptracker.data.Transaction
import com.example.exptracker.database.EntityConverter
import com.example.exptracker.database.TransactionDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionViewModel @Inject constructor(
    private val txDao: TransactionDao
) : ViewModel() {
    private val _txs = MutableStateFlow(listOf<Transaction>())
    val txs = _txs.asStateFlow()

    init {
        viewModelScope.launch {
            txDao.getAll().collect{ newTxs ->
                _txs.update {
                    EntityConverter.convertTxsEntityToTxs(newTxs).sortedByDescending {
                        it.dateTime
                    }
                }
            }
        }
    }

    val changeTx: (Transaction?) -> Unit = { tx ->
        GlobalScope.launch {
            tx?.let {
                txDao.updateTransaction(EntityConverter.convertTxToTxEntity(it))
            }
        }
    }
    val addTx: (Transaction) -> Unit = { tx ->
        GlobalScope.launch {
            txDao.insertTransaction(EntityConverter.convertTxToTxEntity(tx))
        }
    }

    fun deleteTx(id: String) {
        GlobalScope.launch {
            txDao.deleteTransaction(txDao.getTransaction(id))
        }
    }
}