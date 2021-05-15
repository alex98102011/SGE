package com.example.mysge

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONArray
import org.json.JSONObject


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnReg: Button=findViewById(R.id.btnRegistro)
        val btnAcc: Button=findViewById(R.id.btnAcceder)
        val noCuen: EditText=findViewById(R.id.editTextNoCuenta)
        val cont:   EditText=findViewById(R.id.editTextTextPass)
        var mijson = resources.getString(R.string.mi_json)
        println(mijson)
        val nuevoAlumno= JSONObject()
        nuevoAlumno.put("nombre","Joaquin")
        nuevoAlumno.put("no_cuenta","420047085")
        nuevoAlumno.put("carrera","Pedagogia")
        var jsonObject=JSONObject(mijson)
        val array= jsonObject.getJSONArray("alumnos")
        for(i in 0 until array.length()){
            println(array[i].toString())
        }
        array.put(nuevoAlumno)
        jsonObject= JSONObject()
        jsonObject.put("alumnos",array)
        println(jsonObject.toString())

        val lista = jsonObject.optJSONArray("alumnos")
        val cambio= lista.getJSONObject(0)
        cambio.put("nombre","Marcos")
        cambio.put("no_cuenta","420047058")
        cambio.put("carrera","Ingenieria")
        println(jsonObject.toString(1))

        btnReg.setOnClickListener {
        val intent= Intent(this,Registro::class.java)
            startActivity(intent)
        }
      

    }




}