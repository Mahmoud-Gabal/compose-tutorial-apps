package com.example.calculator

sealed class CalculatorActions {
    data class Number(val num : Int) : CalculatorActions()
    object Clear : CalculatorActions()
    object Delete : CalculatorActions()
    object Decimal : CalculatorActions()
    object Calculate : CalculatorActions()
    data class Operations( val operation : Operation) : CalculatorActions()
}
sealed class Operation(val symbol  : String) {
    object Add : Operation("+")
    object Multiply : Operation("*")
    object Devide : Operation("/")
    object Subtract : Operation("-")
}