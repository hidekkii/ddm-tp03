package com.example.cadastroaluno

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Aluno::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun alunoDao(): AlunoDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "cadastro_aluno_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
