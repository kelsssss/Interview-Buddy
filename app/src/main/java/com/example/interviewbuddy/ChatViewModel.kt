package com.example.interviewbuddy

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import com.example.interviewbuddy.data.Message
import com.example.interviewbuddy.data.QuestionRequest
import com.example.interviewbuddy.data.QuestionResponce
import com.example.interviewbuddy.network.Retrofit

class ChatViewModel : ViewModel() {
    var api = Retrofit.apiService
    suspend fun askQuestion(messages: SnapshotStateList<Message>): QuestionResponce {
        return api.askQuestion(
            questionRequest = QuestionRequest(
                messages = messages
            )
        )
    }
}