package com.example.barberbackup

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore.Audio.Radio
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.google.android.material.snackbar.Snackbar
import org.w3c.dom.Text

class AgendarActivity : AppCompatActivity() {
    val selectedCalendar = Calendar.getInstance()
    private var selectedRadioButton: RadioButton? = null
    //@SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agendar)
        val etnombre = findViewById<EditText>(R.id.et_nombrecita)
        val etfecha = findViewById<EditText>(R.id.et_fecha)
        val rghorario = findViewById<RadioGroup>(R.id.rg_horario)
        val btnNext = findViewById<Button>(R.id.btn_next)
        val cvResumen = findViewById<CardView>(R.id.cv_Resumen)
        val cvPrincipal = findViewById<CardView>(R.id.cv_Principal)
        val linearlay = findViewById<LinearLayout>(R.id.ll_agendar_cita)
        btnNext.setOnClickListener{
            if(etnombre.text.toString().length<3) {
                etnombre.error = "Este campo es obligatorio"
            }else if(etfecha.text.toString().length<3) {
                etfecha.error = "Este campo es obligatorio"
                Snackbar.make(
                    etfecha,
                    "Debe seleccionar una fecha para la cita",
                    Snackbar.LENGTH_SHORT
                ).show()
            }else if (selectedRadioButton == null) {
                Snackbar.make(
                    linearlay,
                    "Debe seleccionar una hora para la cita",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
            else{
                showCitaData()
                cvPrincipal.visibility= View.GONE
                cvResumen.visibility= View.VISIBLE
            }

        }
    }
    private fun displayRadioButtons(){
        val hours = arrayOf("8:00 am", "9:00 am", "10:00 am", "2:00 pm", "3:00 pm", "4:00 pm")
        val radioGroup = findViewById<RadioGroup>(R.id.rg_horario)
        selectedRadioButton=null
        radioGroup.removeAllViews()
        hours.forEach {
            val radioButton = RadioButton(this)
            radioButton.id = View.generateViewId()
            radioButton.text = it
            radioButton.setOnClickListener{view ->
                selectedRadioButton?.isChecked = false
                selectedRadioButton=view as RadioButton?
                selectedRadioButton?.isChecked= true
            }
            radioGroup.addView(radioButton)
        }
    }
    fun  onClickScheduleDate(v: View?){
        val etScheduleDate = findViewById<EditText>(R.id.et_fecha)
        val year = selectedCalendar.get(Calendar.YEAR)
        val month = selectedCalendar.get(Calendar.MONTH)
        val dayOfMonth = selectedCalendar.get(Calendar.DAY_OF_MONTH)
        val listener = DatePickerDialog.OnDateSetListener{DatePicker, y, m, d ->
            selectedCalendar.set(y,m,d)
            etScheduleDate.setText("$y-$m-$d")
            etScheduleDate.error = null
            displayRadioButtons()
        }
        DatePickerDialog(this, listener, year, month, dayOfMonth).show()
    }
    private fun showCitaData(){
        val tvnombrecita = findViewById<TextView>(R.id.tv_nombrecita)
        val tvfecha = findViewById<TextView>(R.id.tv_fecha)
        val tvhorario= findViewById<TextView>(R.id.tv_horario)
        val etnombrecita = findViewById<TextView>(R.id.et_nombrecita)
        val etfecha = findViewById<TextView>(R.id.et_fecha)
        val rghorario = findViewById<RadioGroup>(R.id.rg_horario)
        val tvConfirmNombre = findViewById<TextView>(R.id.tvConfirmNombre)
        val tvConfirmFecha = findViewById<TextView>(R.id.tvConfirmFecha)
        val tvConfirmHorario= findViewById<TextView>(R.id.tvConfirmHorario)

        tvConfirmNombre.text = etnombrecita.text.toString()
        tvConfirmFecha.text = etfecha.text.toString()
        tvConfirmHorario.text = selectedRadioButton?.text.toString()
    }

    override fun onBackPressed() {
        val cvResumen = findViewById<CardView>(R.id.cv_Resumen)
        val cvPrincipal = findViewById<CardView>(R.id.cv_Principal)
        if(cvResumen.visibility == View.VISIBLE){
            cvResumen.visibility = View.GONE
            cvPrincipal.visibility=View.VISIBLE
        }else if(cvPrincipal.visibility == View.VISIBLE){
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Está seguro que desea salir?")
            builder.setMessage("Si abandonas el formulario perderá toda la informacion ingresada.")
            builder.setPositiveButton("salir"){_, _ ->
                finish()
            }
            builder.setNegativeButton("continuar"){dialog, _ ->
                dialog.dismiss()
            }
            val dialog = builder.create()
            dialog.show()
        }

    }
}


