package com.example.calculator.Model

import android.util.Log
import android.util.TypedValue
import android.widget.Button
import android.widget.TextView

class Calculator_Model : ICalculator_Model {

    private var number = ""
    private var result = 0.0
    private var DECREACEFONTSIZE = 5F
    private val SYMBOLS = arrayOf('+','-','×','÷','.')
    private var operation_selected = '+'
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

        fun Calculate (operatoin : Char , resultp : Double):Double{

            var numberdb = number.toDouble()


            fun FILTER(number: Double): Double { return if (number == 0.0) 1.0 else number }

          val result = when(operatoin){

                '+' ->
                    resultp + numberdb

                '-' ->
                    resultp - numberdb

                '×' -> {
                    numberdb = FILTER(numberdb)
                    resultp * numberdb
                }
                '÷' -> {
                    numberdb = FILTER(numberdb)
                    resultp / numberdb
                }


               else -> {
                   return 0.0
               }
           }

            return result
        }

    override fun NumberClickEvent(button: Button, main_view: TextView, secondary_view: TextView) {

        val number_txt = button.text

        number += number_txt
        MakeSmallFontSizeView(main_view)
        val final_value = Calculate(operation_selected,result)



        main_view.append(number_txt)
        secondary_view.text = final_value.toString()

    }

    override fun OperatinosButtonCLickEvent(main_view: TextView, secondary_view: TextView, main_button: Button) {

        val button_txt = main_button.text

        // Checking last character of main view

            var ALLOW_TO_APPEND = true

            for (character in SYMBOLS){

                if (main_view.text.get(main_view.length()-1) == character){

                    ALLOW_TO_APPEND = false
                    break

                }

            }

        if (ALLOW_TO_APPEND){

            number = ""
            operation_selected = button_txt[0]
            main_view.append(button_txt)
            result = secondary_view.text.toString().toDouble()

        }

    }

    override fun CleanClickEvent(main_view: TextView, secondary_view: TextView) {

        main_view.text = ""
        secondary_view.text = ""
        operation_selected = '+'

        number = ""
        result = 0.0

    }

    override fun DotClickEvent(main_view: TextView, secondary_view: TextView) {

        var ALLOW_TO_APPEND = true

        // Checking last character of main view


            for (character in SYMBOLS){

                if (main_view.text.get( main_view.length()-1 )  == character){

                    ALLOW_TO_APPEND = false
                    break

                }

            }

       // Checking number has one . symbol

            for (character in number){

                if ( character == '.' ){

                    ALLOW_TO_APPEND = false
                    break

                }

            }

        if ( ALLOW_TO_APPEND ){

            number += "."
            main_view.append(".")

        }

    }

    override fun NumberConverterClickEvent(main_view: TextView, secondary_view: TextView) {

        var converted_number = ""
        var ALLOW_TO_APPEND = true
        var main_view_txt = main_view.text.toString()


        // Checking last character of main_view

            for (character in SYMBOLS){

                if ( main_view_txt.get(main_view_txt.length-1)==character ){

                    ALLOW_TO_APPEND = false
                    break

                }

            }

        // Checking first character of number is - or not

            if ( number.get(0) == '-' ){

                converted_number = number.substringAfter('-')

            }else{

                converted_number = "-$number"

            }


        main_view_txt = main_view_txt.replace(Regex(number),converted_number)

        number = converted_number
        main_view.text = main_view_txt

    }

    override fun RemoveClickEvent(main_view: TextView, secondary_view: TextView) {

        var mainview_str = main_view.text.toString()
        var previous_number = ""

        fun CalulateNewValue (Number : Double ,
                              operation: Char) {

            val calulate_response = when (operation) {

                '+' -> {

                    result += Number

                }

                '-' -> {

                    result -= Number
                    result -= previous_number.toDouble()

                }

                '×' -> {

                    result *= Number

                }

                '÷' -> {

                    result /= Number

                }

                else -> 0.0
            }


        }

            // Remove last character

                mainview_str = mainview_str.dropLast(1)
                if (mainview_str.isEmpty()) mainview_str += "0"
                previous_number = number
                if ( number.isNotEmpty() ) number = number.dropLast(1)

            // To specify last character is symbol or not

            var ISSYMBOL = false

            for (symbol in SYMBOLS){

                if ( mainview_str.last() == symbol ){

                    ISSYMBOL = true
                    break

                }

            }

            if (ISSYMBOL){

                // remove symbol
                // save new number
                // calculate new values

                mainview_str = mainview_str.dropLast(1)

                // get new number from string

                    val number_pattern = Regex("[0-9]+$|[-][0-9]+$|[0-9]+[.][0-9]+$|[-][0-9]+[.][0-9]+$")

                    number = "${number_pattern.find(mainview_str)?.value}"

                // get new operation

                    var ResultToFindeOperation = ""
                    val operation_pattern = Regex("[[+]|[-]|[×]|[÷]][0-9]+$")
                    var response_search = operation_pattern.containsMatchIn(mainview_str)
                    val result_index = ResultToFindeOperation.length
                    val mainview_index = mainview_str.length


                    if (response_search){

                        ResultToFindeOperation = operation_pattern.find((mainview_str))!!.value
                        operation_selected = ResultToFindeOperation!!.get(0)

                    }else{

                        ResultToFindeOperation= "$mainview_str"
                        operation_selected = '+'

                    }


                        val previous_char = mainview_str.get( (mainview_index-result_index)-1 )

                    var ISSYMBO = false
                    for (symbol in arrayOf('-','+','×','÷')){

                        if (previous_char == symbol){

                            ISSYMBOL = true
                            break

                        }

                    }


                    when (ISSYMBO){


                        true ->{

                            number = ResultToFindeOperation


                        }

                        else ->{

                            operation_selected = ResultToFindeOperation.get(0)

                        }

                    }

                if (mainview_str.length == number.length){

                    result = number.toDouble()
                    number = "0"
                    previous_number = "0"
                    operation_selected = '+'

                }

                val operation_parameter = when (operation_selected) {

                    '+' -> '-'
                    '-' -> '+'
                    '×' -> '÷'

                    else -> '×'

                }
                CalulateNewValue(number.toDouble(),operation_parameter)

                val result2 = Calculate(operation_selected,result)
                main_view.text = mainview_str
                secondary_view.text = result2.toString()

            }else{

                // calculate new values
                Log.d("locallllllll","$number , $mainview_str")
                if (number.isEmpty()) number = mainview_str
                if (mainview_str.length == number.length){

                    result = number.toDouble()
                    number = "0"
                    previous_number = "0"
                    operation_selected = '+'

                }
                    CalulateNewValue(number.toDouble(),operation_selected)

                main_view.text = mainview_str
                secondary_view.text = result.toString()

            }


        }

    }

