package com.example.mysge

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.example.mysge.utils.AdminBD
import org.json.JSONArray
import org.json.JSONObject

open class ScheduleActivity : AppCompatActivity() {
lateinit var tvMLunes: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule)
        tvMLunes=findViewById(R.id.Mlunes)
        val carga = intent.getStringExtra("seleccion")
        println(carga)
        val jsonmaterias=JSONArray(carga)
        val registro=jsonmaterias.getJSONObject(0)
        val asignatura=registro.getString("materia")
        tvMLunes.text=asignatura.toString()



    }

}