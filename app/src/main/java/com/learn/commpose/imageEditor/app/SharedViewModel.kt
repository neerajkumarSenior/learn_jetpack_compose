package com.learn.commpose.imageEditor.app

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    private val _selectedImage = mutableStateOf<ImageItem?>(null)
    val selectedImage: State<ImageItem?> = _selectedImage

    private val _selectedFrame = mutableStateOf<FrameItem?>(null)
    val selectedFrame: State<FrameItem?> = _selectedFrame

    fun selectImage(image: ImageItem) {
        _selectedImage.value = image
    }

    fun selectFrame(frame: FrameItem) {
        _selectedFrame.value = frame
    }


    // for text dragable

    private val _textItems = mutableStateListOf<TextItem>()
    val textItems: List<TextItem> = _textItems

    private var currentTextId = 0

    fun addTextItem(text: String) {
        _textItems.add(TextItem(id = currentTextId++, text = text))
    }

    fun updateTextPosition(id: Int, newOffset: Offset) {
        _textItems.replaceAll {
            if (it.id == id) it.copy(offset = newOffset) else it
        }
    }

    // for font size of text

    fun updateTextSize(id: Int, newSize: Float) {
        _textItems.replaceAll {
            if (it.id == id) it.copy(fontSize = newSize) else it
        }
    }

    // add text color

    fun updateTextColor(id: Int, newColor: Color) {
        _textItems.replaceAll {
            if (it.id == id) it.copy(color = newColor) else it
        }
    }



}