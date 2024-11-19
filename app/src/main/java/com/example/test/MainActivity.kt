package com.example.test

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val students = mutableListOf(
            StudentModel("Nguyễn Văn An", "SV001"),
            StudentModel("Trần Thị Bảo", "SV002"),
            StudentModel("Lê Hoàng Cường", "SV003"),
            StudentModel("Phạm Thị Dung", "SV004"),
            StudentModel("Đỗ Minh Đức", "SV005"),
            StudentModel("Vũ Thị Hoa", "SV006"),
            StudentModel("Hoàng Văn Hải", "SV007"),
            StudentModel("Bùi Thị Hạnh", "SV008"),
            StudentModel("Đinh Văn Hùng", "SV009"),
            StudentModel("Nguyễn Thị Linh", "SV010"),
            StudentModel("Phạm Văn Long", "SV011"),
            StudentModel("Trần Thị Mai", "SV012"),
            StudentModel("Lê Thị Ngọc", "SV013"),
            StudentModel("Vũ Văn Nam", "SV014"),
            StudentModel("Hoàng Thị Phương", "SV015"),
            StudentModel("Đỗ Văn Quân", "SV016"),
            StudentModel("Nguyễn Thị Thu", "SV017"),
            StudentModel("Trần Văn Tài", "SV018"),
            StudentModel("Phạm Thị Tuyết", "SV019"),
            StudentModel("Lê Văn Vũ", "SV020")
        )

        val studentAdapter = StudentAdapter(students)

        fun showEditDialog(place: Int){
            var dialog = Dialog(this)
            dialog.setContentView(R.layout.edit_dialog_layout)
            val id = students[place].studentId
            val name = students[place].studentName
            val studentid = dialog.findViewById<EditText>(R.id.value_studentid)
            val studentname = dialog.findViewById<EditText>(R.id.value_studentname)
            studentid.setText(id.toString())
            studentname.setText(name)
            dialog.findViewById<Button>(R.id.button_save).setOnClickListener {
                val id = studentid.text.toString()
                val name = studentname.text.toString()
                val new_student = StudentModel(name,id)
                findViewById<RecyclerView>(R.id.recycler_view_students).scrollToPosition(place)
                dialog.dismiss()
            }
            dialog.findViewById<Button>(R.id.button_cancel).setOnClickListener {
                dialog.dismiss()
            }
            dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            dialog.show()
        }

        fun removeStudent(position: Int) {
            // Xóa sinh viên khỏi danh sách
            students.removeAt(position)
            studentAdapter.notifyItemRemoved(position)
        }

        findViewById<RecyclerView>(R.id.recycler_view_students).run {
            adapter = studentAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        fun addStudent(student: StudentModel){
            studentAdapter.addStudentToFront(student)
        }
        fun editStudent(place: Int, newStudent: StudentModel) {
            studentAdapter.editStudentInPlace(place,newStudent)
        }
        fun showCustomDialog() {
            val dialog = Dialog(this)
            dialog.setContentView(R.layout.custom_dialog_layout)
            val editStudentId = dialog.findViewById<EditText>(R.id.edit_studentid)
            val editStudentName = dialog.findViewById<EditText>(R.id.edit_studentname)
            dialog.findViewById<Button>(R.id.button_ok).setOnClickListener {
                val id = editStudentId.text.toString()
                val name = editStudentName.text.toString()
                //Log.w("user input", "showCustomDialog: ", )
                val student = StudentModel(name,id)
                addStudent(student);
                findViewById<RecyclerView>(R.id.recycler_view_students).scrollToPosition(0)
                dialog.dismiss()
            }
            dialog.findViewById<Button>(R.id.button_cancel).setOnClickListener {
                dialog.dismiss()
            }
            dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            dialog.show()
        }


        findViewById<Button>(R.id.btn_add_new).setOnClickListener{
            showCustomDialog();
        }
    }
}
