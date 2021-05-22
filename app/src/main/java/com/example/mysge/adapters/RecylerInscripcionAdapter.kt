package com.example.mysge.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mysge.R
import org.json.JSONArray
import org.json.JSONObject

class RecylerInscripcionAdapter (val c: Context, val res:Int, val kardex: JSONArray, val reticula: JSONArray) : RecyclerView.Adapter<RecylerInscripcionAdapter.InscripcionVH>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecylerInscripcionAdapter.InscripcionVH {
        return InscripcionVH( LayoutInflater.from(c).inflate(res, null) )
    }

    override fun onBindViewHolder(holder: RecylerInscripcionAdapter.InscripcionVH, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return reticula.length()
    }

    inner class InscripcionVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(i: Int) {

            val jsonSemestre = reticula.getJSONObject(i)

            val tvSemestre = itemView.findViewById<TextView>(R.id.tvRowInscripcionSemestre)
            tvSemestre.text = "Semestre ${i + 1}"

            val jsonArray = JSONArray()
            for (s in 0..kardex.length() - 1) {
                val jsonSemestre = kardex.getJSONObject(s)

                if (jsonSemestre.getInt("semestre") == (i + 1)) {
                    jsonArray.put(jsonSemestre)
                }
            }

            if (jsonArray.length() > 0) {
                println("Colocando materias en el semestre ${i + 1}:\n$jsonArray")

                val recycler = itemView.findViewById<RecyclerView>(R.id.recyclerRowInscripcion)
                recycler.adapter = RecyclerInscripcionCalificacionAdapter(
                    itemView.context,
                    R.layout.row_materias_inscripcion,
                    jsonArray
                )
                recycler.layoutManager = GridLayoutManager(itemView.context, 3)
            } else {
                val jsonArrayMateriasXCursar = jsonSemestre.getJSONArray("semestre-${i + 1}")
                val arrayMxC = JSONArray()
                for (index in 0..jsonArrayMateriasXCursar.length() - 1) {
                    val jsonMateria = jsonArrayMateriasXCursar.getJSONObject(index)

                    val miJson = JSONObject()
                    miJson.put("materia", jsonMateria.getString("S${i + 1}${index + 1}"))
                    miJson.put("calificacion", "-")

                    arrayMxC.put(miJson)
                }

                val recycler = itemView.findViewById<RecyclerView>(R.id.recyclerRowInscripcion)
                recycler.adapter = RecyclerInscripcionCalificacionAdapter(
                    itemView.context,
                    R.layout.row_materias_inscripcion,
                    arrayMxC
                )
                recycler.layoutManager = GridLayoutManager(itemView.context, 3)
            }

            /*if( ! semestreActual.equals(jsonCalificacion.getString("semestre"))) {
                semestreActual = jsonCalificacion.getString("semestre")

                val tvSemestre = itemView.findViewById<TextView>(R.id.tvRowReticulaSemestre)
                tvSemestre.text = "Semestre $semestreActual"

                val calificacionesXSemestre = JSONArray()
                for(i in 0..reticula.length()-1) {
                    val json = reticula.getJSONObject(i)
                    if(json.getString("semestre").equals(semestreActual)) {
                        calificacionesXSemestre.put(json)
                    }
                }
                if(calificacionesXSemestre.length() > 0) {
                    val recycler = itemView.findViewById<RecyclerView>(R.id.recyclerRowReticula)
                    recycler.adapter = RecyclerReticulaCalificacionAdapter(
                        itemView.context,
                        R.layout.row_materias_reticula,
                        calificacionesXSemestre
                    )
                    recycler.layoutManager = GridLayoutManager(itemView.context, 3)
                }
            }*/
        }
    }
}