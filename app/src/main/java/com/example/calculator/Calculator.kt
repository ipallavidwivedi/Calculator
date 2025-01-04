package com.example.calculator

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.background
import androidx.compose.ui.graphics.Brush

val buttonList = listOf(
    "C","(",")","/",
    "7","8","9","*",
    "4","5","6","-",
    "1","2","3","+",
    "AC","0",".","="
)

@Composable
fun Calculator(modifier: Modifier = Modifier, viewModel: CalculatorViewModel = CalculatorViewModel()) {

    val equationText = viewModel.equationText.observeAsState()
    val resultText = viewModel.resultText.observeAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF5C774E) , // Green
                        Color(0xFF070707), // Dark color
                    ),
                    startY = 5f, // Adjust the start position of the gradient
                )
            )
    ) {

        Column(
            modifier = modifier .fillMaxSize(),
            horizontalAlignment = Alignment.Start,
        ) {
            // Input Text Area
            Text(
                text = equationText.value ?: "",
                style = TextStyle(
                    fontSize = 30.sp,
                    textAlign = TextAlign.Start,
                ),
                maxLines = 5,
                overflow = TextOverflow.Ellipsis
            )

            // Spacer
            Spacer(modifier = Modifier.weight(1f))

            // Output Calculation
            Text(
                text = resultText.value ?: "",
                modifier = Modifier.padding(start = 10.dp),
                style = TextStyle(
                    fontSize = 60.sp,
                    textAlign = TextAlign.Start,
                ),
                maxLines = 3
            )

            // Spacer
            Spacer(modifier = Modifier.height(10.dp))

            // Keyboard
            LazyVerticalGrid(
                columns = GridCells.Fixed(4),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(buttonList) {
                    CalculatorButton(btn = it, onClick = { viewModel.onButtonClick(it) })
                }
            }

        }
    }


}



//Button Function
@Composable
fun CalculatorButton(btn: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(80.dp)

                .border(
                    width = 1.dp,
                    color = Color(0xFF4D4D4D), // Subtle gray border
                    shape = RoundedCornerShape(15.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            FloatingActionButton(
                onClick = onClick,
                modifier = Modifier.size(80.dp),
                shape = RoundedCornerShape(15.dp),
                containerColor = Color(0xFF202020),
                contentColor = Color.White
            ) {
                Text(
                    text = btn,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                    style = TextStyle(
                        fontSize = 28.sp,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        color = Color.White // Text color
                    )
                )
            }
        }
    }
}


fun getColor(btn: String): Color {
    if(btn == "=")
        return Color(0xFF5c774e)
    return Color(0xFF202020)

}