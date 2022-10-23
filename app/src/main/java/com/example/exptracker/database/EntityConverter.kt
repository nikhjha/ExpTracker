package com.example.exptracker.database

import com.example.exptracker.data.*

object EntityConverter {
    fun convertTxsEntityToTxs(txs : List<TransactionEntity>) : List<Transaction> {
        return txs.map {
            Transaction(
                it.id,
                it.amount,
                it.description,
                getCategory(it.category) ?: Category.Miscellaneous,
                it.dateTime,
                getCurrency(it.currency) ?: Currencies[1]
            )
        }
    }
    fun convertTxToTxEntity(tx : Transaction) : TransactionEntity {
        return TransactionEntity(
            id = tx.id,
            amount = tx.amount,
            description = tx.description,
            category = tx.category.id,
            dateTime = tx.dateTime,
            currency = tx.currency.id
        )
    }
}