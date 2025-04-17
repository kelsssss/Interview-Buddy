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
import com.example.interviewbuddy.data.Author
import com.example.interviewbuddy.data.Message
import com.example.interviewbuddy.data.testRepository
import com.example.interviewbuddy.ui.theme.InterviewBuddyTheme

@Composable
fun MessageBubble(
    message: Message
){
    var author = message.author
    var text = message.text
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement =  when(author){
            Author.SYSTEM -> Arrangement.Start
            Author.USER -> Arrangement.End
        }

    ){
        Box(
            modifier = Modifier.background(
                color = when(author){
                    Author.SYSTEM -> Color.White
                    Author.USER -> Color.Green
                },
                shape = RoundedCornerShape(15.dp)
            )
                .padding(8.dp)

        ) {
            Text(text = text,
                color = Color.Black,
                modifier = Modifier.widthIn(max = 220.dp))
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