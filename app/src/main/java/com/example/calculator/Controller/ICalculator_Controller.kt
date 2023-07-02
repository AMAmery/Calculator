package com.example.calculator.Controller

import android.widget.Button
import android.widget.TextView

interface ICalculator_Controller {

    fun NumberClickEvent (button: Button,
                          main_view : TextView,
                          secondary_view : TextView
    )

    fun OperatinosButtonCLickEvent (main_view : TextView,
                                    secondary_view : TextView,
                                    main_button : Button)

    fun CleanClickEvent (main_view: TextView , secondary_view : TextView , clean_button : Button)

    fun DotClickEvent (main_view : TextView , secondary_view : TextView , dot_button : Button)

    fun NumberConverterClickEvent (main_view : TextView , secondary_view : TextView,numberConverter_button:Button)

    fun RemoveClickEvent (main_view : TextView , secondary_view : TextView,remove_button:Button)

    fun EqualClickEvent (main_view : TextView , secondary_view : TextView,equal_button:Button)

}