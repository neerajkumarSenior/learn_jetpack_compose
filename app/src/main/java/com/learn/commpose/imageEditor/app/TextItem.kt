package com.learn.commpose.imageEditor.app

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color

data class TextItem(
    val id: Int,
    val text: String,
    val offset: Offset = Offset.Zero,
    val color: Color = Color.White,
    val fontSize: Float = 16f // नया फॉन्ट साइज प्रॉपर्टी

)