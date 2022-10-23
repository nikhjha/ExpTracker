package com.example.exptracker.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {
    @Query("SELECT * FROM transactionEntity")
    fun getAll(): Flow<List<TransactionEntity>>

    @Query("SELECT * FROM transactionEntity WHERE id == :id")
    fun getTransaction(id : String) : TransactionEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTransaction(vararg tx: TransactionEntity)

    @Update
    fun updateTransaction(vararg tx: TransactionEntity)

    @Delete
    fun deleteTransaction(vararg tx: TransactionEntity)
}