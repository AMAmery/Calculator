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

   lateinit var MainView : TextView
   lateinit var SecondaryView : TextView

//        // Main Buttons

    lateinit var PLUS : Button
    lateinit var MINUS : Button
    lateinit var MULTIPLICATION : Button
    lateinit var DIVISION : Button
    lateinit var EQUAL : Button
    lateinit var DOT : Button
    lateinit var CLEAN : Button
    lateinit var REMOVE : ImageButton

        // Numbers

    lateinit var BTN_9 : Button
    lateinit var BTN_8 : Button
    lateinit var BTN_7 : Button
    lateinit var BTN_6 : Button
    lateinit var BTN_5 : Button
    lateinit var BTN_4 : Button
    lateinit var BTN_3 : Button
    lateinit var BTN_2 : Button
    lateinit var BTN_1 : Button
    lateinit var BTN_0 : Button

//     Variables

    lateinit var NumbersList : List<Button>
    lateinit var MainButtonsList : List<Any>
    lateinit var Calcualtor_Controller : Calculator_Controller

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Assign value

            MainView = findViewById<TextView>(R.id.Main_View)
            SecondaryView = findViewById<TextView>(R.id.Secondary_View)
            PLUS = findViewById<Button>(R.id.BTN_Plus)
            MINUS = findViewById<Button>(R.id.BTN_Minus)
            MULTIPLICATION = findViewById<Button>(R.id.BTN_Multiplication)
            DIVISION = findViewById<Button>(R.id.BTN_Division)
            EQUAL = findViewById<Button>(R.id.BTN_Equal)
            DOT = findViewById<Button>(R.id.DOT)
            CLEAN = findViewById<Button>(R.id.CLEAN)
            REMOVE = findViewById<ImageButton>(R.id.REMOVE)

            BTN_9 = findViewById<Button>(R.id.BTN_9)
            BTN_8 = findViewById<Button>(R.id.BTN_8)
            BTN_7 = findViewById<Button>(R.id.BTN_7)
            BTN_6 = findViewById<Button>(R.id.BTN_6)
            BTN_5 = findViewById<Button>(R.id.BTN_5)
            BTN_4 = findViewById<Button>(R.id.BTN_4)
            BTN_3 = findViewById<Button>(R.id.BTN_3)
            BTN_2 = findViewById<Button>(R.id.BTN_2)
            BTN_1 = findViewById<Button>(R.id.BTN_1)
            BTN_0 = findViewById<Button>(R.id.BTN_0)


            NumbersList = listOf<Button>(BTN_9,BTN_8,BTN_7,BTN_6,BTN_5,BTN_4,BTN_3,BTN_2,BTN_1,BTN_0)
            MainButtonsList = listOf<Any>(PLUS,MINUS,MULTIPLICATION,DIVISION,EQUAL,DOT,CLEAN,REMOVE)
            Calcualtor_Controller = Calculator_Controller()

    }

    override fun onResume() {
        super.onResume()


//      Click event of number buttons

            NumbersList.forEach { button -> button.setOnClickListener{

                Calcualtor_Controller.NumberClickEvent(button,MainView,SecondaryView)

            }}

    }

}