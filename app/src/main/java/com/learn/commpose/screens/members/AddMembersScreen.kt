//package com.learn.commpose.screens.members
//
//import android.annotation.SuppressLint
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.rememberScrollState
//import androidx.compose.foundation.verticalScroll
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.input.PasswordVisualTransformation
//import androidx.compose.ui.text.input.TextFieldValue
//import androidx.compose.ui.unit.dp
//import androidx.navigation.NavController
//import com.learn.commpose.R
//
//@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun GymRegistrationScreen(navController: NavController, modifier: Modifier = Modifier) {
//    // State variables
//    var name by remember { mutableStateOf(TextFieldValue("")) }
//    var email by remember { mutableStateOf(TextFieldValue("")) }
//    var phoneNumber by remember { mutableStateOf(TextFieldValue("")) }
//    var age by remember { mutableStateOf(TextFieldValue("")) }
//    var gender by remember { mutableStateOf("Male") }
//    var password by remember { mutableStateOf(TextFieldValue("")) }
//    var confirmPassword by remember { mutableStateOf(TextFieldValue("")) }
//    var membershipType by remember { mutableStateOf("Regular") }
//    var isSubmitting by remember { mutableStateOf(false) }
//    var termsAccepted by remember { mutableStateOf(false) }
//
//    // Remember scroll state for the Column
//    val scrollState = rememberScrollState()
//
//
//    Scaffold(  topBar = {
//        TopAppBar(
//            title = { Text(text = "Gym Registration") },
//            navigationIcon = {
//                IconButton(onClick = { navController.popBackStack() }) {
//                    Icon(
//                        painter = painterResource(id = R.drawable.baseline_arrow_back_24), // replace with your back icon resource
//                        contentDescription = "Back"
//                    )
//                }
//            }
//        )
//    }) { paddingValues ->
//        Box(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(paddingValues) // Use padding from Scaffold
//        ) {
//        Column(
//            modifier = modifier
//                .fillMaxSize()
//                .padding(horizontal = 20.dp)
//                .verticalScroll(scrollState), // Make the column scrollable
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Top // Arrange items from the top
//        ) {
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            // Input fields
//            OutlinedTextField(
//                value = name,
//                onValueChange = { name = it },
//                label = { Text("Name") },
//                modifier = Modifier.fillMaxWidth(),
//                singleLine = true
//            )
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            OutlinedTextField(
//                value = email,
//                onValueChange = { email = it },
//                label = { Text("Email Address") },
//                modifier = Modifier.fillMaxWidth(),
//                singleLine = true
//            )
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            OutlinedTextField(
//                value = phoneNumber,
//                onValueChange = { phoneNumber = it },
//                label = { Text("Phone Number") },
//                modifier = Modifier.fillMaxWidth(),
//                singleLine = true
//            )
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            OutlinedTextField(
//                value = age,
//                onValueChange = { age = it },
//                label = { Text("Age") },
//                modifier = Modifier.fillMaxWidth(),
//                singleLine = true
//            )
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            // Gender Dropdown
//            val genders = listOf("Male", "Female", "Other")
//            var expandedGender by remember { mutableStateOf(false) }
//
//            ExposedDropdownMenuBox(
//                expanded = expandedGender,
//                onExpandedChange = { expandedGender = !expandedGender }
//            ) {
//                OutlinedTextField(
//                    readOnly = true,
//                    value = gender,
//                    onValueChange = { },
//                    label = { Text("Gender") },
//                    modifier = Modifier.fillMaxWidth(),
//                    trailingIcon = {
//                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedGender)
//                    }
//                )
//
//                ExposedDropdownMenu(
//                    expanded = expandedGender,
//                    onDismissRequest = { expandedGender = false }
//                ) {
//                    genders.forEach { item ->
//                        DropdownMenuItem(
//                            text = { Text(text = item) },
//                            onClick = {
//                                gender = item
//                                expandedGender = false
//                            }
//                        )
//                    }
//                }
//            }
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            // Password Input Field
//            OutlinedTextField(
//                value = password,
//                onValueChange = { password = it },
//                label = { Text("Password") },
//                modifier = Modifier.fillMaxWidth(),
//                visualTransformation = PasswordVisualTransformation(),
//                singleLine = true
//            )
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            // Confirm Password Input Field
//            OutlinedTextField(
//                value = confirmPassword,
//                onValueChange = { confirmPassword = it },
//                label = { Text("Confirm Password") },
//                modifier = Modifier.fillMaxWidth(),
//                visualTransformation = PasswordVisualTransformation(),
//                singleLine = true
//            )
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            // Membership Type Dropdown
//            val membershipTypes = listOf("Regular", "Premium", "VIP")
//            var expandedMembership by remember { mutableStateOf(false) }
//
//            ExposedDropdownMenuBox(
//                expanded = expandedMembership,
//                onExpandedChange = { expandedMembership = !expandedMembership }
//            ) {
//                OutlinedTextField(
//                    readOnly = true,
//                    value = membershipType,
//                    onValueChange = { },
//                    label = { Text("Membership Type") },
//                    modifier = Modifier.fillMaxWidth(),
//                    trailingIcon = {
//                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedMembership)
//                    }
//                )
//
//                ExposedDropdownMenu(
//                    expanded = expandedMembership,
//                    onDismissRequest = { expandedMembership = false }
//                ) {
//                    membershipTypes.forEach { type ->
//                        DropdownMenuItem(
//                            text = { Text(text = type) },
//                            onClick = {
//                                membershipType = type
//                                expandedMembership = false
//                            }
//                        )
//                    }
//                }
//            }
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            // Terms and Conditions Checkbox
//            Row(verticalAlignment = Alignment.CenterVertically) {
//                Checkbox(
//                    checked = termsAccepted,
//                    onCheckedChange = { termsAccepted = it }
//                )
//                Text("I accept the Terms and Conditions")
//            }
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            // Submit Button
//            Button(
//                onClick = {
//                    isSubmitting = true
//                    // Handle submission logic here
//                    navController.popBackStack() // Navigate back or to another screen after submission
//                },
//                modifier = Modifier.fillMaxWidth(),
//                enabled = !isSubmitting && termsAccepted
//            ) {
//                if (isSubmitting) {
//                    CircularProgressIndicator(
//                        color = MaterialTheme.colorScheme.primary,
//                        modifier = Modifier.size(24.dp)
//                    )
//                } else {
//                    Text("Submit")
//                }
//            }
//        }
//    }
//    }
//
//
//}



package com.learn.commpose.screens.members

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.learn.commpose.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GymRegistrationScreen(navController: NavController, modifier: Modifier = Modifier) {
    // State variables
    var name by remember { mutableStateOf(TextFieldValue("")) }
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var phoneNumber by remember { mutableStateOf(TextFieldValue("")) }
    var age by remember { mutableStateOf(TextFieldValue("")) }
    var gender by remember { mutableStateOf("Male") }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    var confirmPassword by remember { mutableStateOf(TextFieldValue("")) }
    var membershipType by remember { mutableStateOf("Regular") }
    var isSubmitting by remember { mutableStateOf(false) }
    var termsAccepted by remember { mutableStateOf(false) }

    // Remember scroll state for the Column
    val scrollState = rememberScrollState()

    val scope = rememberCoroutineScope() // Create a CoroutineScope for managing the delay
    // State to control if back navigation can happen
    var canNavigateBack by remember { mutableStateOf(true) }



    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Add Member", style = TextStyle(color = Color.White, fontSize = 24.sp)) },
                navigationIcon = {
                    IconButton(onClick = {


                      //  navController.popBackStack()
                        if (canNavigateBack) {
                            canNavigateBack = false // Disable back button
                            navController.popBackStack() // Instantly navigate back

                            // Launch a coroutine for debouncing (delay inside onClick)
                            scope.launch {
                                delay(50000) // Prevent multiple clicks for 500ms
                                canNavigateBack = true // Re-enable the back navigation after delay
                            }
                        }

                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary)
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(MaterialTheme.colorScheme.background)
        ) {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp)
                    .verticalScroll(scrollState),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                // Title
                Text(
                    text = "Register Now",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(vertical = 8.dp)
                )

                // Input fields
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Name") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email Address") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = phoneNumber,
                    onValueChange = { phoneNumber = it },
                    label = { Text("Phone Number") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = age,
                    onValueChange = { age = it },
                    label = { Text("Age") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Gender Dropdown
                val genders = listOf("Male", "Female", "Other")
                var expandedGender by remember { mutableStateOf(false) }

                ExposedDropdownMenuBox(
                    expanded = expandedGender,
                    onExpandedChange = { expandedGender = !expandedGender }
                ) {
                    OutlinedTextField(
                        readOnly = true,
                        value = gender,
                        onValueChange = { },
                        label = { Text("Gender") },
                        modifier = Modifier.fillMaxWidth(),
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedGender)
                        }
                    )

                    ExposedDropdownMenu(
                        expanded = expandedGender,
                        onDismissRequest = { expandedGender = false }
                    ) {
                        genders.forEach { item ->
                            DropdownMenuItem(
                                text = { Text(text = item) },
                                onClick = {
                                    gender = item
                                    expandedGender = false
                                }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Password Input Field
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") },
                    modifier = Modifier.fillMaxWidth(),
                    visualTransformation = PasswordVisualTransformation(),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Confirm Password Input Field
                OutlinedTextField(
                    value = confirmPassword,
                    onValueChange = { confirmPassword = it },
                    label = { Text("Confirm Password") },
                    modifier = Modifier.fillMaxWidth(),
                    visualTransformation = PasswordVisualTransformation(),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Membership Type Dropdown
                val membershipTypes = listOf("Regular", "Premium", "VIP")
                var expandedMembership by remember { mutableStateOf(false) }

                ExposedDropdownMenuBox(
                    expanded = expandedMembership,
                    onExpandedChange = { expandedMembership = !expandedMembership }
                ) {
                    OutlinedTextField(
                        readOnly = true,
                        value = membershipType,
                        onValueChange = { },
                        label = { Text("Membership Type") },
                        modifier = Modifier.fillMaxWidth(),
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedMembership)
                        }
                    )

                    ExposedDropdownMenu(
                        expanded = expandedMembership,
                        onDismissRequest = { expandedMembership = false }
                    ) {
                        membershipTypes.forEach { type ->
                            DropdownMenuItem(
                                text = { Text(text = type) },
                                onClick = {
                                    membershipType = type
                                    expandedMembership = false
                                }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Terms and Conditions Checkbox
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = termsAccepted,
                        onCheckedChange = { termsAccepted = it }
                    )
                    Text("I accept the Terms and Conditions")
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Submit Button
                Button(
                    onClick = {
                        isSubmitting = true
                        // Handle submission logic here
                        navController.popBackStack() // Navigate back or to another screen after submission
                    },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = !isSubmitting && termsAccepted,
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    if (isSubmitting) {
                        CircularProgressIndicator(
                            color = Color.White,
                            modifier = Modifier.size(24.dp)
                        )
                    } else {
                        Text("Submit", color = Color.White)
                    }
                }
            }
        }
    }
}
