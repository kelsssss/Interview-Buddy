package com.example.interviewbuddy.data

import androidx.compose.runtime.snapshots.SnapshotStateList
import java.util.UUID


data class Chat(
    val id: String = UUID.randomUUID().toString(),
    val messages: SnapshotStateList<Message>,
    val title: String = "chat"
)
