package com.aquino.matias.laboratoriocalificado03

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.aquino.matias.laboratoriocalificado03.databinding.ActivityEjercicio01Binding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Ejercicio01 : AppCompatActivity() {

    private lateinit var binding: ActivityEjercicio01Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEjercicio01Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar RecyclerView
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        // Llamar a la API
        fetchTeachers()
    }

    private fun fetchTeachers() {
        RetrofitClient.instance.getTeachers().enqueue(object : Callback<TeacherResponse> {
            override fun onResponse(call: Call<TeacherResponse>, response: Response<TeacherResponse>) {
                if (response.isSuccessful) {
                    val teachers = response.body()?.teachers ?: emptyList()
                    val adapter = TeacherAdapter(this@Ejercicio01, teachers)
                    binding.recyclerView.adapter = adapter
                } else {
                    Log.e("API_ERROR", "Error en la respuesta: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<TeacherResponse>, t: Throwable) {
                Log.e("API_ERROR", "Error al obtener los datos: ${t.message}")
            }
        })
    }
}