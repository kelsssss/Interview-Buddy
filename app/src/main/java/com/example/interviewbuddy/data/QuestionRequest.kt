package com.example.interviewbuddy.data

data class QuestionRequest(
    var model: String = "mistral-large-latest",
    var messages: List<Message> = listOf<Message>(
        Message(
            role = "user",
            content = "Назови плюсы и минусы linux"
        )
    )
)
