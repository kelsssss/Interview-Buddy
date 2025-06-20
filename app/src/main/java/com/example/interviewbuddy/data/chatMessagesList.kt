package com.example.interviewbuddy.data

import androidx.compose.runtime.mutableStateListOf
import kotlinx.coroutines.flow.MutableStateFlow

var chatMessagesList = MutableStateFlow<MutableList<Message>>(mutableListOf(
    Message(
        content = "Привет! Чем я могу помочь?",
        role = Role.ASSISTANT.type
    ),
)
)
