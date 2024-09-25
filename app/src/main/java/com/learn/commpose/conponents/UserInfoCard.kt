package com.learn.commpose.conponents

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.learn.commpose.model.Post

@Composable
fun UserInfoCard(user: Post) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp)
            .border(1.dp, Color.White, RoundedCornerShape(8.dp)),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.elevatedCardColors()

    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),

            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Name: ${user.name}",
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Red
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Email: ${user.phone}",
                fontSize = 11.sp,
                color = Color.Black
            )
        }
    }
}