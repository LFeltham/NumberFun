package com.example.numberfun

import com.example.numberfun.ui.screens.generateQuestion
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class QuizLogicTest {

    @Test
    fun easyQuestion_numbersStayWithinEasyRange() {
        repeat(50) {
            val question = generateQuestion("Easy")

            assertTrue(question.firstNumber in 1..10)
            assertTrue(question.secondNumber in 1..10)
        }
    }

    @Test
    fun mediumQuestion_numbersStayWithinMediumRange() {
        repeat(50) {
            val question = generateQuestion("Medium")

            assertTrue(question.firstNumber in 1..20)
            assertTrue(question.secondNumber in 1..20)
        }
    }

    @Test
    fun hardQuestion_numbersStayWithinHardRange() {
        repeat(50) {
            val question = generateQuestion("Hard")

            assertTrue(question.firstNumber in 1..50)
            assertTrue(question.secondNumber in 1..50)
        }
    }

    @Test
    fun correctAnswer_matchesQuestionNumbers() {
        repeat(50) {
            val question = generateQuestion("Easy")

            assertEquals(
                question.firstNumber + question.secondNumber,
                question.correctAnswer
            )
        }
    }

    @Test
    fun question_hasFourAnswers_andIncludesCorrectAnswer() {
        repeat(50) {
            val question = generateQuestion("Easy")

            assertEquals(4, question.answers.size)
            assertTrue(
                question.answers.contains(question.correctAnswer)
            )
        }
    }
}