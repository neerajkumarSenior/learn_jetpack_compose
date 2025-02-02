package com.learn.commpose.screens.home

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.learn.commpose.conponents.home.NavBar
import com.learn.commpose.navigation.Dest
import com.learn.commpose.screens.AccountScreen
import com.learn.commpose.screens.UserScreen
import com.learn.commpose.screens.members.GymRegistrationScreen
import com.learn.commpose.screens.pending.FeaturesScreen
import com.learn.commpose.screens.pending.PendingMembersListScreen
import com.learn.commpose.screens.request.AdmissionRequestsScreen
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class) // Needed for using Material3 components
@Composable
fun HomeScreen(navController: NavController) {



    // State for the drawer
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()


    val navBarList = listOf(
        NavBar("Home", Icons.Filled.Home),
        NavBar("DUiP", Icons.Filled.Delete),
        NavBar("Back", Icons.Filled.ShoppingCart),
        NavBar("Settings", Icons.Filled.Settings),
        NavBar("Account", Icons.Filled.AccountCircle)
    )

    var selectedIndex by remember {
        mutableIntStateOf(0)
    }

    var expanded by remember { mutableStateOf(false) } // State for DropdownMenu


    // Modal Navigation Drawer
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                DrawerContent(navController) // Pass the navController to handle navigation
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = "GymClient")
                    },
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(Icons.Filled.Menu, contentDescription = "Menu")
                        }
                    },
                    actions = {

                        // MoreVert IconButton and DropdownMenu
                        Box {
                            IconButton(onClick = { expanded = true }) {
                                Icon(Icons.Filled.MoreVert, contentDescription = "More Options")
                            }

                            DropdownMenu(
                                expanded = expanded,
                                onDismissRequest = { expanded = false }
                            ) {
                                DropdownMenuItem(
                                    text = { Text("Profile") },
                                    onClick = {
                                        expanded = false  // Close menu after selection
                                        // Handle Profile action here
                                    },
                                    leadingIcon = {
                                        Icon(Icons.Filled.Person, contentDescription = "Profile Icon")  // Add Profile icon
                                    }
                                )
                                DropdownMenuItem(
                                    text = { Text("Settings") },
                                    onClick = {
                                        expanded = false  // Close menu after selection
                                        // Handle Settings action here
                                    },
                                    leadingIcon = {
                                        Icon(Icons.Filled.Settings, contentDescription = "Settings Icon")  // Add Settings icon
                                    }
                                )
                                DropdownMenuItem(
                                    text = { Text("Logout") },
                                    onClick = {
                                        expanded = false  // Close menu after selection
                                        // Handle Logout action here
                                    },
                                    leadingIcon = {
                                        Icon(Icons.Filled.ExitToApp, contentDescription = "Logout Icon")  // Add Logout icon
                                    }
                                )
                            }
                        }

                    },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary)
                )


//                TopAppBar(
//                    title = {
//                        Text(text = "GymClient")
//                    },
//                    navigationIcon = {
//                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
//                            Icon(Icons.Filled.Menu, contentDescription = "Menu")
//                        }
//                    },
//                    actions = {
//                        IconButton(onClick = { expanded =true}) {
//                            Icon(Icons.Filled.MoreVert, contentDescription = "More Options")
//                        }
//                    },
//                    colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary)
//
//
//                )


//                DropdownMenu(
//                    expanded = expanded,
//                    onDismissRequest = { expanded = false }
//                ) {
//                    // Use the text parameter directly in DropdownMenuItem
//                    DropdownMenuItem(
//                        text = { Text("Profile") },
//                        onClick = {
//                            expanded = false  // Close menu after selection
//                            // Handle Profile action here
//                        }
//                    )
//                    DropdownMenuItem(
//                        text = { Text("Settings") },
//                        onClick = {
//                            expanded = false  // Close menu after selection
//                            // Handle Settings action here
//                        }
//                    )
//                    DropdownMenuItem(
//                        text = { Text("Logout") },
//                        onClick = {
//                            expanded = false  // Close menu after selection
//                            // Handle Logout action here
//                        }
//                    )
//                }

            },

            bottomBar = {
                NavigationBar( ) {

                    navBarList.forEachIndexed { index, navBarItem ->

                        NavigationBarItem(

                            selected = selectedIndex == index,
                            onClick = { selectedIndex = index},
                            icon = {

                                BadgedBox(badge = {

                                    Badge(){
                                        Text(text = "0")
                                    }
                                }) {
//                                    Icon(navBarItem.icon, contentDescription = navBarItem.label)

                                }

                                Icon(navBarItem.icon, contentDescription = navBarItem.label)


                            },

                            label = {
                                Text(text = navBarItem.label)
                            }


                        )


                    }


                }

//                    MyBottomNavigationBar()



            },
            floatingActionButton = {
                ExtendedFloatingActionButton(
                    text = { Text("Add Member") },
                    icon = { Icon(Icons.Filled.Add, contentDescription = "add member") },
                    onClick = {

                        navController.navigate(Dest.GymRegistrationScreen)

                    }
                    , containerColor =  MaterialTheme.colorScheme.primary,
                    contentColor = Color.White
                )
            }
        ) { contentPadding ->
            ContentScreen(modifier=Modifier.padding(contentPadding),selectedIndex,navController)

        }
    }
}

//@Composable
//fun ContentScreen(
//    modifier: Modifier = Modifier,
//    selectedIndex: Int,
//    navController: NavController
//) {
//    // AnimatedContent to switch between different screens with slide animation
//    AnimatedContent(
//        targetState = selectedIndex,
//        transitionSpec = {
//            // Combine enter and exit transitions using 'togetherWith' to create ContentTransform
//            slideInHorizontally(
//                initialOffsetX = { fullWidth -> fullWidth },
//                animationSpec = tween(300)
//            ) togetherWith slideOutHorizontally(
//                targetOffsetX = { fullWidth -> -fullWidth },
//                animationSpec = tween(300)
//            )
//        }
//    ) { targetIndex ->
//        when (targetIndex) {
//            0 -> FeaturesScreen(navController)
//            1 -> AdmissionRequestsScreen(navController)
//            2 -> PendingMembersListScreen(navController)
//            3 -> AccountScreen(navController)
//        }
//    }
//}

@Composable
fun ContentScreen(modifier: Modifier = Modifier, selectedIndex: Int, navController: NavController,) {

    when(selectedIndex){

        0-> FeaturesScreen(navController)
        1-> AdmissionRequestsScreen(navController)
        2-> PendingMembersListScreen(navController)
        3-> AccountScreen(navController)



    }

}











@Composable
fun DrawerContent(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Drawer Items
        Text(
            text = "Drawer Item 1",
            modifier = Modifier.padding(16.dp),
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            text = "Drawer Item 2",
            modifier = Modifier.padding(16.dp),
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            text = "Drawer Item 3",
            modifier = Modifier.padding(16.dp),
            color = MaterialTheme.colorScheme.onBackground
        )
        // Add navigation logic to handle clicks if needed
    }
}
