package com.example.mysge


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MenuActivity : AppCompatActivity() {
    lateinit var btnKardex : Button
    lateinit var btnHorario : Button
    lateinit var btnReticula : Button
    lateinit var btnPersonales : Button
    lateinit var btnInscripcion: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        btnKardex = findViewById(R.id.btnKardex)
        btnHorario = findViewById(R.id.btnHorario)
        btnReticula = findViewById(R.id.btnReticula)
        btnPersonales = findViewById(R.id.btnPersonales)
        btnInscripcion=findViewById(R.id.btnInscripcion)

        var stringBD = intent.getStringExtra("bd")
        if(stringBD == null ) {
            stringBD = resources.getString(R.string.jsonAlumnos)
        }

        val stringAlumno = intent.getStringExtra("alumno")

        println("Alumno:")
        println(stringAlumno)

        println("BD:")
        println(stringBD)

        btnKardex.setOnClickListener {
            val intent = Intent(this,KardexActivity::class.java)
            intent.putExtra("bd",stringBD)
            intent.putExtra("alumno",stringAlumno)
            startActivityForResult(intent, 1)
        }

        btnReticula.setOnClickListener{
            val intent= Intent(this,AcademicAdvanceActivity::class.java)
            intent.putExtra("bd",stringBD)
            intent.putExtra("alumno",stringAlumno)
            startActivityForResult(intent,1)
        }
        btnHorario.setOnClickListener {
            val intent =Intent(this,ScheduleActivity::class.java)
            intent.putExtra("bd",stringBD)
            intent.putExtra("alumno",stringAlumno)
            startActivityForResult(intent,1)

        }
        btnInscripcion.setOnClickListener{
            val  intent=Intent(this,InscripcionActivity::class.java)
            intent.putExtra("bd",stringBD)
            intent.putExtra("alumno",stringAlumno)
            startActivityForResult(intent,1)
        }

        btnPersonales.setOnClickListener {
            val  intent=Intent(this,DatosActivity::class.java)
            intent.putExtra("bd",stringBD)
            intent.putExtra("alumno",stringAlumno)
            startActivityForResult(intent,1)
         }

    }

}