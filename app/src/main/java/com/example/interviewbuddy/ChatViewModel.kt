package com.example.interviewbuddy

import androidx.lifecycle.ViewModel
import com.example.interviewbuddy.data.Message
import com.example.interviewbuddy.data.QuestionRequest
import com.example.interviewbuddy.data.QuestionResponce
import com.example.interviewbuddy.network.Retrofit

class ChatViewModel: ViewModel() {
    var api = Retrofit.apiService
    suspend fun askQuestion(content: String) : QuestionResponce{
        return api.askQuestion(
            questionRequest =  QuestionRequest(
                messages = listOf<Message>(
                Message(
                    role = "user",
                    content = content
                )
            )
            )
        )
    }
}