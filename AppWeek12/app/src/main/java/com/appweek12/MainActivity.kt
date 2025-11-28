package com.appweek12

import android.graphics.Color
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.appweek12.databinding.ActivityMainBinding
import kotlinx.coroutines.launch // 코틀린x의 코루틴
class MainActivity : AppCompatActivity() {

    // 바인딩 변수 선언, 프론트엔드 연결시 사용
    private lateinit var binding : ActivityMainBinding //나중에 할당

    // 카운트 변수 (MVVM 사용 시 주석 처리되거나 삭제됨)
    // private var count = 0 // 이건 바로 할당

    // 뷰 모델 변수 만들고 (바인딩 변수 만들듯이)
    private val viewModel: CounterViewModel by viewModels()
    // by viewModels()는 위임할 때 쓰는 것
    // 저 변수(viewModel)에 액티비티 프로그램에서 늦게 실행되는 것을 위임
    // lateinit은 널포인트익셉션 될 가능성이 있지만
    // by로 viewModels()를 하면 널 포인트익셉션이 안됨 늦게 생성해주는데도

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 바인딩 객체 만들기
        binding = ActivityMainBinding.inflate(layoutInflater)

        // r.layout.액티비티메인을 이렇게 쓰는 것
        setContentView(binding.root)

        // 액티비티 생명주기 구성 변경 발생 막는 법, 간단하고 가벼운 ui 상태 저장
        // 이전의 상태를 저장해놨다가 다시 불러오는 것
        // 이걸 불러오려면 onSaveInstanceState 함수를 오버라이드해서 사용할 수 있음
        //if(savedInstanceState != null)
        //    count=savedInstanceState.getInt("count",0)

        // 리스너 호출 함수
        setupListeners()
        setupObservers() // 옵저버 설정 함수를 호출해야 상태 변화를 관찰함
        // updateCountDisplay() // 다시 불러들일때 업데이트해줘야 화면에 바뀌게 됨


    } // <--- 여기가 `onCreate` 메서드를 닫는 괄호입니다! (수정 포인트)
        // 새로운 함수 다 주석처리 한 코드대신 만드는 것
        fun setupObservers(){
            // StateFlow 방식으로 변경
            // LiveData 관찰 방식 (주석 처리)
//            viewModel.count.observe(this){
//                count -> binding.textViewCount.text = count.toString()
//
//            // 숫자에 따라 색깔 바뀌게 if여도 when이어도 됨
//            when{
//                count>0 -> binding.textViewCount.setTextColor(Color.BLUE)
//                count<0 -> binding.textViewCount.setTextColor(Color.RED)
//                else -> binding.textViewCount.setTextColor(Color.BLACK)
//            }
//            }

            // StateFlow 관찰 방식 (코루틴 사용)
            lifecycleScope.launch{
                // repeatOnLifecycle을 사용하여 View의 생명주기가 STARTED일 때만 collect를 활성화하여 메모리 누수를 방지함
                repeatOnLifecycle(Lifecycle.State.STARTED){
                    viewModel.count.collect{
                            count -> binding.textViewCount.text = count.toString()

                        // 숫자에 따라 색깔 바뀌게 when 문 사용
                        when{
                            count > 0 -> binding.textViewCount.setTextColor(Color.BLUE)
                            count < 0 -> binding.textViewCount.setTextColor(Color.RED)
                            else -> binding.textViewCount.setTextColor(Color.BLACK)
                        }
                    }
                }
            }
        } // <--- setupObservers 로컬 함수 종료

//    override fun onSaveInstanceState(outState: Bundle) {
//        super.onSaveInstanceState(outState)
//        outState.putInt("count",count) // putInt 정수로 집어넣기
//    }


    // private fun은 이제 `MainActivity` 클래스의 멤버 함수로 올바르게 위치합니다.
    private fun setupListeners() {

        binding.buttonPlus.setOnClickListener{
//            count++ // count
//            // 실제 화면에 보이게 만드는 함수
//           updateCountDisplay()

            viewModel.increment() // ViewModel에 상태 변경 요청
        }

        binding.buttonMinus.setOnClickListener{
//            count-- // count 감소
//            // 실제 화면에 보이게 만드는 함수
//            updateCountDisplay()
            viewModel.decrement() // ViewModel에 상태 변경 요청
        }

        binding.buttonReset.setOnClickListener{
//            count = 0 // count 리셋
//            // 실제 화면에 보이게 만드는 함수
//            updateCountDisplay()
            viewModel.reset() // ViewModel에 상태 변경 요청
        }

        binding.buttonPlus10.setOnClickListener{
//            count = count + 10 // count 10 증가
//            // 실제 화면에 보이게 만드는 함수
//            updateCountDisplay()
            viewModel.incrementBy10() // ViewModel에 상태 변경 요청
        }
    }

    // 실제 프론트엔드 화면에 적용시켜주는 함수 (LiveData/StateFlow 사용으로 불필요하여 주석 처리)
//    private fun updateCountDisplay(){
//        binding.textViewCount.text=count.toString()
//
//        // 숫자에 따라 색깔 바뀌게 if여도 when이어도 됨
//        when{
//            count>0 -> binding.textViewCount.setTextColor(Color.BLUE)
//            count<0 -> binding.textViewCount.setTextColor(Color.RED)
//            else -> binding.textViewCount.setTextColor(Color.BLACK)
//        }
//    }
} // <--- MainActivity 클래스 종료