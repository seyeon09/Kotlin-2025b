package com.appweek12

import android.graphics.Color
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.appweek12.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // 바인딩 변수 선언, 프론트엔드 연결시 사용
    private lateinit var binding : ActivityMainBinding //나중에 할당

    // 카운트 변수
    private var count = 0 // 이건 바로 할당


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 바인딩 객체 만들기
        binding = ActivityMainBinding.inflate(layoutInflater)

        // r.layout.액티비티메인을 이렇게 쓰는 것
        setContentView(binding.root)

        // 리스너 호출 함수
        setupListeners()
    }

    private fun setupListeners() {

        binding.buttonPlus.setOnClickListener{
            count++ // count
            // 실제 화면에 보이게 만드는 함수
           updateCountDisplay()
        }

        binding.buttonMinus.setOnClickListener{
            count-- // count 감소
            // 실제 화면에 보이게 만드는 함수
            updateCountDisplay()
        }

        binding.buttonReset.setOnClickListener{
            count = 0 // count 리셋
            // 실제 화면에 보이게 만드는 함수
            updateCountDisplay()
        }

        binding.buttonPlus10.setOnClickListener{
            count = count + 10 // count 10 증가
            // 실제 화면에 보이게 만드는 함수
            updateCountDisplay()
        }
    }

    private fun updateCountDisplay(){
        binding.textViewCount.text=count.toString()

        // 숫자에 따라 색깔 바뀌게 if여도 when이어도 됨
        when{
            count>0 -> binding.textViewCount.setTextColor(Color.BLUE)
            count<0 -> binding.textViewCount.setTextColor(Color.RED)
            else -> binding.textViewCount.setTextColor(Color.BLACK)
        }
    }
}