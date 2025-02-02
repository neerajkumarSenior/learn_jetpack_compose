//package com.learn.commpose
//
//
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.border
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.gestures.detectTransformGestures
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.offset
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.foundation.text.BasicTextField
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Add
//import androidx.compose.material.icons.filled.Delete
//import androidx.compose.material.icons.filled.Home
//import androidx.compose.material.icons.filled.KeyboardArrowLeft
//import androidx.compose.material.icons.filled.KeyboardArrowRight
//import androidx.compose.material3.Button
//import androidx.compose.material3.Icon
//import androidx.compose.material3.IconButton
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateListOf
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.geometry.Offset
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.graphicsLayer
//import androidx.compose.ui.input.pointer.pointerInput
//import androidx.compose.ui.text.TextStyle
//import androidx.compose.ui.text.input.TextFieldValue
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.unit.IntOffset
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import coil.compose.rememberAsyncImagePainter
//import kotlin.random.Random
//
//@Composable
//fun InstagramStoryEditor() {
//    val textElements = remember { mutableStateListOf<StoryTextElement>() }
//    var selectedElementId by remember { mutableStateOf<Int?>(null) }
//    var currentColor by remember { mutableStateOf(Color.White) }
//    var currentAlignment by remember { mutableStateOf(TextAlign.Center) }
//    val imagePainter = rememberAsyncImagePainter("https://images.unsplash.com/photo-1606474226448-4aa808468efc?q=80&w=1990&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D")
//
//    Box(modifier = Modifier.fillMaxSize()) {
//        Image(
//            painter = imagePainter,
//            contentDescription = "Story background",
//            modifier = Modifier.fillMaxSize()
//        )
//
//        // Drag & Editable Text Elements
//        textElements.forEach { element ->
//            StoryTextElement(
//                element = element,
//                isSelected = element.id == selectedElementId,
//                onSelect = { selectedElementId = element.id },
//                onUpdate = { updatedElement ->
//                    textElements.replaceAll { if (it.id == updatedElement.id) updatedElement else it }
//                },
//                onRemove = { textElements.remove(element) }
//            )
//        }
//
//        // Controls
//        StoryControls(
//            onAddText = {
//                val newElement = StoryTextElement(
//                    id = Random.nextInt(),
//                    text = "Tap to edit",
//                    color = currentColor,
//                    alignment = currentAlignment,
//                    offset = Offset(0.5f, 0.5f),
//                    scale = 1f,
//                    rotation = 0f
//                )
//                textElements.add(newElement)
//                selectedElementId = newElement.id
//            },
//            onColorChange = { currentColor = it },
//            onAlignmentChange = { currentAlignment = it },
//            modifier = Modifier.align(Alignment.TopCenter)
//        )
//
//        // Done Button
//        Button(
//            onClick = { /* Handle story post */ },
//            modifier = Modifier
//                .align(Alignment.BottomEnd)
//                .padding(16.dp)
//        ) {
//            Text("Share")
//        }
//    }
//}
//
//@Composable
//fun StoryTextElement(
//    element: StoryTextElement,
//    isSelected: Boolean,
//    onSelect: () -> Unit,
//    onUpdate: (StoryTextElement) -> Unit,
//    onRemove: () -> Unit // Now we will use this!
//) {
//    var position by remember { mutableStateOf(element.offset) }
//    var scale by remember { mutableStateOf(element.scale) }
//    var rotation by remember { mutableStateOf(element.rotation) }
//
//    Box(
//        modifier = Modifier
//            .offset { IntOffset((position.x * 1000).toInt(), (position.y * 2000).toInt()) }
//            .graphicsLayer(
//                scaleX = scale,
//                scaleY = scale,
//                rotationZ = rotation
//            )
//            .pointerInput(Unit) {
//                detectTransformGestures { _, pan, zoom, rotate ->
//                    onSelect()
//                    position = Offset(
//                        (position.x + pan.x / 1000).coerceIn(0f, 1f),
//                        (position.y + pan.y / 2000).coerceIn(0f, 1f)
//                    )
//                    scale = (scale * zoom).coerceIn(0.5f, 3f)
//                    rotation += rotate
//
//                    onUpdate(element.copy(offset = position, scale = scale, rotation = rotation))
//                }
//            }
//    ) {
//        var text by remember { mutableStateOf(TextFieldValue(element.text)) }
//
//        Row(verticalAlignment = Alignment.CenterVertically) {
//            BasicTextField(
//                value = text,
//                onValueChange = {
//                    text = it
//                    onUpdate(element.copy(text = it.text))
//                },
//                textStyle = TextStyle(
//                    color = element.color,
//                    textAlign = element.alignment,
//                    fontSize = 24.sp
//                ),
//                modifier = Modifier
//                    .background(
//                        color = if (isSelected) Color.Black.copy(alpha = 0.3f) else Color.Transparent,
//                        shape = RoundedCornerShape(4.dp)
//                    )
//                    .border(
//                        width = if (isSelected) 2.dp else 0.dp,
//                        color = Color.White.copy(alpha = 0.5f),
//                        shape = RoundedCornerShape(4.dp)
//                    )
//                    .padding(8.dp)
//            )
//
//            if (isSelected) {
//                IconButton(
//                    onClick = { onRemove() },
//                    modifier = Modifier.padding(start = 8.dp)
//                ) {
//                    Icon(
//                        imageVector = Icons.Default.Delete,
//                        contentDescription = "Delete",
//                        tint = Color.Red
//                    )
//                }
//            }
//        }
//    }
//}
//
//
//
//
//
//data class StoryTextElement(
//    val id: Int,
//    var text: String,
//    var color: Color,
//    var alignment: TextAlign,
//    var offset: Offset, // Normalized position (0-1)
//    var scale: Float,
//    var rotation: Float
//)
//
//
//
//
//
//
//@Composable
//fun StoryControls(
//    onAddText: () -> Unit,
//    onColorChange: (Color) -> Unit,
//    onAlignmentChange: (TextAlign) -> Unit,
//    modifier: Modifier = Modifier
//) {
//    Row(
//        modifier = modifier
//            .fillMaxWidth()
//            .padding(16.dp),
//        horizontalArrangement = Arrangement.SpaceBetween
//    ) {
//        // Color picker
//        Row {
//            listOf(Color.White, Color.Red, Color.Yellow, Color.Blue).forEach { color ->
//                Box(
//                    modifier = Modifier
//                        .size(32.dp)
//                        .background(color, CircleShape)
//                        .clickable { onColorChange(color) }
//                        .border(2.dp, Color.White, CircleShape)
//                        .padding(2.dp)
//                )
//                Spacer(modifier = Modifier.width(8.dp))
//            }
//        }
//
//        // Alignment controls
//        Row {
//            IconButton(onClick = { onAlignmentChange(TextAlign.Left) }) {
//                Icon(Icons.Default.KeyboardArrowLeft, "Left align")
//            }
//            IconButton(onClick = { onAlignmentChange(TextAlign.Center) }) {
//                Icon(Icons.Default.Home, "Center align")
//            }
//            IconButton(onClick = { onAlignmentChange(TextAlign.Right) }) {
//                Icon(Icons.Default.KeyboardArrowRight, "Right align")
//            }
//        }
//
//        // Add text button
//        IconButton(onClick = onAddText) {
//            Icon(Icons.Default.Add, "Add text")
//        }
//    }
//}


