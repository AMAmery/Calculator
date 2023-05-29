package com.example.calculator.Controller

import android.widget.Button
import android.widget.TextView

interface ICalculator_Controller {

    fun NumberClickEvent (button: Button,
                          main_view : TextView,
                          secondary_view : TextView
    )

}