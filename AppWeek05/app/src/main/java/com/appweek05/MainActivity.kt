package com.appweek05

import android.os.Bundle
import android.util.Log
import android.widget.Adapter
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    // 변수 선언 UI Component , 위젯
    private lateinit var buttonAdd : Button // lateinit 나중에 바인딩 하겠다는 뜻
    private lateinit var buttonClear : Button
    private lateinit var editTextStudent : EditText
    private lateinit var textViewCount : TextView
    private lateinit var listView : ListView

    //collection
    private lateinit var studentList : ArrayList<String>
    private lateinit var adapter: ArrayAdapter<String>


    companion object{
        private const val TAG = "KotlinWeek06App"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        Log.d(TAG,"onCreate : AppWeek05 started")
        // 함수 선언
        setupViews()
        setupListViews()
        setupListeners()
        addInitialData()
    }

    private fun setupViews(){
        // 위의 위젯 바인딩 하기
        listView = findViewById(R.id.listViewStudents)
        editTextStudent = findViewById(R.id.editTextStudent)
        buttonClear = findViewById(R.id.buttonClear)
        buttonAdd = findViewById(R.id.buttonAdd)
        textViewCount = findViewById(R.id.textViewCount)

        studentList = ArrayList()
        Log.d(TAG,"Views initialized")
    }
    private fun setupListViews(){

        //어댑터 생성 및 arraylist와 연결
        adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1, studentList)
        listView.adapter = adapter
        Log.d(TAG,"ListView and Adapter setup completed")
    }
    private fun setupListeners(){
        buttonAdd.setOnClickListener{
            addStudent()
        }
        buttonClear.setOnClickListener {
            claerAllStudents()
        }
        // 람다함수 -> 이름 부를일이 없음, 일회용 젓가락 느낌
        //position -> 리스트의 인덱스 느낌
        // 안 쓰는 매개변수들은 _로 처리
        // 팝업 띄우기 -> 토스트 사용
        listView.setOnItemLongClickListener { _, _, position, _ -> removeStudent(position)
            true
        }
        listView.setOnItemClickListener { _, _, position, _ ->
            val studentName = studentList[position]
            Toast.makeText(
                this,
                "Selected: $studentName (Position: ${position+1}",
                Toast.LENGTH_SHORT).show()
            Log.d(TAG,"Selected : ${studentName} (Position : ${position}")
        }
        Log.d(TAG,"Event listener setup complted")
    }

    private fun addStudent(){
        val studentName = editTextStudent.text.toString().trim()

        if (studentName.isEmpty()){ //만약 칸이 비어있다면
            Toast.makeText(this,"please enter a student name",Toast.LENGTH_SHORT).show()
            Log.d(TAG,"Attempted to add empty student name")
            return
        }
        if (studentList.contains(studentName)){ // 만약 이미 있는 값이라면 ( 중복값 )
            Toast.makeText(this,"Student '$studentName' already exists",Toast.LENGTH_SHORT).show()
            Log.d(TAG,"Attempted to add duplicat student '$studentName'")
            return
        }

        // if문 다 지나고 이름 이제 추가 !!
        studentList.add(studentName)
        adapter.notifyDataSetChanged() // 내용 바뀌었다고 알려줌
        editTextStudent.text.clear() // 지워야 또 다음 학생 입력
        updateStudentCount()
        Toast.makeText(this,"Added : $studentName", Toast.LENGTH_SHORT).show()
        Log.d(TAG,"Added student $studentName(total : ${studentList.size})") // 전체 학생 수도 보여줌
    }
    private fun claerAllStudents(){
        if (studentList.isEmpty()){
            Toast.makeText(this,"List is already empty", Toast.LENGTH_SHORT).show()
            return
        }
        var count = studentList.size
        studentList.clear() //진짜로 삭제하는 과정
        // 변동사항이 있으면 항상 어댑터한테 알려서 화면을 바꿔줘야함
        adapter.notifyDataSetChanged() // listview에 반영
        updateStudentCount()
        Toast.makeText(this,"Cleared all $count student", Toast.LENGTH_SHORT).show()
        Log.d(TAG,"Cleared all student (Total cleared : $count)")
    }
    private fun removeStudent(position: Int){
        if (position>=0 && position < studentList.size){ //인덱스의 범위가 정상일때만 동작
            val removedStudent = studentList.removeAt(position) //removeAt 리스트로 부터 삭제된 원소를 리턴
            // 삭제한 학생 이름 저장한 것
            // 어댑터한테 알려주기
            adapter.notifyDataSetChanged()
            updateStudentCount()
            Toast.makeText(
                this,
                "Removed: $removedStudent",
                Toast.LENGTH_SHORT).show()
            Log.d(TAG,"Removed student : $removedStudent (Remaining : ${studentList.size}")
        }
    }

    private fun updateStudentCount(){
        textViewCount.text="Total Student : ${studentList.size}"
    }

    private fun addInitialData(){
        val initialStudent = listOf("Kim","lee","park")
        studentList.addAll(initialStudent)
        adapter.notifyDataSetChanged()
        updateStudentCount()
        Log.d(TAG,"Added initial data : $initialStudent")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG,"onResume : Current Student count  ${studentList.size}")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG,"onPause : Saving state with  ${studentList.size}")
    }

}