package com.example.exptracker.di

import android.content.Context
import com.example.exptracker.database.AppDatabase
import com.example.exptracker.database.TransactionDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideTxsDb(
        @ApplicationContext context: Context
    ) = AppDatabase.getDatabase(context)

    @Provides
    fun provideDao(
        appDatabase: AppDatabase
    ) : TransactionDao {
        return appDatabase.transactionDao()
    }
}