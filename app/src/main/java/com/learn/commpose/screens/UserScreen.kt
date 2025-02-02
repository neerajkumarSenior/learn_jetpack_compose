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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.learn.commpose.conponents.UserInfoCard
import com.learn.commpose.model.Post
import com.learn.commpose.network.ApiResult
import com.learn.commpose.viewModels.UserViewModel

@Composable
fun UserScreen(navController: NavController, modifier: Modifier=Modifier) {

    val viewModel: UserViewModel = hiltViewModel()
    val userResult by viewModel.userResult.collectAsState()


    Column(
        modifier = modifier.padding(horizontal = 13.dp
        )
            .fillMaxSize()

    ) {
        Text(text = "User List", style = MaterialTheme.typography.titleSmall)
        Spacer(modifier = Modifier.height(16.dp))

       // LoginScreen()

        when (userResult) {
            is ApiResult.Loading -> {
                CircularProgressIndicator()
            }
            is ApiResult.Success -> {
                val users = (userResult as ApiResult.Success<List<Post>>).data


                if (users.isNotEmpty()) {
                    users.forEach { user ->

                        UserInfoCard(user)

                    }
                } else {
                    Text(text = "No Users Available")
                }
            }
            is ApiResult.Error -> {
                Text(text = "Error: ${(userResult as ApiResult.Error).message}")
            }
        }
    }
}
