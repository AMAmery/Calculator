package com.example.calculator.Controller

import android.content.Context
import android.widget.Button
import android.widget.TextView
import com.example.calculator.Model.Calculator_Model

class Calculator_Controller(context: Context) : ICalculator_Controller {

    private val calcullator_model = Calculator_Model(context)

    override fun NumberClickEvent(button: Button, main_view: TextView, secondary_view: TextView) {

        calcullator_model.NumberClickEvent(button,main_view,secondary_view)

    }

    override fun OperatinosButtonCLickEvent(main_view: TextView, secondary_view: TextView, main_button: Button) {

       if (!main_view.text.isNullOrEmpty()) { calcullator_model.OperatinosButtonCLickEvent(main_view, secondary_view, main_button) }

    }

    override fun CleanClickEvent(main_view: TextView, secondary_view: TextView, clean_button : Button) {

        calcullator_model.CleanClickEvent(main_view,secondary_view,clean_button)

    }

    override fun DotClickEvent(main_view: TextView, secondary_view: TextView,dot_button : Button) {

        if (!main_view.text.isNullOrEmpty()){

            calcullator_model.DotClickEvent(main_view,secondary_view,dot_button)

        }

    }

    override fun NumberConverterClickEvent(main_view: TextView, secondary_view: TextView,numberConverter:Button) {

        if (!main_view.text.isNullOrEmpty()){

            calcullator_model.NumberConverterClickEvent(main_view,secondary_view,numberConverter)

        }

    }

    override fun RemoveClickEvent(main_view: TextView, secondary_view: TextView,remove_button : Button) {

        if (!main_view.text.isNullOrEmpty()){

            calcullator_model.RemoveClickEvent(main_view,secondary_view,remove_button)

        }

    }

    override fun EqualClickEvent(main_view: TextView, secondary_view: TextView,equal_animation:Button) {

        if(main_view.text.isNotEmpty()){
            calcullator_model.EqualClickEvent(main_view,secondary_view,equal_animation)
        }

    }

}