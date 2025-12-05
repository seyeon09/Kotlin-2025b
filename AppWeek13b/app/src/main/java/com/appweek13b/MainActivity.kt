package com.appweek13b

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LazyColumn (
                modifier = Modifier
                    .background(color = Color.Cyan)
                    .fillMaxWidth()
            ){
                items(90){
                    index -> Text("코틀린 ${index + 1}")
                }
            }
        }

//        setContent {
//            val scrollstate = rememberScrollState()
//            // 컴포즈함수?
//            //  열 -> xml로 치면 리니어 레이아웃의 버티컬 같은 효과 / row는 horizontal // 아래로 쭉 일자로 생김
//            Column(
//                modifier = Modifier
//                    .background(color = Color.Blue)
//                    .fillMaxWidth()
//                    .verticalScroll(scrollstate)
//            ){
//                for(i in 1..90){ // 1..90
//                    Text("코틀린 $i")
//                }
//            }
//        }

    }
}

