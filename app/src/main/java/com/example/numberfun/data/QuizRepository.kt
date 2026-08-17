package com.example.numberfun.data

import kotlinx.coroutines.flow.Flow

class QuizRepository(
    private val quizResultDao: QuizResultDao
) {

    fun getAllResults(): Flow<List<QuizResult>> {
        return quizResultDao.getAllResults()
    }

    fun getQuizCount(): Flow<Int> {
        return quizResultDao.getQuizCount()
    }

    fun getBestScore(): Flow<Int> {
        return quizResultDao.getBestScore()
    }

    fun getTotalQuestions(): Flow<Int> {
        return quizResultDao.getTotalQuestions()
    }

    fun getTotalCorrect(): Flow<Int> {
        return quizResultDao.getTotalCorrect()
    }

    suspend fun saveQuizResult(
        score: Int,
        totalQuestions: Int,
        difficulty: String
    ) {
        val result = QuizResult(
            score = score,
            totalQuestions = totalQuestions,
            difficulty = difficulty
        )

        quizResultDao.insertQuizResult(result)
    }
}