import android.content.ContentValues
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import kotlin.random.Random



// Add these imports
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Color as AndroidColor
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.provider.MediaStore
import androidx.compose.ui.graphics.toArgb
import androidx.core.graphics.drawable.toBitmap
import coil.ImageLoader
import coil.request.SuccessResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.OutputStream


// Function to draw text elements onto the bitmap
@Composable
private fun drawTextElementOnBitmap(
    element: StoryTextElement,
    canvas: Canvas,
    imageWidth: Int,
    imageHeight: Int
) {
    val density = LocalDensity.current
    val textPaint = Paint().apply {
        color = element.color.toArgb()
        textSize = with(density) { element.fontSize.toPx() } // Convert TextUnit to pixels
        textAlign = when (element.alignment) {
            TextAlign.Left -> Paint.Align.LEFT
            TextAlign.Center -> Paint.Align.CENTER
            TextAlign.Right -> Paint.Align.RIGHT
            else -> Paint.Align.LEFT
        }
        isAntiAlias = true
        if (element.textShadow) {
            setShadowLayer(4f, 2f, 2f, AndroidColor.BLACK)
        }
    }

    // Calculate position
    val xPos = element.offset.x * imageWidth
    val yPos = element.offset.y * imageHeight

    // Text measurement
    val text = element.text
    val rect = android.graphics.Rect()
    val chars = text.toCharArray()

    // Get text bounds properly
    textPaint.getTextBounds(chars, 0, chars.size, rect)

    canvas.save()
    // Apply translation, scaling, and rotation to the canvas
    canvas.translate(xPos, yPos)
    canvas.scale(element.scale, element.scale)
    canvas.rotate(element.rotation)

    // Draw background (if any)
    element.textBackground?.let { bgColor ->
        val bgPaint = Paint().apply {
            color = bgColor.toArgb()
            style = Paint.Style.FILL
        }
        val padding = 10f
        canvas.drawRect(
            rect.left.toFloat() - padding,
            rect.top.toFloat() - padding,
            rect.right.toFloat() + padding,
            rect.bottom.toFloat() + padding,
            bgPaint
        )
    }

    // Draw the text
    canvas.drawText(text, -rect.exactCenterX(), -rect.exactCenterY(), textPaint)
    canvas.restore()
}

