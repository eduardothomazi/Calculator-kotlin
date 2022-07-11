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
        tvInput?.append((view as Button).text)
        lastNumeric = true
    }

    fun onClear(view: View) {
        lastNumeric = false
        lastDot = false
        tvInput?.text = ""
    }

    fun onOperator(view: View) {
        tvInput?.text?.let {
            if (lastNumeric && !isOperatorAdded(it.toString())) {
                tvInput?.append((view as Button).text)
                lastNumeric = false
                lastDot = false
            }
        }
    }

    fun onEqual(view: View) {
        if (lastNumeric) {
            var tvValue = tvInput?.text.toString()
            var prefix = ""

            try {
                if (tvValue.startsWith("-")) {
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }
                if (tvValue.contains("-")) {
                    val splitValue = tvValue.split("-")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    if (prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    tvInput?.text = (one.toDouble() - two.toDouble()).toString()
                }

            } catch (e: ArithmeticException) {
                e.printStackTrace()
            } catch (e: IllegalStateException) {

            } catch (e: IndexOutOfBoundsException) {

            } catch (e: NumberFormatException) {
                Toast.makeText(this, "Enter valid calculus", Toast.LENGTH_SHORT).show()
                tvInput?.text = "0"
            }
        }
    }

    fun onDecimal(view: View) {
        if (lastNumeric && !lastDot) {
            tvInput?.append((view as Button).text)
            lastNumeric = false
            lastDot = true
        }
    }

    private fun isOperatorAdded(value: String): Boolean {
        return if (value.startsWith("-")) {
            false
        } else
            value.contains("/")
                    || value.contains("*")
                    || value.contains("-")
                    || value.contains("+")

    }
}
