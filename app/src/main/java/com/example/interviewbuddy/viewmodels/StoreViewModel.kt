package com.example.interviewbuddy.viewmodels

import androidx.lifecycle.ViewModel
import com.example.interviewbuddy.data.Chat
import com.example.interviewbuddy.data.ChatRepository
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore


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
}