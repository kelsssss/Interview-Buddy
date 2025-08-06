package com.example.interviewbuddy.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.interviewbuddy.ui.theme.InterviewBuddyTheme
import com.example.interviewbuddy.viewmodels.ChatViewModel

@Composable
fun ChoiceScreen(
    navController: NavController,
    chatViewModel: ChatViewModel = viewModel()
){
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Какой чат вы хотите создать?",
                fontSize = 30.sp,
                textAlign = TextAlign.Center,
                lineHeight = 40.sp
            )
            Button(
                onClick = {
                    var newChatId = chatViewModel.createInterviewChat()
                    navController.navigate("chat/${newChatId}")
                },
                modifier = Modifier.padding(top = 25.dp)

            ) {
                Text(
                    text = "Тестовое собеседование",
                    fontSize = 25.sp
                    )
            }
            Button(
                onClick = {
                    var newChatId = chatViewModel.createNewChat()
                    navController.navigate("chat/${newChatId}")
                },
                modifier = Modifier.padding(top = 15.dp)
            ) {
                Text(
                    text = "Обычный чат с ИИ",
                    fontSize = 25.sp
                    )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChoiceScreenPreview(){
    InterviewBuddyTheme {
        ChoiceScreen(
            navController = rememberNavController()
        )
    }
}
