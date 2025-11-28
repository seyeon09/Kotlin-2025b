package com.appweek12

//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
// STATEFlow  버전
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CounterViewModel : ViewModel() {
    private val _count = MutableStateFlow(0)
    val count: StateFlow<Int> = _count.asStateFlow()
    //asStateFlow() 카운터 객체는 누구나 바꿀 수 있음 -> as로 하면 _count 객체를 읽기전용으로 바꿔서 수정못하게

    fun increment(){
       // _count.value = (_count.value ?: 0) + 1
        // StateFlow는 널 값 체크를 하지 않아서 엘비스 연산자 사용할 필요 없음
        _count.value = (_count.value) + 1
    }
    fun decrement(){
        //_count.value = (_count.value ?: 0) - 1
        _count.value = (_count.value) - 1
    }
    fun reset(){
       // _count.value = 0
        // 그대로 유지
        _count.value = 0
    }
    fun incrementBy10(){
        //_count.value = (_count.value ?: 0) + 10
        _count.value = (_count.value) + 10
    }
}