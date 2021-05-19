package com.example.mysge

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mysge.utils.AdminBD
import org.json.JSONArray
import org.json.JSONObject

class InscripcionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inscripcion)


        var stringBD = intent.getStringExtra("bd")
        if(stringBD == null) {
            stringBD = resources.getString(R.string.jsonAlumnos)
        }

        val stringAlumno = intent.getStringExtra("alumno")

        val bd = JSONObject(stringBD)

        // Se obtiene el semestre
        val jsonAlumno = JSONObject(stringAlumno)
        val strSemestre = jsonAlumno.getString("semestre")



        // Se verifica si tiene kardex
        var jsonAlumnoMaterias = JSONArray()
        if( ! jsonAlumno.has("kardex")) {
            val admin = AdminBD()
            jsonAlumnoMaterias = admin.generaCalificaciones(stringBD, strSemestre)
        }
    }
}