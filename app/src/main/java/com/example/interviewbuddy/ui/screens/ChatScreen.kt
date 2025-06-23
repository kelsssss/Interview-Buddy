package com.example.interviewbuddy.ui.screens


import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Send
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.ChatBubble
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.interviewbuddy.data.Chat
import com.example.interviewbuddy.data.Message
import com.example.interviewbuddy.data.Role
import com.example.interviewbuddy.data.chatMessagesList
import com.example.interviewbuddy.ui.components.MessageBubble
import com.example.interviewbuddy.viewmodels.AuthViewModel
import com.example.interviewbuddy.viewmodels.ChatViewModel
import com.example.interviewbuddy.viewmodels.StoreViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun ChatScreen(
    navController: NavController,
    chatId: String
) {
    var drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    var scope = rememberCoroutineScope()
    var text by remember { mutableStateOf("") }
    var listState = rememberLazyListState()
    var chatViewModel: ChatViewModel = viewModel()
    var authViewModel: AuthViewModel = viewModel()
    var auth = authViewModel.auth
    var storeViewModel: StoreViewModel = viewModel()
    var gotMessage by remember { mutableIntStateOf(0) }


    //Список сообщений
    var chats = chatViewModel.chats.collectAsState()
    var currentChat = chats.value.find { it.id == chatId } ?: Chat(messages = chatMessagesList.collectAsState().value)


    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier
                    .width(270.dp)

            ) {
                chats.value.asReversed().forEach {
                    NavigationDrawerItem(
                        label = { Text(text = it.id) },
                        onClick = {
                            navController.navigate("chat/${it.id}")
                        },
                        selected = currentChat.id == it.id,
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                NavigationDrawerItem(
                    label = {
                        Text(
                            text = "Создать чат"
                        )
                    },
                    onClick = {
//                        var newChatId = chatViewModel.createNewChat()
//                        navController.navigate("chat/${newChatId}")
                        navController.navigate("choice")
                    },
                    selected = false,
                )
                NavigationDrawerItem(
                    label = {
                        Row {
                            Icon(
                                Icons.Outlined.AccountCircle,
                                contentDescription = null,
                                modifier = Modifier.padding(3.dp)
                            )
                            Text(
                                text = auth.currentUser?.email ?: "error@mail.com"
                            )
                        }
                    },
                    onClick = {
                        auth.signOut()
                        navController.navigate("signIn")
                    },
                    selected = false,
                )
            }
        },
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize()
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start
                ) {
                    IconButton(
                        onClick = {
                            scope.launch {
                                drawerState.open()
                            }
                        }
                    ) {
                        Icon(
                            Icons.Outlined.ChatBubble,
                            contentDescription = null
                        )
                    }
                }
                LazyColumn(
                    modifier = Modifier
                        .padding(start = 20.dp, end = 20.dp)
                        .weight(1f),
                    verticalArrangement = Arrangement.Bottom,
                    state = listState

                ) {
                    items(currentChat.messages) {
                        MessageBubble(
                            message = it
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
                //TODO: ВКЛЮЧИТЬ ПОТОМ, ЭТО ПРОКРУТКА К НИЖНЕМУ СООБЩЕНИЮ
                LaunchedEffect(currentChat.messages.size) {
                    delay(100)
                    listState.animateScrollToItem(currentChat.messages.lastIndex)
                }

                TextField(
                    value = text,
                    onValueChange = { text = it },
                    placeholder = { Text(text = "Send a message") },
                    trailingIcon = {
                        IconButton(
                            onClick = {
                                if (text != "") {
                                    currentChat.messages += (
                                            Message(
                                                content = text,
                                                role = Role.USER.type
                                            )
                                            )
                                    chatViewModel.viewModelScope.launch {
                                        Log.d("MyLog", "Scope начался")
                                        try {
                                            Log.d("MyLog", "Try начался")
                                            var responce =
                                                chatViewModel.askQuestion(currentChat.messages)
                                            Log.d("MyLog", "Данные получены")
//                                            withContext(Dispatchers.Main) {
                                            currentChat.messages += (responce.choices[0].message)
                                            Log.d(
                                                "MyLog",
                                                "Данные получены и сообщение добавлено в репозиторий"
                                            )
                                            storeViewModel.saveChat(currentChat)
                                            Log.d("MyTag", "Данные сохранены в firestore")
                                            text = ""
                                        } catch (e: Exception) {
                                            Log.d("MyLog", "Ошибка словлена")
                                        }
                                    }
                                    text = "Ожидайте..."
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
}