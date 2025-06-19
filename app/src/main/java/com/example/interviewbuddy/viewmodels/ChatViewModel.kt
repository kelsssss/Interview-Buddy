package com.example.interviewbuddy.viewmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import com.example.interviewbuddy.data.Chat
import com.example.interviewbuddy.data.ChatRepository
import com.example.interviewbuddy.data.Message
import com.example.interviewbuddy.data.QuestionRequest
import com.example.interviewbuddy.data.QuestionResponce
import com.example.interviewbuddy.data.Role
import com.example.interviewbuddy.network.Retrofit
import kotlinx.coroutines.flow.update

class ChatViewModel : ViewModel() {

    var api = Retrofit.apiService
    var chats = ChatRepository.chats

    suspend fun askQuestion(messages: SnapshotStateList<Message>): QuestionResponce {
        return api.askQuestion(
            questionRequest = QuestionRequest(
                messages = messages
            )
        )
    }

    fun createNewChat(): String {
        var newChat = Chat(
            messages = mutableStateListOf(
                Message(
                    content = "Привет! Чем я могу помочь?",
                    role = Role.ASSISTANT.type
                )
            )
        )
        chats.update { chats -> chats + newChat }
        return newChat.id
    }

    //TODO: Сделать нормальную добавку сообщений через ViewModel(эта так себе)
    fun addMessage(chatId: String, message: Message) {
//        chats.value.forEach { chat ->
//            if(chat.id == chatId){
//                chat.messages.add(message)
//            }
//        }
        chats.value.find { it.id == chatId }!!.messages.add(message)
    }
}