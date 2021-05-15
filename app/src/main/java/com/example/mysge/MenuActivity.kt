package com.example.mysge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MenuActivity : AppCompatActivity() {
    lateinit var btnKardex : Button
    lateinit var btnHorario : Button
    lateinit var btnReticula : Button
    lateinit var btnPersonales : Button

    lateinit var stringBD : String
    lateinit var stringAlumno : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
    }
}