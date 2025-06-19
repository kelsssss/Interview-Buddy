package com.example.interviewbuddy.viewmodels

import androidx.lifecycle.ViewModel
import com.example.interviewbuddy.data.Chat
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class StoreViewModel : ViewModel() {
    val auth = Firebase.auth
    val userUid = auth.currentUser?.uid ?: ""

    fun saveChat(currentChat: Chat) {
        val db = Firebase.firestore
        db.collection("users")
            .document(userUid)
            .collection("UserChats")
            .document(currentChat.id)
            .set(currentChat)
    }
}