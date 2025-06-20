package com.example.interviewbuddy.viewmodels

import androidx.lifecycle.ViewModel
import com.example.interviewbuddy.data.Chat
import com.example.interviewbuddy.data.ChatRepository
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import android.util.Log
import com.google.firebase.firestore.toObjects


class StoreViewModel : ViewModel() {
    val auth = Firebase.auth
    var userUid = auth.currentUser?.uid ?: ""
    val db = Firebase.firestore
    var localChats = ChatRepository.chats

    fun saveChat(currentChat: Chat) {
        db.collection("users")
            .document(userUid)
            .collection("UserChats")
            .document(currentChat.id)
            .set(currentChat)
    }

//    fun loadChats() {
//        var result: List<Chat> = emptyList()
//        db.collection("users")
//            .document(userUid)
//            .collection("UserChats")
//            .get()
//            .addOnSuccessListener { chats ->
//                for(chat in chats){
//                    Log.d("MyLog", "Полученный чат - ${chat.get("id")}")
//                }
//                result = chats.toObjects(Chat::class.java)
//                localChats.value += result
//            }
//            .addOnFailureListener {
//                Log.d("MyTag", "Чаты не скачались с firestore")
//            }
//    }
}