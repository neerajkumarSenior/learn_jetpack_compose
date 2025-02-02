package com.learn.commpose.screens.request

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.learn.commpose.R

data class AdmissionRequest(val name: String, val email: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdmissionRequestsScreen(navController: NavController) {
    // Sample data for admission requests
    val admissionRequests = listOf(
        AdmissionRequest("Alice Johnson", "alice@example.com"),
        AdmissionRequest("Bob Smith", "bob@example.com"),
        AdmissionRequest("Charlie Brown", "charlie@example.com"),
        // Add more requests as needed
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Admission Requests") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                            contentDescription = "Back"
                        )
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
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(admissionRequests) { request ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(text = "Name: ${request.name}", style = MaterialTheme.typography.titleMedium)
                            Text(text = "Email: ${request.email}", style = MaterialTheme.typography.bodyMedium)

                            // Row to hold Accept and Reject buttons
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.End
                            ) {
                                Button(
                                    onClick = {
                                        // Handle Accept logic
                                    },
                                    modifier = Modifier.padding(end = 8.dp)
                                ) {
                                    Text("Accept")
                                }

                                Button(
                                    onClick = {
                                        // Handle Reject logic
                                    }
                                ) {
                                    Text("Reject")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
