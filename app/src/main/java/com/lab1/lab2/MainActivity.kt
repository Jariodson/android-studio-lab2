package com.lab1.lab2

import android.annotation.SuppressLint
import android.os.Bundle
import android.service.autofill.VisibilitySetterAction
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.transition.Visibility

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val number_field_1 = findViewById<EditText>(R.id.number_field_1)
        val number_field_2 = findViewById<EditText>(R.id.number_field_2)
        val button_calculate = findViewById<Button>(R.id.button_calculate)
        val label_text = findViewById<TextView>(R.id.textView)
        val label_calculate_result = findViewById<TextView>(R.id.label_calculate_result)
        val button_clear = findViewById<Button>(R.id.button_clear)

        val spinner: Spinner = findViewById(R.id.spinner)
        ArrayAdapter.createFromResource(
            this,
            R.array.calculator_functions,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        button_calculate.setOnClickListener {
            val n1 = number_field_1.text.toString().toFloat()
            val n2 = number_field_2.text.toString().toFloat()

            val selectedValue = spinner.selectedItem.toString()
            var res: Float = 0F
            if (selectedValue == "Сложение") {
                res = n1 + n2
            }
            else if (selectedValue == "Вычитание") {
                res = n1 - n2
            }
            else if (selectedValue == "Умножение") {
                res = n1 * n2
            }
            else if (selectedValue == "Деление") {
                res = n1 / n2
            }

            label_calculate_result.visibility = View.VISIBLE
            label_calculate_result.setText(res.toString())
        }

        button_clear.setOnClickListener {
            number_field_1.setText("")
            number_field_2.setText("")
            label_calculate_result.setText("")
        }
    }
}