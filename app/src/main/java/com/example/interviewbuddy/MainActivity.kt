package com.example.interviewbuddy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.interviewbuddy.navigation.Navigation
import com.example.interviewbuddy.ui.theme.InterviewBuddyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InterviewBuddyTheme {
//                ChatScreen()
                Navigation()
            }
        }
    }
}