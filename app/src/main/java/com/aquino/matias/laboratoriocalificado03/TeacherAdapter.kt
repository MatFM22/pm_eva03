package com.aquino.matias.laboratoriocalificado03

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class TeacherAdapter(private val context: Context, private val teachers: List<Teacher>) :
    RecyclerView.Adapter<TeacherAdapter.TeacherViewHolder>() {

    class TeacherViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgTeacher: ImageView = view.findViewById(R.id.imgTeacher)
        val tvName: TextView = view.findViewById(R.id.tvName)
        val tvLastName: TextView = view.findViewById(R.id.tvLastName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeacherViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_teacher_card, parent, false)
        return TeacherViewHolder(view)
    }

    override fun onBindViewHolder(holder: TeacherViewHolder, position: Int) {
        val teacher = teachers[position]
        holder.tvName.text = teacher.name
        holder.tvLastName.text = teacher.last_name
        Glide.with(context).load(teacher.imageUrl).into(holder.imgTeacher)
    }

    override fun getItemCount(): Int = teachers.size
}