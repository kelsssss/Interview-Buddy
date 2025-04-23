package com.example.interviewbuddy.ui.components

//import androidx.compose.foundation.lazy.LazyColumn
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.interviewbuddy.ChatViewModel
import com.example.interviewbuddy.data.Message
import com.example.interviewbuddy.data.Role
import com.example.interviewbuddy.data.chatMessagesList
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun ChatScreen() {
    var text by remember { mutableStateOf("") }
    var listState = rememberLazyListState()
    var viewModel: ChatViewModel = viewModel()
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            LazyColumn(
                modifier = Modifier
                    .padding(start = 20.dp, end = 20.dp)
                    .weight(1f),
                verticalArrangement = Arrangement.Bottom,
                state = listState

            ) {
                items(chatMessagesList) {
                    MessageBubble(
                        message = it
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }

            LaunchedEffect(chatMessagesList.size) {
                delay(100)
                listState.animateScrollToItem(chatMessagesList.lastIndex)
            }

            TextField(
                value = text,
                onValueChange = { text = it },
                placeholder = { Text(text = "Send a message") },
                trailingIcon = {
                    IconButton(
                        onClick = {
                            if (text != "") {
                                chatMessagesList.add(
                                    Message(
                                        content = text,
                                        role = Role.USER.type
                                    )
                                )
                                viewModel.viewModelScope.launch {
                                    Log.d("MyLog", "Scope начался")
                                    try {
                                        Log.d("MyLog", "Try начался")
                                        var responce = viewModel.askQuestion(chatMessagesList)
                                        Log.d("MyLog", "Данные получены")
                                        chatMessagesList.add(responce.choices[0].message)
                                        Log.d(
                                            "MyLog",
                                            "Данные получены и сообщение добавлено в репозиторий"
                                        )
                                    } catch (e: Exception) {
                                        Log.d("MyLog", "Ошибка словлена")
                                    }
                                }
                                text = ""
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Outlined.Send,
                            contentDescription = null
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}