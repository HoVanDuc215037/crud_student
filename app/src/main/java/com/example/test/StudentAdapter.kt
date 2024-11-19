package com.example.test

import android.app.Dialog
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StudentAdapter(
  var students: MutableList<StudentModel>,
) : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

  class StudentViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val textStudentName: TextView = itemView.findViewById(R.id.text_student_name)
    val textStudentId: TextView = itemView.findViewById(R.id.text_student_id)
    val imageEdit: ImageView = itemView.findViewById(R.id.image_edit)
    val imageRemove: ImageView = itemView.findViewById(R.id.image_remove)
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
    val itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout_student_item,
       parent, false)
    return StudentViewHolder(itemView)
  }

  override fun getItemCount(): Int = students.size

  fun editStudentInPlace(place: Int, student: StudentModel){
    students.set(place, student)
    notifyDataSetChanged()
  }

  fun addStudentToFront(student: StudentModel) {
    students.add(0, student)  // Thêm sinh viên vào đầu danh sách
    notifyItemInserted(0)  // Thông báo cho adapter rằng có phần tử mới được thêm
  }

  override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
    val student = students[position]

    holder.textStudentName.text = student.studentName
    holder.textStudentId.text = student.studentId
    holder.imageEdit.setOnClickListener {
      Log.w("clicked_student", "Clicked Student: $student" )
    }
  }

  companion object
}