package com.example.calculator.Controller

import android.widget.Button
import android.widget.TextView
import com.example.calculator.Model.Calculator_Model

class Calculator_Controller : ICalculator_Controller {

    private val calcullator_model = Calculator_Model()

    override fun NumberClickEvent(button: Button, main_view: TextView, secondary_view: TextView) {

        calcullator_model.NumberClickEvent(button,main_view,secondary_view)

    }

    override fun OperatinosButtonCLickEvent(main_view: TextView, secondary_view: TextView, main_button: Button) {

        calcullator_model.OperatinosButtonCLickEvent(main_view,secondary_view,main_button)

    }

}