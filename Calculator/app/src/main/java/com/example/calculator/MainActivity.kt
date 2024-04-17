package com.example.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.calculator.ui.theme.CalculatorTheme
import com.example.calculator.ui.theme.MediumGrey

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculatorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                        val viewModel : CalculatorViewModel = viewModel()
                        val calcData = viewModel.CalculatorData
                        val buttonSpacing = 8.dp
                    Calculator(
                        data = calcData,
                        buttonSpacing = buttonSpacing,
                        onAction = viewModel::onAction,
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MediumGrey)
                            .padding(16.dp)
                    )
                }
            }
        }
    }
}

