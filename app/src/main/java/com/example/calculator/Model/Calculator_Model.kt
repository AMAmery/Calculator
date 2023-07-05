package com.example.calculator.Model

import android.animation.Animator
import android.content.Context
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.math.BigDecimal
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder

class Calculator_Model(context: Context) : ICalculator_Model {

    private var number = ""
    private var result = BigDecimal("0.0")
    private val SYMBOLS = arrayOf('+','-','×','÷','.')
    private var operation_selected = '+'
    private val decimalFormat = DecimalFormat("#.########", DecimalFormatSymbols.getInstance(Locale.ENGLISH))
    private val context = context
    private val decimalDigits_WARNINGMESSAGE = "the maximum decimal digits of number is 8"
    private val Digits_WARNINGMESSAGE = "the maximum digits of number is 16"
    private val DiviedByZeroUnavailable = "divide by zero is not available"
    private var OrginalViewScaleX : Float? = null
    private var OrginalViewScaleY : Float? = null

    // Methods

        fun SmallScaleAnimation (view:Button) : ObjectAnimator{

            OrginalViewScaleX = view.scaleX
            OrginalViewScaleY = view.scaleY

            val animation = ObjectAnimator.ofPropertyValuesHolder(view,

                PropertyValuesHolder.ofFloat("scaleX", OrginalViewScaleX!! , OrginalViewScaleX!!-0.5F),
                PropertyValuesHolder.ofFloat("scaleY", OrginalViewScaleY!! , OrginalViewScaleY!!-0.5F)

            )

            animation.duration = 70

            return animation
        }

        fun ResizeScaleAnimation (view:Button) : ObjectAnimator{

            val animation = ObjectAnimator.ofPropertyValuesHolder(view,

                PropertyValuesHolder.ofFloat("scaleX", OrginalViewScaleX!!-0.5F , OrginalViewScaleX!!) ,
                PropertyValuesHolder.ofFloat("scaleY", OrginalViewScaleY!!-0.5F , OrginalViewScaleY!!)

                )

            animation.duration = 70

         return animation

        }

        fun RunAnimation (button:Button){

            val scale_animation = SmallScaleAnimation(button)
            scale_animation.start()
            scale_animation.addListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator) {
                }

                override fun onAnimationEnd(animation: Animator) {

                    ResizeScaleAnimation(button).start()

                }

                override fun onAnimationCancel(animation: Animator) {
                }

