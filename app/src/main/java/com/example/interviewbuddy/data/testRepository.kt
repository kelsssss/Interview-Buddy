package com.example.interviewbuddy.data

import androidx.compose.runtime.mutableStateListOf

var testRepository = mutableStateListOf<Message>(
    Message(
        content = "Привет! Чем я могу помочь?",
        role = Role.ASSISTANT.type
    ),
//    Message(
//        content = "message2",
//        role = Role.ASSISTANT.type
//    ),
//    Message(
//        content = "message3",
//        role = Role.ASSISTANT.type
//    ),
)
