package com.example.interviewbuddy.data

data class QuestionRequest(
    var model: String = "mistral-large-latest",
    var messages: List<Message>
)
