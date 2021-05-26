package com.example.mysge

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.example.mysge.utils.AdminBD
import org.json.JSONObject

class ScheduleActivity : AppCompatActivity() {
    lateinit var linearLunes : LinearLayout
    lateinit var linearMartes : LinearLayout
    lateinit var linearMiercoles : LinearLayout
    lateinit var linearJueves : LinearLayout
    lateinit var linearViernes : LinearLayout
    lateinit var prueba:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule)
        prueba=findViewById<TextView>(R.id.prueba)
        linearLunes = findViewById(R.id.linearScheduleMonday)
        linearMartes = findViewById(R.id.linearScheduleTuesday)
        linearMiercoles = findViewById(R.id.linearScheduleWednesday)
        linearJueves = findViewById(R.id.linearScheduleThursday)
        linearViernes = findViewById(R.id.linearScheduleFriday)

        var stringBD = intent.getStringExtra("bd")
        if(stringBD == null) {
            stringBD = resources.getString(R.string.jsonAlumnos)
        }

        val stringAlumno = intent.getStringExtra("alumno")
        val carga=intent.getStringExtra("seleccion")
        //val jsonInscripcion=JSONObject(carga)
        //println("Datos contenidos:")
        println(carga)
        //prueba.text=jsonInscripcion.getString("materia")
        // De String a JSON
        val bd = JSONObject(stringBD)
        val jsonAlumno = JSONObject(stringAlumno)

        // Se obtiene el semestre
        val strSemestre = jsonAlumno.getString("semestre")

        val admin = AdminBD()
        var jsonAlumnoHorario = admin.generaHorario(stringBD, strSemestre)

        for(i in 0..jsonAlumnoHorario.length()-1) {
            val jsonDia = jsonAlumnoHorario.getJSONObject(i)

            val linear = LinearLayout(this)
            linear.orientation = LinearLayout.VERTICAL
            linear.gravity = Gravity.CENTER
            linear.layoutParams = ViewGroup.LayoutParams(200,200)
            linear.setBackgroundColor(Color.parseColor("#00ffcc"))

            val tvMateria = TextView(this)
            tvMateria.setTextColor(Color.BLACK)
            tvMateria.text = jsonDia.getString("materia")
            linear.addView(tvMateria)

            val tvHora = TextView(this)
            tvHora.setTextColor(Color.BLACK)
            var hora = jsonDia.getString("hora")
            hora = hora.replace("00",":00")
            tvHora.text = hora
            linear.addView(tvHora)

            when(jsonDia.getString("dia")) {
                "Lunes" -> linearLunes.addView(linear)
                "Martes" -> linearMartes.addView(linear)
                "Miercoles" -> linearMiercoles.addView(linear)
                "Jueves" -> linearJueves.addView(linear)
                else -> linearViernes.addView(linear)
            }
        }
    }

}