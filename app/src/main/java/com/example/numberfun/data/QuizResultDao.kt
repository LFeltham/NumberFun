package com.example.numberfun.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface QuizResultDao {

    @Insert
    suspend fun insertQuizResult(result: QuizResult)

    @Query("SELECT * FROM quiz_results ORDER BY completedAt DESC")
    fun getAllResults(): Flow<List<QuizResult>>

    @Query("SELECT COUNT(*) FROM quiz_results")
    fun getQuizCount(): Flow<Int>

    @Query("SELECT COALESCE(MAX(score), 0) FROM quiz_results")
    fun getBestScore(): Flow<Int>

    @Query("SELECT COALESCE(SUM(totalQuestions), 0) FROM quiz_results")
    fun getTotalQuestions(): Flow<Int>

    @Query("SELECT COALESCE(SUM(score), 0) FROM quiz_results")
    fun getTotalCorrect(): Flow<Int>
}