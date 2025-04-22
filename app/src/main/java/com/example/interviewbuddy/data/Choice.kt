package com.example.interviewbuddy.data

import com.google.gson.annotations.SerializedName

data class Choice(
    var index: Double,
    var message: Message,
    @SerializedName("finish_reason")
    var finishReason: String,
)
