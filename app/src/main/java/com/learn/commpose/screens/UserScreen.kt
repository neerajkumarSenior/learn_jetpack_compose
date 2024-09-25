package com.learn.commpose.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.learn.commpose.conponents.UserInfoCard
import com.learn.commpose.network.ApiResult
import com.learn.commpose.viewModels.UserViewModel

@Composable
fun UserScreen(viewModel: UserViewModel = viewModel()) {
    val userResult = viewModel.userResult

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "User List", style = MaterialTheme.typography.titleSmall)
        Spacer(modifier = Modifier.height(16.dp))

        when (userResult) {
            is ApiResult.Loading -> {
                CircularProgressIndicator()
            }
            is ApiResult.Success -> {
                val users = userResult.data


                if (users.isNotEmpty()) {
                    users.forEach { user ->
//                        Text(text = "Name: ${user.name}")
//                        Text(text = "Email: ${user.phone}")
//                        Spacer(modifier = Modifier.height(8.dp))
                        UserInfoCard(user)
                    }
                } else {
                    Text(text = "No Users Available")
                }
            }
            is ApiResult.Error -> {
                Text(text = "Error: ${userResult.message}")
            }
        }
    }
}
