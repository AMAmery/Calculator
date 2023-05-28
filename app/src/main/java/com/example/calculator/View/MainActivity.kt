package com.example.calculator.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import com.example.calculator.Controller.Calculator_Controller
import com.example.calculator.R

class MainActivity : AppCompatActivity() {

    // Views

    val MainView = findViewById<TextView>(R.id.Main_View)
    val SecondaryView = findViewById<TextView>(R.id.Secondary_View)

        // Main Buttons

    val PLUS = findViewById<Button>(R.id.BTN_Plus)
    val MINUS = findViewById<Button>(R.id.BTN_Minus)
    val MULTIPLICATION = findViewById<Button>(R.id.BTN_Multiplication)
    val DIVISION = findViewById<Button>(R.id.BTN_Division)
    val EQUAL = findViewById<Button>(R.id.BTN_Equal)
    val DOT = findViewById<Button>(R.id.DOT)
    val CLEAN = findViewById<Button>(R.id.CLEAN)
    val REMOVE = findViewById<ImageButton>(R.id.REMOVE)

        // Numbers
    val BTN_9 = findViewById<Button>(R.id.BTN_9)
    val BTN_8 = findViewById<Button>(R.id.BTN_8)
    val BTN_7 = findViewById<Button>(R.id.BTN_7)
    val BTN_6 = findViewById<Button>(R.id.BTN_6)
    val BTN_5 = findViewById<Button>(R.id.BTN_5)
    val BTN_4 = findViewById<Button>(R.id.BTN_4)
    val BTN_3 = findViewById<Button>(R.id.BTN_3)
    val BTN_2 = findViewById<Button>(R.id.BTN_2)
    val BTN_1 = findViewById<Button>(R.id.BTN_1)
    val BTN_0 = findViewById<Button>(R.id.BTN_0)

    // Variables

    val NumbersList = listOf<Button>(BTN_9,BTN_8,BTN_7,BTN_6,BTN_5,BTN_4,BTN_3,BTN_2,BTN_1,BTN_0)
    val MainButtonsList = listOf<Any>(PLUS,MINUS,MULTIPLICATION,DIVISION,EQUAL,DOT,CLEAN,REMOVE)
    val Calcualtor_Controller = Calculator_Controller()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}