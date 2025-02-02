package com.learn.commpose.imageEditor.app

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.learn.commpose.R

@Composable
fun EditScreen(viewModel: SharedViewModel) {
    var newText by remember { mutableStateOf("") }

    var selectedTextId by remember { mutableStateOf<Int?>(null) }
    var sliderValue by remember { mutableFloatStateOf(16f) }

    val selectedText = viewModel.textItems.firstOrNull { it.id == selectedTextId }


    // सिलेक्टेड टेक्स्ट के साइज को अपडेट करें
    LaunchedEffect(selectedTextId) {
        viewModel.textItems.firstOrNull { it.id == selectedTextId }?.let {
            sliderValue = it.fontSize
        }
    }


    val frames = listOf(
        FrameItem(1, R.drawable.frame1),
        FrameItem(2, R.drawable.frame2),
        FrameItem(3, R.drawable.frame3)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // स्लाइडर और साइज इंडिकेटर
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Size: ${sliderValue.toInt()}sp", modifier = Modifier.width(80.dp))
            Slider(
                value = sliderValue,
                onValueChange = { newValue ->
                    sliderValue = newValue
                    selectedTextId?.let { id ->
                        viewModel.updateTextSize(id, newValue)
                    }
                },
                valueRange = 8f..48f,
                modifier = Modifier.weight(1f),
                enabled = selectedTextId != null
            )
        }

        // टेक्स्ट इनपुट फील्ड
        Row(modifier = Modifier.fillMaxWidth()) {
            TextField(
                value = newText,
                onValueChange = { newText = it },
                modifier = Modifier.weight(1f),
                placeholder = { Text("Enter text") }
            )
            Button(onClick = {
                if (newText.isNotBlank()) {
                    viewModel.addTextItem(newText)
                    newText = ""
                }
            }) {
                Text("Add Text")
            }
        }


        // कलर पिकर
        selectedText?.let {
            ColorPicker(
                selectedColor = it.color,
                onColorSelected = { newColor ->
                    viewModel.updateTextColor(it.id, newColor)
                }
            )
        }


        // Image with Frame
        Box(
            modifier = Modifier.size(300.dp)
        ) {
            viewModel.selectedImage.value?.let { image ->
                Image(
                    painter = painterResource(id = image.imageRes),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
            }

            viewModel.selectedFrame.value?.let { frame ->
                Image(
                    painter = painterResource(id = frame.frameRes),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
            }

            // ड्रैगेबल टेक्स्ट आइटम्स
            viewModel.textItems.forEach { textItem ->
                DraggableText(
                    textItem = textItem,
                    onPositionChange = { newOffset ->
                        viewModel.updateTextPosition(textItem.id, newOffset)
                    },
                    onTextSelected = { id ->
                        selectedTextId = id // सिलेक्टेड टेक्स्ट को ट्रैक करें
                    }
                )
            }

        }

        Spacer(modifier = Modifier.height(16.dp))

        // Frame List
        LazyRow {
            items(frames) { frame ->
                Image(
                    painter = painterResource(id = frame.frameRes),
                    contentDescription = null,
                    modifier = Modifier
                        .size(80.dp)
                        .clickable { viewModel.selectFrame(frame) }
                        .padding(4.dp)
                )
            }
        }
    }
}