// Function to save the bitmap to the gallery
private suspend fun saveBitmapToGallery(
    context: Context,
    bitmap: Bitmap
): Boolean = withContext(Dispatchers.IO) {
    try {
        val contentResolver = context.contentResolver
        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, "story_${System.currentTimeMillis()}.jpg")
            put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
            put(MediaStore.MediaColumns.RELATIVE_PATH, "Pictures/YourAppName")
        }

        val uri = contentResolver.insert(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            contentValues
        ) ?: return@withContext false

        contentResolver.openOutputStream(uri)?.use { outputStream: OutputStream ->
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, outputStream)
        }

        true
    } catch (e: Exception) {
        false
    }
}





@Composable
fun InstagramStoryEditor() {
    val textElements = remember { mutableStateListOf<StoryTextElement>() }
    var selectedElementId by remember { mutableStateOf<Int?>(null) }
    var currentColor by remember { mutableStateOf(Color.White) }
    var currentAlignment by remember { mutableStateOf(TextAlign.Center) }
    var currentFontSize by remember { mutableStateOf(24.sp) }
    var textShadowEnabled by remember { mutableStateOf(false) }
    var textBackgroundEnabled by remember { mutableStateOf(false) }

    val imagePainter = rememberAsyncImagePainter(
        ImageRequest.Builder(LocalContext.current)
            .data("https://images.unsplash.com/photo-1606474226448-4aa808468efc")
            .build()
    )

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = imagePainter,
            contentDescription = "Story background",
            modifier = Modifier.fillMaxSize()
        )

        textElements.forEach { element ->
            StoryTextElement(
                element = element,
                isSelected = element.id == selectedElementId,
                onSelect = { selectedElementId = element.id },
                onUpdate = { updatedElement ->
                    textElements.replaceAll { if (it.id == updatedElement.id) updatedElement else it }
                },
                onRemove = { textElements.remove(element) }
            )
        }

        StoryControls(
            currentColor = currentColor,
            onAddText = {
                val newElement = StoryTextElement(
                    id = Random.nextInt(),
                    text = "",
                    color = currentColor,
                    alignment = currentAlignment,
                    offset = Offset(0.5f, 0.5f),
                    scale = 1f,
                    rotation = 0f,
                    fontSize = currentFontSize,
                    fontWeight = FontWeight.Normal,
                    textShadow = textShadowEnabled,
                    textBackground = if (textBackgroundEnabled) Color.Black.copy(alpha = 0.3f) else null
                )
                textElements.add(newElement)
                selectedElementId = newElement.id
            },
            onColorChange = { currentColor = it },
            onAlignmentChange = { currentAlignment = it },
            onFontSizeChange = {   currentFontSize = (currentFontSize.value + it)
                .coerceIn(12f, 48f)  // Apply to Float first
                .sp },
            onTextShadowToggle = { textShadowEnabled = it },
            onTextBackgroundToggle = { textBackgroundEnabled = it },
            modifier = Modifier.align(Alignment.TopCenter)
        )

        Button(
            onClick = { /* Handle story post */ },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            )
        ) {
            Text("Share")
        }
    }
}

