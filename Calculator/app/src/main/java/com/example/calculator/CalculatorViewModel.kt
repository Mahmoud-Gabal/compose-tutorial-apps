package com.example.calculator

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class CalculatorViewModel : ViewModel() {
    var CalculatorData by mutableStateOf(CalculatorData())
        private set
    fun onAction(action : CalculatorActions){
        when(action){
            is CalculatorActions.Number -> enterNumber(action.num)
            is CalculatorActions.Clear -> CalculatorData = CalculatorData()
            is CalculatorActions.Operations -> performOperation(action.operation)
            is CalculatorActions.Calculate -> calculateResult()
            is CalculatorActions.Decimal -> enterDecimal()
            is CalculatorActions.Delete -> performDeletion()
        }

    }

    private fun performDeletion() {
        when{
            CalculatorData.number2.isNotBlank() ->
                CalculatorData = CalculatorData.copy(number2 = CalculatorData.number2.dropLast(1))

            CalculatorData.operation != null ->
                CalculatorData = CalculatorData.copy(operation = null)

            CalculatorData.number1.isNotBlank() ->
                CalculatorData = CalculatorData.copy(number1 = CalculatorData.number1.dropLast(1))
        }
    }

    private fun enterDecimal() {
        if (CalculatorData.operation == null &&
            !CalculatorData.number1.contains(".") &&
            CalculatorData.number1.isNotBlank()){

            CalculatorData = CalculatorData.copy(number1 = CalculatorData.number1 + ".")
            return
        }
        if (!CalculatorData.number2.contains(".") &&
            CalculatorData.number2.isNotBlank()){

            CalculatorData = CalculatorData.copy(number2 = CalculatorData.number2 + ".")

        }
    }

    private fun calculateResult() {
        val number1 = CalculatorData.number1.toDoubleOrNull()
        val number2 = CalculatorData.number2.toDoubleOrNull()
        if (number1 != null && number2 != null){
            val result = when(CalculatorData.operation){
                is Operation.Add -> number1 + number2
                is Operation.Subtract -> number1 - number2
                is Operation.Multiply -> number1 * number2
                is Operation.Devide -> number1 / number2
                null -> return
            }
            CalculatorData = CalculatorData.copy(
                number1 = result.toString().take(15),
                number2 = "",
                operation = null
            )
        }
    }

    private fun performOperation(operation: Operation) {
        if (CalculatorData.number1.isNotBlank()){
            CalculatorData = CalculatorData.copy(operation = operation)
        }
    }

    private fun enterNumber(num: Int) {
        if (CalculatorData.operation == null){
            if (CalculatorData.number1.length >= 8){
                return
            }
            CalculatorData = CalculatorData.copy(number1 = CalculatorData.number1 + num)
            return
        }
        if (CalculatorData.number2.length >= 8){
            return
        }
        CalculatorData = CalculatorData.copy(number2 = CalculatorData.number2 + num)
    }
}