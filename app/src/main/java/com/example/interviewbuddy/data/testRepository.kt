package com.example.interviewbuddy.data

import androidx.compose.runtime.mutableStateListOf
import com.example.interviewbuddy.data.Message

var testRepository = mutableStateListOf<Message>(
    Message(
        text = "There will be a question from Interview Buddy!",
        author = Author.SYSTEM
    ),
    Message(
        text = "message2",
        author = Author.SYSTEM
    ),
    Message(
        text = "message3",
        author = Author.SYSTEM
    ),


)
