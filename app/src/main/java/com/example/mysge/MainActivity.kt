package com.example.mysge

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONObject


class MainActivity : AppCompatActivity() {
    lateinit var editNoControl : EditText
    lateinit var editPass : EditText
    lateinit var btnAcceder : Button
    lateinit var btnRegistrar : Button
    lateinit var btnInscripcion: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editNoControl = findViewById<EditText>(R.id.editNoControl)
        editPass = findViewById<EditText>(R.id.editContrasenia)
        btnAcceder = findViewById<Button>(R.id.btnAcceder)
        btnRegistrar = findViewById<Button>(R.id.btnRegistrar)

        val miJson = resources.getString(R.string.jsonAlumnos)

        val jsonAlumnos = JSONObject(miJson)

        val arrayAlumnos = jsonAlumnos.getJSONArray("alumnos")

        btnAcceder.setOnClickListener {
            val noControl = editNoControl.text.toString()
            val pass = editPass.text.toString()

            var encontrado = false
            for(i in 0..(arrayAlumnos.length()-1)) {
                println(arrayAlumnos[i].toString())

                val jsonStudent = arrayAlumnos.getJSONObject(i)
                if(jsonStudent.getString("no_control").trim() == noControl.trim()) {
                    encontrado = true
                    if(jsonStudent.getString("contrasenia").trim() == pass.trim()) {
                        Toast.makeText(this,"Encontrado", Toast.LENGTH_LONG).show()

                        val intent = Intent(this,MenuActivity::class.java)
                        intent.putExtra("alumno",jsonStudent.toString())
                        intent.putExtra("bd", miJson)
                        startActivity(intent)
                        val intent2= Intent(this,DatosActivity::class.java)
                        intent2.putExtra("alumno",jsonStudent.toString())
                        finish()
                    } else {
                        Toast.makeText(this,"Contrase??a incorrecta", Toast.LENGTH_LONG).show()
                    }
                }
            }
            if(!encontrado) {
                Toast.makeText(this, "No existe el registro", Toast.LENGTH_LONG).show()
            }
        }

        btnRegistrar.setOnClickListener {
            val intent = Intent(this,RegisterActivity::class.java)
            intent.putExtra("bd", miJson)
            startActivity(intent)
            finish()
        }

    }
}