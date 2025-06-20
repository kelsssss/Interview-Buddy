package com.example.interviewbuddy.data

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import kotlinx.coroutines.flow.MutableStateFlow
import java.util.UUID

data class Chat(
    val id: String = UUID.randomUUID().toString(),
    val messages: SnapshotStateList<Message> = mutableStateListOf(),
//    val messages: MutableStateFlow<List<Message>> = MutableStateFlow(emptyList()),
    val title: String = "chat"
)