                override fun onAnimationRepeat(animation: Animator) {
                }
            })


        }

        fun Warning(message: String) = Toast.makeText(context,message,Toast.LENGTH_SHORT).show()

        fun AmountOfDecimalDigits():Int{

            var count = 0
            var DotIndex = number.indexOf('.')
            val PreviousNumber = number
            if (DotIndex==-1){

                number+=".0"
                DotIndex = number.indexOf('.')
            }
            var index = DotIndex
            while(index<number.length-1){
                count++
                index++
            }
            number = PreviousNumber
            return count

        }

        fun AmountOfDigits():Int{

            val previous_number = number
            if (number.isEmpty()) number = "0"
            val dotIndex = number.indexOf('.')

            if (dotIndex<0){
                number+=".0"
            }

            var count = 0
            var index = 0

            while( number[index]!='.' ){
                index++
                count++
            }

            number = previous_number

            return count
        }

        fun FindeNumberInEnd (main_view_str:String):String{

            val numberRP = Regex("[0-9]+$")
            val negative_numberRP = Regex("[-][0-9]+$")
            val decimalRP = Regex("[0-9]+[.][0-9]+$")
            val negative_decimalRP = Regex("[-][0-9]+[.][0-9]+$")
            val regex = Regex("$numberRP|$negative_numberRP|$decimalRP|$negative_decimalRP")

            val result = regex.find(main_view_str)?.value

            return "$result"

        }

        fun RemoveZeroFromBeginning (inputp:String):String{

            val numberRP = Regex("[0-9]+$")
            val negative_numberRP = Regex("[-][0-9]+$")
            val decimalRP = Regex("[0-9]+[.][0-9]+$")
            val negative_decimalRP = Regex("[-][0-9]+[.][0-9]+$")
            val regex = Regex("$numberRP|$negative_numberRP|$decimalRP|$negative_decimalRP")

            val ResultOfSearch = regex.find(inputp)?.value
            var end = false
            var StringWithOutExtra0 = ""
            for (char in "$ResultOfSearch"){

                if (char !in arrayOf('-','0')){

                    val start_index = ResultOfSearch.toString().indexOf(char)
                    val end_index = ResultOfSearch.toString().length-1
                    end = true
                    for (index in start_index..end_index){

                        StringWithOutExtra0 += ResultOfSearch.toString().get(index)

                    }

                }

                if (end)break

            }

            // if number is like this : .5
                if(StringWithOutExtra0[0].equals('.'))StringWithOutExtra0="0$StringWithOutExtra0"

            if (inputp.toDouble()<0) StringWithOutExtra0 = "-$StringWithOutExtra0"
            return  StringWithOutExtra0

        }


        fun Calculate (operatoin : Char , resultp : BigDecimal):BigDecimal{

            var numberdb = BigDecimal(number)

            fun FILTER(number: BigDecimal): BigDecimal { return if (number == BigDecimal("0.0")) BigDecimal("1.0") else number }

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

                    if (numberdb.equals(BigDecimal("0"))){
                        Warning(DiviedByZeroUnavailable)
                        numberdb = BigDecimal("1")
                    }

                        resultp.divide(numberdb,10,BigDecimal.ROUND_HALF_UP)

                }


               else -> {
                   return BigDecimal("0.0")
               }
           }

            return result
        }

    override fun NumberClickEvent(button: Button, main_view: TextView, secondary_view: TextView) {

        RunAnimation(button)

        val number_txt = button.text
        var digits = AmountOfDigits()
        var decimal_digits = AmountOfDecimalDigits()
        if (digits < 16 && decimal_digits < 8){
        main_view.append(number_txt)
        number += number_txt
        }
        digits = AmountOfDigits()
        decimal_digits = AmountOfDecimalDigits()
        if (digits == 16){
            Warning(Digits_WARNINGMESSAGE)
        }
        if (decimal_digits == 8){
            Warning(decimalDigits_WARNINGMESSAGE)
        }


        val final_value = Calculate(operation_selected,result)



        if(number.toDouble() != 0.0){
            val old_number = number
            number = RemoveZeroFromBeginning(number)
            main_view.text = main_view.text.toString().replace(old_number,number)
        }
        secondary_view.text = decimalFormat.format(final_value)

    }

    override fun OperatinosButtonCLickEvent(main_view: TextView, secondary_view: TextView, main_button: Button) {

        RunAnimation(main_button)

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
            result = BigDecimal(secondary_view.text.toString())

        }

    }

    override fun CleanClickEvent(main_view: TextView, secondary_view: TextView,clean_button:Button) {

        RunAnimation(clean_button)
        main_view.text = ""
        secondary_view.text = ""
        operation_selected = '+'

        number = ""
        result = BigDecimal("0.0")

    }

    override fun DotClickEvent(main_view: TextView, secondary_view: TextView , dot_button:Button) {

        RunAnimation(dot_button)
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

    override fun NumberConverterClickEvent(main_view: TextView, secondary_view: TextView,numberConverter:Button) {

        RunAnimation(numberConverter)
        var mainView_str = main_view.text.toString()
        var previousNumber = ""
        var newNumber = ""
        if (mainView_str.last() !in "+-×÷") {
            previousNumber = number
            if (number.last()=='.')number+="0"
            newNumber =
                if (previousNumber[0] == '-') previousNumber.substring(1) else "-$previousNumber"
            number = newNumber


            // replace

            mainView_str = mainView_str.replace(Regex("$previousNumber$"), newNumber)

            main_view.text = mainView_str
            secondary_view.text =
                decimalFormat.format(Calculate(operation_selected, result)).toString()
        }
    }

    override fun RemoveClickEvent(main_view: TextView, secondary_view: TextView,remove_button:Button) {

        RunAnimation(remove_button)
        var mainview_str = main_view.text.toString()

        fun CalculateNewValue (Number : Double,
                               operation: Char) {

            val calulate_response = when (operation) {

                '+' -> {

                    result += BigDecimal("$Number")

                }

                '-' -> {

                    result -= BigDecimal("$Number")

                }

                '×' -> {

                    result *= BigDecimal("$Number")

                }

                '÷' -> {

                    result /= BigDecimal("$Number")

                }

                else -> 0.0
            }


        }

            // Remove last character
                if (mainview_str.last()!in "+-×÷") {
                    mainview_str = mainview_str.dropLast(1)
                }
                main_view.text = mainview_str
                if (mainview_str.isEmpty()) mainview_str += "0"
                if ( number.isNotEmpty() ) number = number.dropLast(1)

            // To specify last character is symbol or not

            var ISSYMBOL = false
            for (symbol in "+-×÷"){

                if ( mainview_str.last() == symbol ){

                    ISSYMBOL = true
                    break

                }

            }

            if (ISSYMBOL){

                if (mainview_str.length==number.length && number[0]=='-'){
                    mainview_str = mainview_str.dropLast(1)
                    number = number.dropLast(1)
                }else {
                    mainview_str = mainview_str.dropLast(1)
                }
                if (mainview_str.isEmpty())mainview_str+="0"
                if ( mainview_str.last() in SYMBOLS){

                    mainview_str = mainview_str.dropLast(1)

                }

                // get new number from string

                    val number_pattern = Regex("[0-9]+$|[-][0-9]+$|[0-9]+[.][0-9]+$|[-][0-9]+[.][0-9]+$")

                    number = "${number_pattern.find(mainview_str)?.value}"


                // get new operation

                    var ResultToFindeOperation = ""
                    val operation_pattern = Regex("[[+]|[-]|[×]|[÷]][0-9]+$|[[+]|[-]|[×]|[÷]][0-9]+[.][0-9]+$")
                    var response_search = operation_pattern.containsMatchIn(mainview_str)


                    if (response_search){

                        ResultToFindeOperation = operation_pattern.find((mainview_str))!!.value
                        operation_selected = ResultToFindeOperation!!.get(0)

                    }else{

                        ResultToFindeOperation= mainview_str
                        operation_selected = '+'

                    }
                    val result_index = ResultToFindeOperation.length
                    val mainview_index = mainview_str.length

                    var previous_char = ' '
                    if (mainview_str.length == number.length){

                        previous_char = mainview_str.get(0)

                    }else{

                        previous_char = mainview_str.get( (mainview_index-result_index)-1 )

                    }

                    var ISSYMBOL = false
                    for (symbol in arrayOf('-','+','×','÷')){

                        if (previous_char == symbol){

                            ISSYMBOL = true
                            break

                        }

                    }


                    when (ISSYMBOL){


                        true ->{

                            number = ResultToFindeOperation
                            operation_selected=previous_char

                        }

                        else ->{

                            if (mainview_str.length==number.length){
                                result = BigDecimal("0.0")
                                operation_selected = '+'

                            }else{

                            operation_selected = ResultToFindeOperation.get(0)
                            ResultToFindeOperation =ResultToFindeOperation.substring(1)
                            number = ResultToFindeOperation

                            }

                        }

                    }

                if (mainview_str.length == number.length){

                    result = BigDecimal("0.0")
                    operation_selected = '+'

                }

                val operation_parameter = when (operation_selected) {

                    '+' -> '-'
                    '-' -> '+'
                    '×' -> '÷'

                    else -> '×'

                }

                if (mainview_str.length==number.length){
                    operation_selected = '+'
                }else{
                    CalculateNewValue(number.toDouble(),operation_parameter)
                }
                val temporary = number
                number = temporary
                main_view.text = mainview_str
                secondary_view.text = decimalFormat.format(Calculate(operation_selected,result)).toString()

            }else{

                // calculate new values
                if (number.isEmpty()) {

                    number = FindeNumberInEnd(mainview_str)
                    val start_index = number.length
                    val end_index = mainview_str.length

                    var previous_char = ' '
                    if (mainview_str.length == number.length){
                        result = BigDecimal("0.0")
                        previous_char = mainview_str.get(0)

                    }else{

                        previous_char = mainview_str.get( (end_index-start_index)-1 )

                    }

                    var ISSYMBOL = false
                    for (symbol in arrayOf('-','+','×','÷')){

                        if (previous_char == symbol){

                            ISSYMBOL = true
                            break

                        }

                    }

                    when (ISSYMBOL){

                        false->{

                            if (mainview_str.length==number.length){

                                result = BigDecimal("0.0")
                                operation_selected = '+'

                            }else{

                                operation_selected = number.get(0)
                                number = number.substring(1)

                            }

                        }


                    }

                }
                if (mainview_str.length == number.length){

                    result = BigDecimal("0.0")
                    operation_selected = '+'

                }


                main_view.text = mainview_str
                secondary_view.text = decimalFormat.format(Calculate(operation_selected,result)).toString()

            }


        }

    override fun EqualClickEvent(main_view: TextView, secondary_view: TextView , equal_button:Button) {

        var mainview_str = main_view.text.toString()
        RunAnimation(equal_button)
        if (mainview_str.last() in "+-×÷"){
            mainview_str = mainview_str.dropLast(1)
            number = "0"
        }

        val final_value = decimalFormat.format(Calculate(operation_selected,result)).toString()
        main_view.text =  final_value
        secondary_view.text = final_value

    }

}

