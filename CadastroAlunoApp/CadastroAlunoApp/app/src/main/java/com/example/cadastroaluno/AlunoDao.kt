package com.example.cadastroaluno

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

/**
 * DAO (Data Access Object) responsável pelo acesso aos dados de Aluno
 * no banco de dados local (Room).
 */
@Dao
interface AlunoDao {

    @Insert
    suspend fun inserir(aluno: Aluno)

    @Query("SELECT * FROM alunos ORDER BY id DESC")
    suspend fun listarTodos(): List<Aluno>
}
