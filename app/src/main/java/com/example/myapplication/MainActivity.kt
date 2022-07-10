package com.example.myapplication

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var tvInput: TextView? = null
    private var lastNumeric: Boolean = false
    private var lastDot: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvInput = findViewById(R.id.tv_input)
    }

    fun onDigit(view: View) {
        lastNumeric = true
        tvInput?.append((view as Button).text)
    }

    fun onClear(view: View) {
        lastNumeric = false
        tvInput?.text = ""
    }

    fun onDecimal(view: View) {
        if (tvInput?.text?.contains(".") == false) {
            if (lastNumeric) {
                tvInput?.append((view as Button).text)
                lastNumeric = false
            }
        }

    }
}