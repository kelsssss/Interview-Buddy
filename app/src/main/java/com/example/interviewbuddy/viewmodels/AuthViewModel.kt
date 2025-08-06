package com.example.interviewbuddy.viewmodels

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.google.firebase.Firebase
import com.google.firebase.auth.auth


class AuthViewModel : ViewModel() {

    var auth = Firebase.auth


    fun signIn(
        email: String,
        password: String,
        navController: NavController,
        context: Context,
        lastChatId: String,
        onComplete: () -> Unit
    ) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("MyLog", "Успех авторизации")
                    navController.navigate("chat/${lastChatId}")
                    onComplete()
                } else {
                    Log.d("MyLog", "Ошибка авторизации")
                    Toast.makeText(
                        context,
                        "Ошибка авторизации.",
                        Toast.LENGTH_SHORT,
                    ).show()
                }
            }
    }

    fun signUp(
        email: String,
        password: String,
        navController: NavController,
        context: Context,
        lastChatId: String,
        onComplete: () -> Unit
    ) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("MyLog", "Успех регистрации")
                    navController.navigate("chat/${lastChatId}")
                    onComplete()
                } else {
                    Log.d("MyLog", "Ошибка регистрации")
                    Toast.makeText(
                        context,
                        "Ошибка регистрации.",
                        Toast.LENGTH_SHORT,
                    ).show()
                }
            }
    }

}