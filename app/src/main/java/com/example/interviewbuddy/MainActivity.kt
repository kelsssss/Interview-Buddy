package com.example.interviewbuddy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.example.interviewbuddy.navigation.Navigation
import com.example.interviewbuddy.ui.theme.InterviewBuddyTheme
import com.example.interviewbuddy.viewmodels.ChatViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class MainActivity : ComponentActivity() {
    private val chatViewModel: ChatViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        if(Firebase.auth.currentUser != null){
            chatViewModel.loadChats()
        }
        setContent {
            InterviewBuddyTheme {
                Navigation()
            }
        }
    }
}