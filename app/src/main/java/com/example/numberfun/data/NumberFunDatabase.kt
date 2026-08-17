package com.example.numberfun.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [QuizResult::class],
    version = 1,
    exportSchema = false
)
abstract class NumberFunDatabase : RoomDatabase() {

    abstract fun quizResultDao(): QuizResultDao

    companion object {

        @Volatile
        private var INSTANCE: NumberFunDatabase? = null

        fun getDatabase(context: Context): NumberFunDatabase {

            return INSTANCE ?: synchronized(this) {

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NumberFunDatabase::class.java,
                    "numberfun_database"
                ).build()

                INSTANCE = instance

                instance
            }
        }
    }
}