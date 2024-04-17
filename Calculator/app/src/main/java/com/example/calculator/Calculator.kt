package com.example.calculator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculator.ui.theme.lightGrey
import com.example.calculator.ui.theme.orange
import java.nio.file.WatchEvent

@Composable
fun Calculator(
    modifier: Modifier = Modifier,
    data: CalculatorData,
    buttonSpacing : Dp,
    onAction : (CalculatorActions) -> Unit
) {
    Box (modifier = modifier){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            verticalArrangement = Arrangement.spacedBy(buttonSpacing)
        ) {
            Text(text = data.number1 + (data.operation?.symbol ?: "") + data.number2,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 32.dp),
                fontWeight = FontWeight.Light,
                fontSize = 80.sp,
                color = Color.White,
                maxLines = 2)

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ){
                CalculatorButton(
                    symbol = "AC",
                    modifier = Modifier.background(lightGrey)
                        .aspectRatio(2f)
                        .weight(2f),
                    onClick = {onAction(CalculatorActions.Clear)}
                )
                CalculatorButton(
                    symbol = "Del",
                    modifier = Modifier.background(lightGrey)
                        .aspectRatio(1f)
                        .weight(1f),
                    onClick = {onAction(CalculatorActions.Delete)}
                )
                CalculatorButton(
                    symbol = "/",
                    modifier = Modifier.background(orange)
                        .aspectRatio(1f)
                        .weight(1f),
                    onClick = {onAction(CalculatorActions.Operations(Operation.Devide))}
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ){
                CalculatorButton(
                    symbol = "7",
                    modifier = Modifier.background(Color.DarkGray)
                        .aspectRatio(1f)
                        .weight(1f),
                    onClick = {onAction(CalculatorActions.Number(7))}
                )
                CalculatorButton(
                    symbol = "8",
                    modifier = Modifier.background(Color.DarkGray)
                        .aspectRatio(1f)
                        .weight(1f),
                    onClick = {onAction(CalculatorActions.Number(8))}
                )
                CalculatorButton(
                    symbol = "9",
                    modifier = Modifier.background(Color.DarkGray)
                        .aspectRatio(1f)
                        .weight(1f),
                    onClick = {onAction(CalculatorActions.Number(9))}
                )
                CalculatorButton(
                    symbol = "*",
                    modifier = Modifier.background(orange)
                        .aspectRatio(1f)
                        .weight(1f),
                    onClick = {onAction(CalculatorActions.Operations(Operation.Multiply))}
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ){
                CalculatorButton(
                    symbol = "4",
                    modifier = Modifier.background(Color.DarkGray)
                        .aspectRatio(1f)
                        .weight(1f),
                    onClick = {onAction(CalculatorActions.Number(4))}
                )
                CalculatorButton(
                    symbol = "5",
                    modifier = Modifier.background(Color.DarkGray)
                        .aspectRatio(1f)
                        .weight(1f),
                    onClick = {onAction(CalculatorActions.Number(5))}
                )
                CalculatorButton(
                    symbol = "6",
                    modifier = Modifier.background(Color.DarkGray)
                        .aspectRatio(1f)
                        .weight(1f),
                    onClick = {onAction(CalculatorActions.Number(6))}
                )
                CalculatorButton(
                    symbol = "-",
                    modifier = Modifier.background(orange)
                        .aspectRatio(1f)
                        .weight(1f),
                    onClick = {onAction(CalculatorActions.Operations(Operation.Subtract))}
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ){
                CalculatorButton(
                    symbol = "1",
                    modifier = Modifier.background(Color.DarkGray)
                        .aspectRatio(1f)
                        .weight(1f),
                    onClick = {onAction(CalculatorActions.Number(1))}
                )
                CalculatorButton(
                    symbol = "2",
                    modifier = Modifier.background(Color.DarkGray)
                        .aspectRatio(1f)
                        .weight(1f),
                    onClick = {onAction(CalculatorActions.Number(2))}
                )
                CalculatorButton(
                    symbol = "3",
                    modifier = Modifier.background(Color.DarkGray)
                        .aspectRatio(1f)
                        .weight(1f),
                    onClick = {onAction(CalculatorActions.Number(3))}
                )
                CalculatorButton(
                    symbol = "+",
                    modifier = Modifier.background(orange)
                        .aspectRatio(1f)
                        .weight(1f),
                    onClick = {onAction(CalculatorActions.Operations(Operation.Add))}
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ){
                CalculatorButton(
                    symbol = "0",
                    modifier = Modifier.background(Color.DarkGray)
                        .aspectRatio(2f)
                        .weight(2f),
                    onClick = {onAction(CalculatorActions.Number(0))}
                )
                CalculatorButton(
                    symbol = ".",
                    modifier = Modifier.background(Color.DarkGray)
                        .aspectRatio(1f)
                        .weight(1f),
                    onClick = {onAction(CalculatorActions.Decimal)}
                )
                CalculatorButton(
                    symbol = "=",
                    modifier = Modifier.background(orange)
                        .aspectRatio(1f)
                        .weight(1f),
                    onClick = {onAction(CalculatorActions.Calculate)}
                )
            }
        }
    }
}