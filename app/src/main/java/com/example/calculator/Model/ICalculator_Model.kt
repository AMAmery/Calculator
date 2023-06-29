package com.example.calculator.Model

import android.widget.Button
import android.widget.TextView

interface ICalculator_Model {

    fun NumberClickEvent (button:Button ,
                          main_view : TextView ,
                          secondary_view : TextView)

    fun OperatinosButtonCLickEvent (main_view : TextView,
                                    secondary_view : TextView,
                                    main_button : Button)

    fun CleanClickEvent (main_view: TextView , secondary_view : TextView)

    fun DotClickEvent (main_view : TextView , secondary_view : TextView)

    fun NumberConverterClickEvent (main_view : TextView , secondary_view : TextView)

    fun RemoveClickEvent (main_view : TextView , secondary_view : TextView)

    fun EqualClickEvent (main_view : TextView , secondary_view : TextView)

}