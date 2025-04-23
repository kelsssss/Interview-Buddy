package com.example.interviewbuddy.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.interviewbuddy.ui.screens.SignInScreen
import androidx.navigation.compose.composable
import com.example.interviewbuddy.ui.screens.ChatScreen
import com.example.interviewbuddy.ui.screens.SignUpScreen


//@Composable
//fun Navigation(){
//
//    val navController = rememberNavController()
//
//    NavHost(
//        navController = navController,
//        startDestination = SignInScreenRoute
//    ) {
//        composable<SignInScreenRoute> {
//            SignInScreen(navController = navController)
//        }
//
//        composable<SighUpScreenRoute> {
//            SignUpScreen(navController = navController)
//        }
//
//        composable<ChatScreenRoute> {
//            ChatScreen(navController = navController)
//        }
//    }
//}

@Composable
fun Navigation(){

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "signUp"
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







