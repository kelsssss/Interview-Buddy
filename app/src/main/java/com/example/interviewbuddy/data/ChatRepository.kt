package com.example.interviewbuddy.data

import kotlinx.coroutines.flow.MutableStateFlow

object ChatRepository {
    var chats = MutableStateFlow<List<Chat>>(emptyList())
}