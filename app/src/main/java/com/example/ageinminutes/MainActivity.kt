package com.example.ageinminutes

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private var tvselecteddate: TextView?=null
    private var tvinminute: TextView?=null
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button: Button=findViewById(R.id.button1)
        tvselecteddate=findViewById(R.id.tvSelecteddate)
        tvinminute=findViewById(R.id.tvinminute)
        button.setOnClickListener{


            clickPicker()

        }
    }

    fun clickPicker(){

        val myCalender=Calendar.getInstance()
        val year=myCalender.get(Calendar.YEAR)
        val month=myCalender.get(Calendar.MONTH)
        val day=myCalender.get(Calendar.DAY_OF_MONTH)
        val dp= DatePickerDialog(
            this,
            {view,selectedYear,selectedMonth,selecteddayOfMonth ->
                Toast.makeText(this,"Year was $selectedYear, Month was ${selectedMonth+1}, Day of the month was $selecteddayOfMonth",Toast.LENGTH_LONG).show()

                val selectedDate="$selecteddayOfMonth/${selectedMonth+1}/$selectedYear"
                tvselecteddate?.setText(selectedDate)

                val sdf=SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val thedate=sdf.parse(selectedDate)

                val seldateminutes=thedate.time/60000

                val currentDate=sdf.parse(sdf.format(System.currentTimeMillis()))

                val currDateinMinutes=currentDate.time/60000
                val differ=currDateinMinutes-seldateminutes
                tvinminute?.text=differ.toString()


            },
            year,
            month,
            day
        )
        dp.datePicker.maxDate=System.currentTimeMillis()-86400000
        dp.show()
    }

}