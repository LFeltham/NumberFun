package com.example.numberfun.di

import android.content.Context
import com.example.numberfun.data.NumberFunDatabase
import com.example.numberfun.data.QuizRepository
import com.example.numberfun.data.QuizResultDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNumberFunDatabase(
        @ApplicationContext context: Context
    ): NumberFunDatabase {
        return NumberFunDatabase.getDatabase(context)
    }

    @Provides
    fun provideQuizResultDao(
        database: NumberFunDatabase
    ): QuizResultDao {
        return database.quizResultDao()
    }

    @Provides
    @Singleton
    fun provideQuizRepository(
        quizResultDao: QuizResultDao
    ): QuizRepository {
        return QuizRepository(quizResultDao)
    }
}