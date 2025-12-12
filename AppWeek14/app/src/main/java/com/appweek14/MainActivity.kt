package com.appweek14

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.appweek14.data.RetrofitClient
import com.appweek14.data.User
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HelloRetrofitApp()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HelloRetrofitApp() {
    val scope = rememberCoroutineScope()
    var users by remember { mutableStateOf<List<User>>(emptyList()) }
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }
    
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Hello Retrofit") }
            )
        }
    ) { innerPadding ->
        Column( // 세로 방향
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize()
        ) { // 버튼 누르면 코루틴 돌아감
            Button(
                onClick = {
                    scope.launch {
                        isLoading = true
                        errorMessage = ""
                        try {
                            val result = RetrofitClient.api.getUsers()
                            users = result
                        } catch (e: Exception) {
                            errorMessage = "에러: ${e.message}"
                        } finally {
                            isLoading = false
                        }
                    }
                }
            ) {
                Text("Fetch Users")
            }
            // 공간 채우는 것
            Spacer(modifier = Modifier.height(16.dp))
            // true, false 로 if문 실행
            if (isLoading) {
                CircularProgressIndicator()
            } else if (errorMessage.isNotEmpty()) {
                Text(text = errorMessage, color = MaterialTheme.colorScheme.error)
            } else {
                LazyColumn { // 리사이클러뷰 대체하는 것 백만건이아도 보여지는 값만 가져옴, 메모리 로딩 시간 단축
                    items(users) { user ->
                        UserItem(user)
                    }
                }
            }
        }
    }
}

@Composable
fun UserItem(user: User) {
    Column(modifier = Modifier.padding(vertical = 4.dp)) { //세로방향으로 배치
        Text(text = "${user.id}. ${user.name}")
        Text(text = user.email, style = MaterialTheme.typography.bodySmall)
    }
}