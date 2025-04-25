package com.example.interviewbuddy.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.interviewbuddy.ui.screens.ChatScreen
import com.example.interviewbuddy.ui.screens.SignInScreen
import com.example.interviewbuddy.ui.screens.SignUpScreen
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


@Composable
fun Navigation() {
    var auth = Firebase.auth

    val navController = rememberNavController()

    Log.d("MyLog", "Почта зашедшего ${auth.currentUser?.email}")

    NavHost(
        navController = navController,
        startDestination = if (auth.currentUser != null) {
            "chat"
        } else {
            "signIn"
        }
    ) {
        composable("signIn") {
            SignInScreen(navController = navController)
        }

        composable("signUp") {
            SignUpScreen(navController = navController)
        }

        composable("chat") {
            ChatScreen(navController = navController)
        }
    }
}







