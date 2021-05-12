package com.example.mysge

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import org.json.JSONObject

class Registro : AppCompatActivity() {
    lateinit var editNControl:EditText
    lateinit var editNombre: EditText
    lateinit var editCarrera: EditText
    lateinit var editSemestre: EditText
    lateinit var editPass: EditText
    lateinit var btnRegistro: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        editNControl=findViewById(R.id.editTextNoCuenta)
        editNombre=findViewById(R.id.editNombre)
        editCarrera=findViewById(R.id.editCarrera)
        editSemestre=findViewById(R.id.editSemestre)
        editPass=findViewById(R.id.editPass)
        btnRegistro=findViewById(R.id.btnRegistro)


        val stringBD = intent.getStringExtra("bd")

        var jsonBD = JSONObject(stringBD)

        btnRegistro.setOnClickListener {
            val json=JSONObject()
            json.put("no_control", editNControl.text.toString())
            json.put("nombre", editNombre.text.toString())
            json.put("carrera", editCarrera.text.toString())
            json.put("semestre", editSemestre.text.toString().toInt())
            json.put("password", editPass.text.toString())
            val alumnos = jsonBD.getJSONArray("alumnos")
            alumnos.put(json)

            jsonBD = JSONObject()
            jsonBD.put("alumnos", alumnos)

            val intent = Intent(this,   Sesion::class.java)
            intent.putExtra("alumno", json.toString())
            intent.putExtra("bd", jsonBD.toString())
            startActivity(intent)
            finish()





        }

    }
}