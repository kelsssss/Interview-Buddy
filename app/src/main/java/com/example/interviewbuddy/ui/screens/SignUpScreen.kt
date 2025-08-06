package com.example.interviewbuddy.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.interviewbuddy.ui.theme.InterviewBuddyTheme
import com.example.interviewbuddy.viewmodels.AuthViewModel
import com.example.interviewbuddy.viewmodels.ChatViewModel

@Composable
fun SignUpScreen(
    navController: NavController,
    authViewModel: AuthViewModel = viewModel(),
    chatViewModel: ChatViewModel = viewModel(),

    ) {
    var context = LocalContext.current
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var repeatedPassword by remember { mutableStateOf("") }
    var passwordIsVisible by remember { mutableStateOf(false) }

    var lastChatId = chatViewModel.chats.collectAsState().value.last().id



    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(text = "Создать аккаунт", fontSize = 40.sp)
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text(text = "Почта") },
                placeholder = { Text(text = "примерпочты@mail.com") },
            )

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text(text = "Пароль") },
                placeholder = { Text(text = "пароль123") },
                visualTransformation =
                    if (passwordIsVisible == false) {
                        PasswordVisualTransformation()
                    } else {
                        VisualTransformation.None
                    },
                trailingIcon = {
                    IconButton(
                        onClick = {
                            passwordIsVisible = !passwordIsVisible
                        }
                    ) {
                        Icon(
                            imageVector = if (passwordIsVisible) Icons.Filled.VisibilityOff
                            else Icons.Filled.Visibility,
                            contentDescription = null
                        )
                    }
                }
            )

            Spacer(modifier = Modifier.height(20.dp))

            if (password != "" && repeatedPassword != "" && password != repeatedPassword) {
                Text(text = "Пароли различаются!", color = Color.Red)
                Spacer(modifier = Modifier.height(10.dp))
            }

            OutlinedTextField(
                value = repeatedPassword,
                onValueChange = { repeatedPassword = it },
                label = { Text(text = "Повторите пароль") },
                placeholder = { Text(text = "пароль123") },
                visualTransformation =
                    if (passwordIsVisible == false) {
                        PasswordVisualTransformation()
                    } else {
                        VisualTransformation.None
                    },
                trailingIcon = {
                    IconButton(
                        onClick = {
                            passwordIsVisible = !passwordIsVisible
                        }
                    ) {
                        Icon(
                            imageVector = if (passwordIsVisible) Icons.Filled.VisibilityOff
                            else Icons.Filled.Visibility,
                            contentDescription = null
                        )
                    }
                }
            )
            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = {
                    authViewModel.signUp(
                        email = email,
                        password = password,
                        navController = navController,
                        context = context,
                        lastChatId = lastChatId,
                        onComplete = {chatViewModel.loadChats()}

                    )
                }
            ) {
                Text(text = "Создать аккаунт")
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(text = "Уже есть аккаунт?")


            Button(
                onClick = {
                    navController.navigate("signIn")
                }
            ) {
                Text(text = "Войти")
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
    InterviewBuddyTheme {
//        SignUpScreen()
    }
}



