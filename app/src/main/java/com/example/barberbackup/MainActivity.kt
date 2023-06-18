package com.example.barberbackup

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnAgendarCita = findViewById<Button>(R.id.btn_agendar_cita)
        btnAgendarCita.setOnClickListener{
            goToAgendarCita()
        }
        val btnVerCitas = findViewById<Button>(R.id.btn_miscitas)
        btnVerCitas.setOnClickListener{
            goToVerCitas()
        }
    }
    private fun goToAgendarCita(){
        val i = Intent(this, AgendarActivity::class.java)
        startActivity(i)
    }
    private fun goToVerCitas(){
        val intent = Intent(this, CitasActivity::class.java)
        startActivity(intent)
    }
}