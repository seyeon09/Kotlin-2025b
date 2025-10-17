package com.appweek06
import java.util.*

data class Student( // 3개의 매개변수를 가진 생성자
    val name: String,
    val id: String = UUID.randomUUID().toString(), // 랜덤하게 만듬
    val addedDate: Date = Date()
)

data class CartItem( // 5개의 변수
    val name: String,
    var quantity: Int = 1, // 초기값 개수 1
    val price: Double,
    val id: String = UUID.randomUUID().toString(), // 랜덤으로
    val addedDate: Date = Date()
) { // 클래스 시작
    fun getTotalPrice(): Double = quantity * price // 메소드
    // 코틀린 문법 숏폼으로 만든 것 = 수량 * 가격 리턴해줌

    override fun toString(): String { // 오브젝트의 투스트링 오버라이드
        return "$name (x$quantity) - $%.2f".format(getTotalPrice())
    } // 이름 x 수량 소수점 둘째자리까지의 가격
}

enum class AppMode(val displayName: String) { //enum 열거체를 하나 만든 것
    STUDENT_LIST("Student List"),
    SHOPPING_CART("Shopping Cart"),
}
