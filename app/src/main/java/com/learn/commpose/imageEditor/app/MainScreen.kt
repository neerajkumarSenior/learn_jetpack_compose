package com.learn.commpose.imageEditor.app

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.learn.commpose.R

@Composable
fun MainScreen(navController: NavController, viewModel: SharedViewModel) {
    val images = listOf(
        ImageItem(1, R.drawable.image1),
        ImageItem(2, R.drawable.image2),
        ImageItem(3, R.drawable.image3)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Selected Image Box
        Box(
            modifier = Modifier
                .size(350.dp)
                .clickable { /* Handle image click */ }
                .border(1.dp, Color.Gray)
        ) {
            viewModel.selectedImage.value?.let { image ->
                Image(
                    painter = painterResource(id = image.imageRes),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )

                // Edit Icon
                IconButton(
                    onClick = { navController.navigate("editScreen") },
                    modifier = Modifier.align(Alignment.TopEnd)
                ) {
                    Icon(Icons.Default.Edit, "Edit")
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Image List
        LazyRow {
            items(images) { image ->
                Image(
                    painter = painterResource(id = image.imageRes),
                    contentDescription = null,
                    modifier = Modifier
                        .size(80.dp)
                        .clickable { viewModel.selectImage(image) }
                        .padding(4.dp)
                )
            }
        }
    }
}