package com.learn.commpose.screens.pending

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

data class Member(val name: String, val email: String, val status: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PendingMembersListScreen(navController: NavController) {
    val pendingMembers = listOf(
        Member("John Doe", "john@example.com", "Pending"),
        Member("Jane Smith", "jane@example.com", "Pending"),
        Member("Emily Johnson", "emily@example.com", "Pending"),
        // Add more members as needed
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Pending Members") },
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
                items(pendingMembers) { member ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Text(text = "Name: ${member.name}", style = MaterialTheme.typography.titleMedium)
                            Text(text = "Email: ${member.email}", style = MaterialTheme.typography.bodyMedium)
                            Text(text = "Status: ${member.status}", style = MaterialTheme.typography.bodyMedium)

                            // Button to approve member or take action
                            Button(
                                onClick = { 
                                    // Handle member approval logic
                                },
                                modifier = Modifier.align(Alignment.End)
                            ) {
                                Text("Approve")
                            }
                        }
                    }
                }
            }
        }
    }
}
