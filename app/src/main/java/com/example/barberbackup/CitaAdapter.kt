package com.example.barberbackup

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class CitaAdapter(private val cita:ArrayList<CitaModel>)
    : RecyclerView.Adapter<CitaAdapter.ViewHolder>(){
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val tvCitaId = itemView.findViewById<TextView>(R.id.tv_citaid)
        val tvCitaNombre = itemView.findViewById<TextView>(R.id.tv_nombre)
        val tvCitaFecha = itemView.findViewById<TextView>(R.id.tv_fecha)
        val tvCitaHorario = itemView.findViewById<TextView>(R.id.tv_horario)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_cita, parent, false)
        )
    }

    override fun getItemCount()= cita.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val citas = cita[position]
        holder.tvCitaId.text = "Cita #${citas.id}"
        holder.tvCitaNombre.text = "Nombre: ${citas.nombre}"
        holder.tvCitaFecha.text = "Fecha: ${citas.fecha}"
        holder.tvCitaHorario.text = "Hora ${citas.horario}"
    }
}