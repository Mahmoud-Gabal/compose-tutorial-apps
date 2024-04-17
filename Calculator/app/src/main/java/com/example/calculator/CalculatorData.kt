package com.example.calculator

data class CalculatorData(
    val number1 : String = "",
    val number2 : String = "",
    val operation : Operation ?= null
)
