package com.learn.commpose.imageEditor.app

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.roundToInt

@Composable
fun DraggableText(
    textItem: TextItem,
    onPositionChange: (Offset) -> Unit,
    onTextSelected: (Int) -> Unit // नया कॉलबैक

) {
    var offset by remember { mutableStateOf(textItem.offset) }

    //val borderColor = if (isSelected) Color.Red else Color.Transparent


    Box(
        modifier = Modifier
            .offset {
                IntOffset(offset.x.roundToInt(), offset.y.roundToInt())
            }
            .pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    offset += dragAmount
                    onPositionChange(offset)
                }
            }.clickable { onTextSelected(textItem.id) } // टेक्स्ट सिलेक्शन
            .background(Color.Transparent)
            .padding(4.dp)
    ) {
        Text(
            text = textItem.text,
            color = textItem.color,
            fontSize = textItem.fontSize.sp, // स्लाइडर वैल्यू का उपयोग

            modifier = Modifier.pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    offset += dragAmount
                    onPositionChange(offset)
                }
            }
        )
    }
}