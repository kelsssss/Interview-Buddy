package com.example.interviewbuddy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.interviewbuddy.navigation.Navigation
import com.example.interviewbuddy.ui.theme.InterviewBuddyTheme
import com.example.interviewbuddy.viewmodels.ChatViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
//        var userUid = Firebase.auth.currentUser?.uid
//        if(Firebase.auth.currentUser != null){
//            ChatViewModel().loadChats()
//        }
        setContent {
            InterviewBuddyTheme {
                Navigation()
            }
        }
    }
}