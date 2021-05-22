package com.example.mysge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import org.json.JSONObject

class DatosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {



        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_datos)


        val texNom=findViewById<TextView>(R.id.textViewNombre)
        val texSem=findViewById<TextView>(R.id.TvSemestre)
        val textcontrol=findViewById<TextView>(R.id.textViewNoControl)
        val texcarrara=findViewById<TextView>(R.id.textViewCarrera)

        var stringBD = intent.getStringExtra("bd")
        if(stringBD == null) {
            stringBD = resources.getString(R.string.jsonAlumnos)
        }


        val stringAlumno = intent.getStringExtra("alumno")
        val bd = JSONObject(stringBD)
        val jsonAlumno = JSONObject(stringAlumno)
        val nombre=jsonAlumno.getString("nombre")
        val sem=jsonAlumno.getString("semestre")
        val control=jsonAlumno.getString("no_control")
        val carrera=jsonAlumno.getString("carrera")
        texNom.text=nombre.toString()
        texSem.text=sem.toString()
        texcarrara.text=carrera.toString()
        textcontrol.text=control.toString()


    }
}