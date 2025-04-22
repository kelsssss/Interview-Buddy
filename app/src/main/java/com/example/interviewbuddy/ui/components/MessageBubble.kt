package com.example.interviewbuddy.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.interviewbuddy.data.Role
import com.example.interviewbuddy.data.Message
import com.example.interviewbuddy.data.testRepository
import com.example.interviewbuddy.ui.theme.InterviewBuddyTheme

@Composable
fun MessageBubble(
    message: Message
){
    var author = message.role
    var text = message.content
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement =  when(author){
            Role.ASSISTANT.type -> Arrangement.Start
            Role.USER.type -> Arrangement.End
            else -> Arrangement.Start
        }

    ){
        Box(
            modifier = Modifier.background(
                color = when(author){
                    Role.ASSISTANT.type -> Color.White
                    Role.USER.type -> Color.Green
                    else -> {Color.Green}
                },
                shape = RoundedCornerShape(15.dp)
            )
                .padding(8.dp)

        ) {
            Text(text = text,
                color = Color.Black,
                modifier = Modifier.widthIn(max = 250.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MessagePreview() {
    InterviewBuddyTheme(darkTheme = true) {
        Scaffold(
            modifier = Modifier.fillMaxSize()
        ) { innerPadding ->
            Column(
                modifier = Modifier.fillMaxSize()
                    .padding(innerPadding),
                verticalArrangement = Arrangement.Bottom
            ) {
                MessageBubble(
                    message = testRepository[0]
                    )
            }
        }

    }
}