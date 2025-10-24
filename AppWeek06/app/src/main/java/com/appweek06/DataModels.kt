package com.appweek06
import android.app.ActivityManager.TaskDescription
import android.graphics.Color
import java.util.*

data class Student( // 3개의 매개변수를 가진 생성자
    val name: String,
    val id: String = UUID.randomUUID().toString(), // 랜덤하게 만듬
    val addedDate: Date = Date()
)

data class Task( // 5개의 변수
    val title: String,
    var description: String = "" , // 초기값 개수 1
    var isCompleted: Boolean=false, //true,false가 바뀌어야하니까 var로
    val priority: TaskPriority,
    val dueDate: Date? = null,
    val id: String = UUID.randomUUID().toString(), // 랜덤으로
    val createDate: Date = Date()
) { // 클래스 시작

    override fun toString(): String { // 오브젝트의 투스트링 오버라이드
        // 지역 변수 선언, 상태 정보 저장하는 변수
        val status = if(isCompleted) "v" else "O"
        val priorityIcon = when(priority){
            TaskPriority.HIGH ->"!!!"
            TaskPriority.MEDIUM ->"!!"
            TaskPriority.LOW->"!"
        }
        return "$status $priorityIcon $title"
    }
}

enum class TaskPriority(val displayName: String, val color: Int) {
    HIGH("High",Color.RED),
    MEDIUM("Medium",Color.BLUE),
    LOW("Low",Color.GREEN)

}

enum class AppMode(val displayName: String) { //enum 열거체를 하나 만든 것
    STUDENT_LIST("Student List"),
    TASK_MANAGER("Task Manager"),
}