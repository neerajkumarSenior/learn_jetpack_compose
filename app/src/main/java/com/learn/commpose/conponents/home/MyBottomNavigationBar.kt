package com.learn.commpose.conponents.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue


@Composable
fun MyBottomNavigationBar() {

    val navBarList = listOf(
        NavBar("Home", Icons.Filled.Home),
        NavBar("DUiP", Icons.Filled.Delete),
        NavBar("Back", Icons.Filled.ShoppingCart),
        NavBar("Account", Icons.Filled.AccountCircle)
    )

    var selectedIndex by remember {
        mutableIntStateOf(0)
    }

    NavigationBar( ) {

        navBarList.forEachIndexed { index, navBarItem ->

            NavigationBarItem(

                selected = selectedIndex == index,
                onClick = { selectedIndex = index},
                icon = {
                    Icon(navBarItem.icon, contentDescription = navBarItem.label)

                },

                label = {
                    Text(text = navBarItem.label)
                }


            )


        }


    }
}
