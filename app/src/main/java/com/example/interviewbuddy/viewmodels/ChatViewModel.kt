package com.example.interviewbuddy.viewmodels

import android.util.Log
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
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.update
import okhttp3.internal.wait

class ChatViewModel : ViewModel() {

    var api = Retrofit.apiService
    val db = Firebase.firestore

    //    var chats = MutableStateFlow<List<Chat>>(emptyList())
    var chats = ChatRepository.chats

    suspend fun askQuestion(messages: SnapshotStateList<Message>): QuestionResponce {
        return api.askQuestion(
            questionRequest = QuestionRequest(
                messages = messages
            )
        )
    }

    fun createNewChat(): String {
//        Log.d("MyLog", "Вызов createnewchat")
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

    //TODO: Это тестовое(без привязки к аккаунту, поменять позже)
    fun addChat(chat: Chat){
        db.collection("test").document().set(chat)
            .addOnSuccessListener {
                Log.d("MyLog", "Чат добавлен")
            }.addOnFailureListener {
                Log.d("MyLog", "Ошибка")
            }
    }
}