package com.learn.commpose

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.learn.commpose.screens.UserScreen
import com.learn.commpose.ui.theme.LearnComposeTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.exp

class MainActivity : ComponentActivity() {
    @SuppressLint("InvalidColorHexValue")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            UserScreen()

//            LearnComposeTheme {
//                Scaffold(modifier = Modifier
//                    .fillMaxSize()
//                    .padding(top = 24.dp),
////                    topBar = {
////
////
////                        Row(
////                            modifier = Modifier
////                                .height(65.dp)
////                                .fillMaxWidth()
////
////                                .background(color = Color.Yellow),
////                            verticalAlignment = Alignment.CenterVertically,
////                            horizontalArrangement = Arrangement.SpaceEvenly
////                        ) {
////
////
////                          ToolBarIcon(icon = R.drawable.baseline_home_24)
////                          ToolBarIcon(icon = R.drawable.baseline_view_in_ar_24)
////                          ToolBarIcon(icon = R.drawable.baseline_notifications_24)
////                          ToolBarIcon(icon = R.drawable.baseline_settings_24)
////
////
////                        }
////                    },
//                     bottomBar = {
//
//                        Row(
//                            modifier = Modifier
//                                .height(67.dp)
//                                .fillMaxWidth()
////
//                                .background(color = Color.Yellow),
//                            verticalAlignment = Alignment.CenterVertically,
//                            horizontalArrangement = Arrangement.SpaceEvenly
//                        ) {
//
//
//                            ToolBarIcon(icon = R.drawable.baseline_home_24)
//                            ToolBarIcon(icon = R.drawable.baseline_view_in_ar_24)
//                            ToolBarIcon(icon = R.drawable.baseline_notifications_24)
//                            ToolBarIcon(icon = R.drawable.baseline_settings_24)
//
//
//                        }
//
//                    }
//
//
//                ) { innerPadding ->
//
//                    Box(
//                        modifier = Modifier
//
//                            .fillMaxWidth()
//                            .size(250.dp)
//
//                            .background(
//                                brush = Brush.linearGradient(
//                                    listOf(Color.Red, Color.Black),
//                                    start = Offset(0f, 0f),
//                                    end = Offset(500f, 500f)
//                                ),
//                                shape = RoundedCornerShape(bottomStart = 30.dp, bottomEnd = 30.dp)
//                            ),
//
//
//                        contentAlignment = Alignment.Center
//                    ) {
//
//                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
//
//                            Row(
//                                modifier = Modifier
//                                    .padding(horizontal = 25.dp)
//                                    .fillMaxWidth(),
//                                horizontalArrangement = Arrangement.SpaceBetween
//                            ) {
//                                Home("A", Color.White)
//                                Home("B", Color.White)
//                                Home("A", Color.White)
//                                Home("B", Color.White)
//
//
//                            }
//
//
//                            Home(text = "Z", Color.White)
//                            Home(text = "Z", Color.White)
//                            Home(text = "Z", Color.White)
//                            Home(text = "Z", Color.White)
//
//
//                        }
//
//
//                    }
//
//
//                    Box(
//                        modifier = Modifier // Alignment ko adjust kar ke position set karen
//
//                            .fillMaxWidth(),
//
//
//                    ) {
//                        // Pehla Box
//                        Box(
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .size(250.dp)
//                                .background(
//                                    brush = Brush.linearGradient(
//                                        listOf(Color.Red, Color.Black),
//                                        start = Offset(0f, 0f),
//                                        end = Offset(500f, 500f)
//                                    ),
//                                    shape = RoundedCornerShape(
//                                        bottomStart = 20.dp,
//                                        bottomEnd = 20.dp
//                                    )
//                                ),
//                            contentAlignment = Alignment.Center
//                        ) {
//                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
//                                Row(
//                                    modifier = Modifier
//                                        .padding(horizontal = 25.dp)
//                                        .fillMaxWidth(),
//                                    horizontalArrangement = Arrangement.SpaceBetween
//                                ) {
//                                    Home("A", Color.White)
////                                    Home("B", Color.White)
////                                    Home("A", Color.White)
////                                    Home("B", Color.White)
//                                }
//                                Home(text = "Z", Color.White)
////                                Home(text = "Z", Color.White)
////                                Home(text = "Z", Color.White)
////                                Home(text = "Z", Color.White)
//                            }
//                        }
//
//                        // Overlapping Box
//                        Box(
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .height(150.dp)
//                                .padding(horizontal = 20.dp)
//
//                                // Alignment ko adjust kar ke position set karen
//                                .offset(y = 200.dp) // Yeh offset red/black box ke neeche laane ke liye hai
//
//                                .background(Color(0xFFffd3ca), shape = RoundedCornerShape(15.dp)),
//                            contentAlignment = Alignment.Center
//                        ) {
//                           Row(modifier = Modifier
//                               .padding(15.dp)
//                               .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
//                               ToolBarIcon(icon = R.drawable.baseline_home_24)
//                               ToolBarIcon(icon = R.drawable.baseline_view_in_ar_24)
//                               ToolBarIcon(icon = R.drawable.baseline_notifications_24)
//                               ToolBarIcon(icon = R.drawable.baseline_settings_24)
//                           }
//                        }
//                    }
//
//
//
//
//                }
//            }
        }
    }
}




@Composable
fun Home(text: String, color: Color) {
    val context = LocalContext.current

    Text(
        text = text,
        modifier = Modifier
            .padding(8.dp)


            .clickable {
                Toast
                    .makeText(context, text, Toast.LENGTH_LONG)
                    .show()

            },

        color = color,
        fontSize = 25.sp,
    )


}





@Composable
fun ToolBarIcon(icon: Int){

    Column(horizontalAlignment = Alignment.CenterHorizontally) {



            Icon(modifier = Modifier
                .background(color = Color.White, shape = RoundedCornerShape(22.dp))
                .padding(horizontal = 18.dp, vertical = 4.dp),  tint = Color.DarkGray,  painter = painterResource(id = icon )  , contentDescription ="Icons" )






        Text(text = "Home")


    }








}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LearnComposeTheme {

    }
}