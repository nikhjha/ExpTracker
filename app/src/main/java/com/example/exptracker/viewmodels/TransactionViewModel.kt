package com.example.exptracker.viewmodels

import androidx.lifecycle.ViewModel
import com.example.exptracker.data.Transaction
import com.example.exptracker.data.dummyTx
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class TransactionViewModel : ViewModel() {
    private val _txs = MutableStateFlow(dummyTx)
    val txs = _txs.asStateFlow()
    val changeTx: (Transaction?) -> Unit = { tx ->
        _txs.update { oldTxs ->
            oldTxs.map {
                if(tx != null && tx.id == it.id) return@map tx else it
            }
        }
    }
    val addTx: (Transaction) -> Unit = { tx ->
        _txs.update {
            listOf(tx,*it.toTypedArray())
        }
    }

    fun deleteTx(id : String){
        _txs.update {
            it.filter {
                txId -> txId.id != id
            }
        }
    }
}