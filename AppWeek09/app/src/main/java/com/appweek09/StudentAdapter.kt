package com.appweek09

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.appweek09.data.Student
import com.appweek09.databinding.ItemStudentBinding

class StudentAdapter (
    private val studentList: List<Student>,
    private val onItemClick:(Student, Int) -> Unit, // 람다식 입력만 있고 리턴은 없음
    private val onItemLongClick: (Int) ->Unit

)   // 여기에 상속
    : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

        // 중첩 클래스 만들기
        // 클래스 안에 있어서 외부에서 접근을 못함, 외부에서 객체를 생성 후 안으로 접근 가능
        // 보안에 좋지만 접근이 까다로움
        inner class StudentViewHolder(
            private val binding: ItemStudentBinding
        ) // 여기에 상송
            : RecyclerView.ViewHolder(binding.root) {
            fun bind(student: Student){
                binding.apply { // findviewid 하던 걸 inner 클래스 안에서
                    textViewName.text=student.name
                    textViewDept.text=student.department
                    textViewGrade.text=student.grade
                    textViewEmail.text=student.email

                    root.setOnClickListener{
                        onItemClick(student,adapterPosition); // 위에서 람다식으로 선언한 함수
                    }

                    root.setOnLongClickListener {
                        onItemLongClick(adapterPosition) // 위에서 람다식으로 선언한 함수
                        true
                    }
                }
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val binding = ItemStudentBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return StudentViewHolder(binding)
    }


    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.bind(studentList[position])
    }


    override fun getItemCount() = studentList.size

}