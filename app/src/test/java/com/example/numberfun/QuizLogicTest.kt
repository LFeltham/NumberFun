package com.example.numberfun

import com.example.numberfun.ui.screens.generateQuestion
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class QuizLogicTest {

    @Test
    fun easyQuestion_numbersAreWithinExpectedRange() {
        repeat(50) {
            val question = generateQuestion("Easy")

            assertTrue(question.firstNumber in 1..10)
            assertTrue(question.secondNumber in 1..10)
        }
    }

    @Test
    fun mediumQuestion_numbersAreWithinExpectedRange() {
        repeat(50) {
            val question = generateQuestion("Medium")

            assertTrue(question.firstNumber in 1..20)
            assertTrue(question.secondNumber in 1..20)
        }
    }

    @Test
    fun hardQuestion_numbersAreWithinExpectedRange() {
        repeat(50) {
            val question = generateQuestion("Hard")

            assertTrue(question.firstNumber in 1..50)
            assertTrue(question.secondNumber in 1..50)
        }
    }

    @Test
    fun generatedQuestion_correctAnswerMatchesNumbers() {
        repeat(50) {
            val question = generateQuestion("Easy")

            assertEquals(
                question.firstNumber + question.secondNumber,
                question.correctAnswer
            )
        }
    }

    @Test
    fun generatedQuestion_containsFourAnswersIncludingCorrectAnswer() {
        repeat(50) {
            val question = generateQuestion("Easy")

            assertEquals(4, question.answers.size)
            assertTrue(
                question.answers.contains(question.correctAnswer)
            )
        }
    }
}