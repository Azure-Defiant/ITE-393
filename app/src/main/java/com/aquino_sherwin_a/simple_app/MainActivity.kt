package com.aquino_sherwin_a.simple_app

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var firstNumberEditText: EditText
    private lateinit var secondNumberEditText: EditText
    private lateinit var resultTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize views
        firstNumberEditText = findViewById(R.id.firstnumber)
        secondNumberEditText = findViewById(R.id.secondnumber)
        resultTextView = findViewById(R.id.result)

        // Initialize buttons
        val additionButton: Button = findViewById(R.id.addition)
        val subtractionButton: Button = findViewById(R.id.subtraction)
        val multiplicationButton: Button = findViewById(R.id.multiplication)
        val divisionButton: Button = findViewById(R.id.division)

        // Set click listeners for each operation
        additionButton.setOnClickListener { performOperation('+') }
        subtractionButton.setOnClickListener { performOperation('-') }
        multiplicationButton.setOnClickListener { performOperation('*') }
        divisionButton.setOnClickListener { performOperation('/') }
    }

    private fun performOperation(operator: Char) {
        // Get the input values
        val firstNumberStr = firstNumberEditText.text.toString()
        val secondNumberStr = secondNumberEditText.text.toString()

        // Check if inputs are empty
        if (firstNumberStr.isEmpty() || secondNumberStr.isEmpty()) {
            Toast.makeText(this, "Please enter both numbers", Toast.LENGTH_SHORT).show()
            return
        }

        // Convert inputs to doubles
        val firstNumber = firstNumberStr.toDouble()
        val secondNumber = secondNumberStr.toDouble()

        // Perform calculation based on operator
        val result = when (operator) {
            '+' -> firstNumber + secondNumber
            '-' -> firstNumber - secondNumber
            '*' -> firstNumber * secondNumber
            '/' -> {
                // Handle division by zero
                if (secondNumber == 0.0) {
                    Toast.makeText(this, "Cannot divide by zero", Toast.LENGTH_SHORT).show()
                    return
                }
                firstNumber / secondNumber
            }
            else -> {
                Toast.makeText(this, "Invalid operator", Toast.LENGTH_SHORT).show()
                return
            }
        }

        // Format the result to remove .0 for whole numbers
        val formattedResult = if (result % 1 == 0.0) {
            result.toInt().toString()
        } else {
            result.toString()
        }

        // Display the result
        resultTextView.text = "Result: $formattedResult"
    }
}