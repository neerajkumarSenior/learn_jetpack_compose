package com.learn.commpose.imageEditor.app

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ColorPicker(
    selectedColor: Color,
    onColorSelected: (Color) -> Unit
) {
    val colors = listOf(
        Color.White, Color.Black, Color.Red,
        Color.Blue, Color.Green, Color.Yellow,
        Color.Magenta, Color.Cyan, Color.Gray
    )

    Column(modifier = Modifier.padding(8.dp)) {
        Text("Select Color", style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow {
            items(colors.size) { index ->
                val color = colors[index]
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .padding(4.dp)
                        .border(
                            2.dp,
                            if (color == selectedColor) Color.White else Color.Transparent,
                            CircleShape
                        )
                        .background(color, CircleShape)
                        .clickable { onColorSelected(color) }
                )
            }
        }

    }
}