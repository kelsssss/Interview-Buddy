package com.example.interviewbuddy.network

import com.example.interviewbuddy.data.QuestionRequest
import com.example.interviewbuddy.data.QuestionResponce
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST


interface ApiService {
    @POST("v1/chat/completions")
    suspend fun askQuestion(
        @Header("Content-Type") contentType: String = "application/json",
        @Header("Accept") accept: String = "application/json",
        @Header("Authorization") authorization: String = "Bearer IU0HTilxsQoJDprNrDnOkhjn1l60Xead",
        @Body questionRequest: QuestionRequest
    ): QuestionResponce
}