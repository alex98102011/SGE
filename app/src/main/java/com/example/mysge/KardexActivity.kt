package com.example.mysge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONObject

class KardexActivity : AppCompatActivity() {
    lateinit var recyclerKardex: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kardex)

        var stringBD = intent.getStringExtra("bd")
        if (stringBD == null) {
            stringBD = resources.getString(R.string.jsonAlumnos)
        }

        val stringAlumno = intent.getStringExtra("alumno")

        // Se obtiene el semestre
        val jsonAlumno = JSONObject(stringAlumno)
        val strSemestre = jsonAlumno.getString("semestre")

        val admin = AdminBD()
        val jsonAlumnoMaterias = admin.generaCalificaciones(stringBD, strSemestre)

        if (jsonAlumnoMaterias.length() > 0) {
            // Colocamos el resultado en la UI
            recyclerKardex = findViewById(R.id.recyclerKardex)
            recyclerKardex.adapter = RecyclerKardexAdapter(this, R.layout.row_kardex, jsonAlumnoMaterias)
            recyclerKardex.layoutManager = LinearLayoutManager(this)
        } else {
            Toast.makeText(this, "Datos incorrectos", Toast.LENGTH_LONG).show()
        }

    }


}