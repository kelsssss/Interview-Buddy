package com.example.interviewbuddy.data

import androidx.compose.runtime.mutableStateListOf

//Первое сообщение
var chatMessagesList = mutableStateListOf<Message>(
    Message(
        content = "Привет! Чем я могу помочь?",
        role = Role.ASSISTANT.type
    ),
)
