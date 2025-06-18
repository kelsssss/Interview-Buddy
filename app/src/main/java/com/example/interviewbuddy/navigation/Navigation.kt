package com.example.interviewbuddy.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.interviewbuddy.ui.screens.ChatScreen
import com.example.interviewbuddy.ui.screens.SignInScreen
import com.example.interviewbuddy.ui.screens.SignUpScreen
import com.example.interviewbuddy.viewmodels.ChatViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


@Composable
fun Navigation() {
    var auth = Firebase.auth
    var chatViewModel: ChatViewModel = viewModel()
    val navController = rememberNavController()

    Log.d("MyLog", "Почта зашедшего ${auth.currentUser?.email}")

    NavHost(
        navController = navController,
//        startDestination = if (auth.currentUser != null) {
//            "chat/new"
//        } else {
//            "signIn"
//        }
        startDestination = "signIn"

    ) {
        composable("signIn") {
            SignInScreen(navController = navController)
        }

        composable("signUp") {
            SignUpScreen(navController = navController)
        }

        composable("chat/{chatId}") { backStackEntry ->
            var chatId = backStackEntry.arguments?.getString("chatId") ?: ""
            if(chatId == "new"){
                Log.d("MyLog", "Чат создался в навигации chat/new")
                var entryChatId = chatViewModel.createNewChat()
                ChatScreen(
                    navController = navController,
                    chatId = entryChatId
                )
            } else {
                ChatScreen(
                    navController = navController,
                    chatId = chatId
                )
            }

        }

//        composable("chatnew") {
//            Log.d("MyLog", "Чат создался в навигации chatnew")
//            var entryChatId = chatViewModel.createNewChat()
//            ChatScreen(
//                navController = navController,
//                chatId = entryChatId
//            )
//        }
    }
}







