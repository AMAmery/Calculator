package com.example.calculator.Model

import android.util.Log
import android.util.TypedValue
import android.widget.Button
import android.widget.TextView
import org.w3c.dom.Text

class Calculator_Model : ICalculator_Model {

    private var number = ""
    private var result = 0.0
    private var DECREACEFONTSIZE = 5F
    private val SYMBOLS = arrayOf('+','-','×','÷','.')
    // Methods

        fun MakeSmallFontSizeView (main_view : TextView){


            while (main_view.lineCount > 1){

                var fontsize_mainview = main_view.textSize

                if (fontsize_mainview > 35F){
                    fontsize_mainview = 35F
                }
                fontsize_mainview -= DECREACEFONTSIZE
                Log.d("fontsize",DECREACEFONTSIZE.toString())

                main_view.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontsize_mainview)
                DECREACEFONTSIZE +=5F

            }


        }

    override fun NumberClickEvent(button: Button, main_view: TextView, secondary_view: TextView) {

        val number_txt = button.text

        number += number_txt
        main_view.append(number_txt)
        MakeSmallFontSizeView(main_view)

    }

    override fun OperatinosButtonCLickEvent(main_view: TextView, secondary_view: TextView, main_button: Button) {

        val button_txt = main_button.text

        // Checking last character of main view

            var ALLOW_TO_APPEND = true

            for (character in SYMBOLS){

                if (main_view.text.get(main_view.length()-1) == character){

                    ALLOW_TO_APPEND = false

                }

            }

        if (ALLOW_TO_APPEND){

            main_view.append(button_txt)

        }

    }

}