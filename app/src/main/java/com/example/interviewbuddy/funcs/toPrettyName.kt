package com.example.interviewbuddy.funcs

fun String.toPrettyName(): String{
    return when(this){
        "interview"-> "Мок-Собеседование"
        "chat"-> "Чат с ИИ"
        else-> "Чат с ИИ"
    }
}