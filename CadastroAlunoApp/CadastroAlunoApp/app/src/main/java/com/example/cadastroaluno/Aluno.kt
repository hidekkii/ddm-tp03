package com.example.cadastroaluno

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entidade que representa um aluno cadastrado.
 * Cada aluno possui um nome e um número de matrícula gerado aleatoriamente.
 */
@Entity(tableName = "alunos")
data class Aluno(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nome: String,
    val matricula: Int
)
