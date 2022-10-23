package com.example.exptracker.database

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity
data class TransactionEntity(
    @PrimaryKey val id: String,
    @NonNull val amount: Float,
    @NonNull val description: String,
    @NonNull val category: String,
    @NonNull @ColumnInfo(name = "date_time")val dateTime: LocalDateTime,
    @NonNull val currency: String
)