package com.example.interviewbuddy.data

import java.util.UUID

data class Chat(
    val id: String = UUID.randomUUID().toString(),
    val messages: MutableList<Message> = mutableListOf(),
    val title: String = "chat"
)