@Composable
fun StoryTextElement(
    element: StoryTextElement,
    isSelected: Boolean,
    onSelect: () -> Unit,
    onUpdate: (StoryTextElement) -> Unit,
    onRemove: () -> Unit
) {
    val density = LocalDensity.current
    var containerSize by remember { mutableStateOf(IntSize.Zero) }
    val focusRequester = remember { FocusRequester() }

    // **🔹 State for drag position, scale, and rotation**
    var offset by remember { mutableStateOf(element.offset) }
    var scale by remember { mutableStateOf(element.scale) }
    var rotation by remember { mutableStateOf(element.rotation) }

    // **🔹 Set initial position to center when container is measured**
    LaunchedEffect(containerSize) {
        if (containerSize.width > 0 && containerSize.height > 0) {
            offset = Offset(
                (containerSize.width / 2f) - 50f, // Center horizontally
                (containerSize.height / 2f) - 50f // Center vertically
            )
        }
    }

    LaunchedEffect(isSelected) {
        if (isSelected) focusRequester.requestFocus()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .onSizeChanged { containerSize = it } // **Detect container size**
    ) {
        Box(
            modifier = Modifier
                .offset { IntOffset(offset.x.toInt(), offset.y.toInt()) }
                .graphicsLayer {
                    scaleX = scale
                    scaleY = scale
                    rotationZ = rotation
                }
                .pointerInput(Unit) {
                    detectTransformGestures { _, pan, zoom, rotationChange ->
                        onSelect()

                        // **🔹 Scale within limits (0.5x to 5x)**
                        scale = (scale * zoom).coerceIn(0.5f, 5f)

                        // **🔹 Rotation within 0-360 degrees**
                        rotation = (rotation + rotationChange) % 360

                        // **🔹 Apply pan with boundary checks**
                        val newX = (offset.x + pan.x).coerceIn(0f, containerSize.width.toFloat() - 100f)
                        val newY = (offset.y + pan.y).coerceIn(0f, containerSize.height.toFloat() - 100f)
                        offset = Offset(newX, newY)

                        onUpdate(element.copy(scale = scale, rotation = rotation, offset = offset))
                    }
                }
        ) {
            var text by remember { mutableStateOf(element.text) }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                BasicTextField(
                    value = text,
                    onValueChange = {
                        text = it
                        onUpdate(element.copy(text = it))
                    },
                    textStyle = TextStyle(
                        color = element.color,
                        textAlign = element.alignment,
                        fontSize = element.fontSize,
                        fontWeight = element.fontWeight
                    ),
                    modifier = Modifier
                        .focusRequester(focusRequester)
                        .background(
                            color = element.textBackground ?: Color.Transparent,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .border(
                            width = if (isSelected) 2.dp else 0.dp,
                            color = Color.White.copy(alpha = 0.5f),
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(12.dp)
                )

                if (isSelected) {
                    Row(
                        modifier = Modifier.padding(top = 8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        IconButton(
                            onClick = { rotation = 0f },
                            modifier = Modifier
                                .size(32.dp)
                                .background(MaterialTheme.colorScheme.primary, CircleShape)
                        ) {
                            Icon(
                                Icons.Default.Refresh,
                                contentDescription = "Reset rotation",
                                tint = Color.White
                            )
                        }

                        IconButton(
                            onClick = onRemove,
                            modifier = Modifier
                                .size(32.dp)
                                .background(Color.Red, CircleShape)
                        ) {
                            Icon(
                                Icons.Default.Delete,
                                contentDescription = "Delete",
                                tint = Color.White
                            )
                        }
                    }
                }
            }
        }
    }
}




data class StoryTextElement(
    val id: Int,
    var text: String,
    var color: Color,
    var alignment: TextAlign,
    var offset: Offset,
    var scale: Float,
    var rotation: Float,
    var fontSize: TextUnit,
    var fontWeight: FontWeight,
    var textShadow: Boolean,
    var textBackground: Color?
)

@Composable
fun StoryControls(
    onAddText: () -> Unit,
    currentColor: Color, // Add this parameter

    onColorChange: (Color) -> Unit,
    onAlignmentChange: (TextAlign) -> Unit,
    onFontSizeChange: (Float) -> Unit,
    onTextShadowToggle: (Boolean) -> Unit,
    onTextBackgroundToggle: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    var textShadowEnabled by remember { mutableStateOf(false) }
    var textBackgroundEnabled by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Color Picker
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            listOf(
                Color.White,
                Color.Red,
                Color(0xFFFFD600), // Yellow
                Color.Blue,
                Color(0xFFFF00FF), // Magenta
                Color.Black
            ).forEach { color ->
                ColorCircle(
                    color = color,
                    isSelected = color == currentColor,
                    onSelected = { onColorChange(color) }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Controls Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Alignment
            Row {
                IconButton(onClick = { onAlignmentChange(TextAlign.Left) }) {
                    Icon(Icons.Default.KeyboardArrowLeft, "Left align")
                }
                IconButton(onClick = { onAlignmentChange(TextAlign.Center) }) {
                    Icon(Icons.Default.FavoriteBorder, "Center align")
                }
                IconButton(onClick = { onAlignmentChange(TextAlign.Right) }) {
                    Icon(Icons.Default.KeyboardArrowRight, "Right align")
                }
            }

            // Font Size
            Row {
                IconButton(onClick = { onFontSizeChange(-2f) }) {
                    Icon(Icons.Filled.ThumbUp, "Decrease size")
                }
                IconButton(onClick = { onFontSizeChange(2f) }) {
                    Icon(Icons.Default.KeyboardArrowDown, "Increase size")
                }
            }

            // Effects
            Row {
                IconButton(onClick = {
                    textShadowEnabled = !textShadowEnabled
                    onTextShadowToggle(textShadowEnabled)
                }) {
                    Icon(
                        Icons.Default.Info,
                        "Shadow",
                        tint = if (textShadowEnabled) MaterialTheme.colorScheme.primary else Color.White
                    )
                }
                IconButton(onClick = {
                    textBackgroundEnabled = !textBackgroundEnabled
                    onTextBackgroundToggle(textBackgroundEnabled)
                }) {
                    Icon(
                        Icons.Default.ShoppingCart,
                        "Background",
                        tint = if (textBackgroundEnabled) MaterialTheme.colorScheme.primary else Color.White
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Add Text Button
        Button(
            onClick = onAddText,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = Color.White
            ),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Edit, "Add text")
                Spacer(modifier = Modifier.width(8.dp))
                Text("Add Text", style = MaterialTheme.typography.titleMedium)
            }
        }
    }
}

@Composable
fun ColorCircle(
    color: Color,
    isSelected: Boolean,
    onSelected: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(32.dp)
            .clip(CircleShape)
            .background(color)
            .border(
                2.dp,
                if (isSelected) MaterialTheme.colorScheme.primary else Color.White,
                CircleShape
            )
            .clickable(onClick = onSelected)
    )
}

