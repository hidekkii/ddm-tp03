package com.example.tarefasapp

data class Task(
    val name: String,
    val description: String,
    var isCompleted: Boolean = false
)
