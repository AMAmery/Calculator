package com.example.calculator.Model

import android.widget.Button
import android.widget.TextView

class Calculator_Model : ICalculator_Model {

    override fun NumberClickEvent(button: Button, main_view: TextView, secondary_view: TextView) {

        val number_txt = button.text

        main_view.append(number_txt)

    }

}