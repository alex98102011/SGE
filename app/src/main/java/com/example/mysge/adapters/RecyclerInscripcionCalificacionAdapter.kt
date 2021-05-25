package com.example.mysge.adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.mysge.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.json.JSONArray
import org.json.JSONObject
import android.os.Bundle
import android.content.Intent
import android.provider.Settings.Global.getString

class RecyclerInscripcionCalificacionAdapter(val c: Context, val r: Int, val calificaciones: JSONArray) : RecyclerView.Adapter<RecyclerInscripcionCalificacionAdapter.CalificacionInscripcionVH>()  {


    inner class CalificacionInscripcionVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(json : JSONObject) {
            val tvMateria = itemView.findViewById<TextView>(R.id.tvRowMateriaInscripcion)
            val tvCalificacion = itemView.findViewById<TextView>(R.id.tvRowCalificacionInscripcion)
            val cardView = itemView.findViewById<CardView>(R.id.cardRowMateriasInscripcion)
            val btnSelect=itemView.findViewById<FloatingActionButton>(R.id.btnSeleccionarMateia)
            val btnPr1=itemView.findViewById<RadioButton>(R.id.Profesor1)
            val btnPr2=itemView.findViewById<RadioButton>(R.id.Profesor2)
            tvMateria.text = json.getString("materia")
            if( ! json.getString("calificacion").equals("-")) {
                tvCalificacion.text = json.getString("calificacion")
                if (json.getString("calificacion").toInt() >= 70) {
                    cardView.setCardBackgroundColor(Color.parseColor("#00cc00"))
                    cardView.visibility=View.INVISIBLE
                    btnSelect.visibility=View.INVISIBLE
                    btnPr1.visibility=View.INVISIBLE
                    btnPr2.visibility=View.INVISIBLE

                } else {
                    cardView.setCardBackgroundColor(Color.BLUE)
                    tvCalificacion.text = "La materia debe ser seleccionada"

                }
            } else {
                cardView.setCardBackgroundColor(Color.BLUE)
                tvCalificacion.text = "Materia Posible a Seleccionar"
            }

            btnSelect.setOnClickListener {
                cardView.setCardBackgroundColor(Color.RED)
                tvCalificacion.text = "Cursando"

            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalificacionInscripcionVH {
        return CalificacionInscripcionVH( LayoutInflater.from(c).inflate(r,null) )

    }

    override fun onBindViewHolder(holder: CalificacionInscripcionVH, position: Int) {
        val json = calificaciones.getJSONObject(position)
        holder.bind(json)
    }

    override fun getItemCount(): Int {
        return calificaciones.length()
    }
}