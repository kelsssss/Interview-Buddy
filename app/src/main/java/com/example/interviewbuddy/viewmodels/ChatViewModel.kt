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
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.flow.update
import kotlin.collections.plus

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


    //TODO: не работает, исправить, мб вообще переписать
    fun loadChats() {
        var db = Firebase.firestore
        var userUid = Firebase.auth.currentUser?.uid ?: ""
//        var result: List<Chat> = emptyList()
        db.collection("users")
            .document(userUid)
            .collection("UserChats")
            .get()
            .addOnSuccessListener { querySnapshot ->
                for(chat in querySnapshot.documents){
                    Log.d("MyLog", "Полученный чат - ${chat.get("id")}")
                    if(chat != null){
                        chats.update { it + chat.toObject(Chat::class.java)!! }
                    }

                }
//                result = querySnapshot.documents.
//                chats.value += result
            }
            .addOnFailureListener {
                Log.d("MyTag", "Чаты не скачались с firestore")
            }
    }
}