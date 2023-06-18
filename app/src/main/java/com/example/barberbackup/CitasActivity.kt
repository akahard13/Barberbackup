package com.example.barberbackup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CitasActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_citas)
        val rvCitas = findViewById<RecyclerView>(R.id.rv_citas)
        val cita = ArrayList<CitaModel>()
        cita.add(
            CitaModel(1, "Harnaldo", "25-03-23", "09:00 am")
        )
        cita.add(
            CitaModel(2, "Hard", "15-07-23", "09:00 am")
        )
        cita.add(
            CitaModel(3, "Hardsito", "25-08-23", "03:00 pm")
        )
        cita.add(
            CitaModel(2, "Hardy", "16-08-23", "04:00 pm")
        )
        rvCitas.layoutManager = LinearLayoutManager(this)
        rvCitas.adapter=CitaAdapter(cita)
    }
}