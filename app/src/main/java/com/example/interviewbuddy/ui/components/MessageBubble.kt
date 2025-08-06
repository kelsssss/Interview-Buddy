package com.example.interviewbuddy.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.interviewbuddy.data.Message
import com.example.interviewbuddy.data.Role
import com.halilibo.richtext.markdown.Markdown
//import com.halilibo.richtext.ui.RichText
import com.halilibo.richtext.ui.BasicRichText

@Composable
fun MessageBubble(
    message: Message
) {
    var author = message.role
    var text = message.content
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = when (author) {
            Role.ASSISTANT.type -> Arrangement.Start
            Role.USER.type -> Arrangement.End
            else -> Arrangement.Start
        }

    ) {
        Box(
            modifier = Modifier
                .background(
                    color = when (author) {
                        Role.ASSISTANT.type -> Color.White
                        Role.USER.type -> Color.Green
                        else -> {
                            Color.Green
                        }
                    },
                    shape = RoundedCornerShape(15.dp)
                )
                .padding(8.dp)

        ) {
            //Тут поменял RichText на этот
            BasicRichText(
                modifier = Modifier.widthIn(max = 250.dp),
            ) {
                Markdown(
                    content = text
                )

            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun MessagePreview() {
//    InterviewBuddyTheme(darkTheme = true) {
//        Scaffold(
//            modifier = Modifier.fillMaxSize()
//        ) { innerPadding ->
//            Column(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(innerPadding),
//                verticalArrangement = Arrangement.Bottom
//            ) {
//                MessageBubble(
////                    message = chatMessagesList.[0]
//                )
//            }
//        }
//
//    }
//}