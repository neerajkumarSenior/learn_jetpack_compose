//package com.learn.commpose.screens.pending
//
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.material3.*
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.unit.dp
//import androidx.navigation.NavController
//import com.learn.commpose.R
//
//data class Feature(val title: String, val icon: Int)
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun FeaturesScreen(navController: NavController) {
//    val features = listOf(
//        Feature("Members", R.drawable.ic_members),  // replace with your icons
//        Feature("Classes", R.drawable.ic_classes),
//        Feature("Trainers", R.drawable.ic_trainers),
//        Feature("Schedule", R.drawable.ic_schedule),
//        Feature("Admission Requests", R.drawable.ic_admission_requests)
//    )
//
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = { Text(text = "Gym Management") },
//                actions = {
//                    IconButton(onClick = { /* Handle profile click */ }) {
//                        Icon(painter = painterResource(id = R.drawable.ic_profile), contentDescription = "Profile")
//                    }
//                }
//            )
//        }
//    ) { paddingValues ->
//        Box(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(paddingValues)
//        ) {
//            LazyColumn(
//                modifier = Modifier.fillMaxSize(),
//                verticalArrangement = Arrangement.spacedBy(16.dp),
//                contentPadding = PaddingValues(16.dp)
//            ) {
//                item {
//                    Text(
//                        text = "Welcome to the Gym Management App!",
//                        style = MaterialTheme.typography.headlineMedium,
//                        textAlign = TextAlign.Center,
//                        modifier = Modifier.padding(vertical = 16.dp)
//                    )
//                }
//
//                items(features) { feature ->
//                    Card(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .height(100.dp),
//                        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp),
//                        onClick = {
//                            when (feature.title) {
//                                "Members" -> navController.navigate("members")
//                                "Classes" -> navController.navigate("classes")
//                                "Trainers" -> navController.navigate("trainers")
//                                "Schedule" -> navController.navigate("schedule")
//                                "Admission Requests" -> navController.navigate("admission_requests")
//                            }
//                        }
//                    ) {
//                        Row(
//                            modifier = Modifier
//                                .fillMaxSize()
//                                .padding(16.dp),
//                            verticalAlignment = Alignment.CenterVertically
//                        ) {
//                            Icon(
//                                painter = painterResource(id = feature.icon),
//                                contentDescription = feature.title,
//                                modifier = Modifier.size(48.dp),
//                                tint = MaterialTheme.colorScheme.primary
//                            )
//                            Spacer(modifier = Modifier.width(16.dp))
//                            Text(text = feature.title, style = MaterialTheme.typography.titleMedium)
//                        }
//                    }
//                }
//            }
//        }
//    }
//}




package com.learn.commpose.screens.pending

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.learn.commpose.R

data class Feature(val title: String, val icon: Int)

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun FeaturesScreen(navController: NavController) {
    val features = listOf(
        Feature("Members", R.drawable.ic_members),  // replace with your icons
        Feature("Classes", R.drawable.ic_classes),
        Feature("Trainers", R.drawable.ic_trainers),
        Feature("Schedule", R.drawable.ic_schedule),
        Feature("Admission Requests", R.drawable.ic_admission_requests)
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Gym Management") },
                actions = {
                    IconButton(onClick = { /* Handle profile click */ }) {
                        Icon(painter = painterResource(id = R.drawable.ic_profile), contentDescription = "Profile")
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 128.dp),
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(16.dp)
            ) {
                item(span = { GridItemSpan(2) }) { // Header item spans across two columns
                    Text(
                        text = "Welcome to the Gym Management App!",
                        style = MaterialTheme.typography.headlineMedium,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(vertical = 16.dp)
                    )
                }

                items(features) { feature ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth(),
                        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp),
                        onClick = {
                            when (feature.title) {
                                "Members" -> navController.navigate("members")
                                "Classes" -> navController.navigate("classes")
                                "Trainers" -> navController.navigate("trainers")
                                "Schedule" -> navController.navigate("schedule")
                                "Admission Requests" -> navController.navigate("admission_requests")
                            }
                        }
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(id = feature.icon),
                                contentDescription = feature.title,
                                modifier = Modifier.size(48.dp),
                                tint = MaterialTheme.colorScheme.primary
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            Text(text = feature.title, style = MaterialTheme.typography.titleMedium)
                        }
                    }
                }
            }
        }
    }
}
