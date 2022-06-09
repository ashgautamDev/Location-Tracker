package com.ashish.locationtracker.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ashish.locationtracker.data.manager.UserManager
import com.ashish.locationtracker.data.manager.UserManager.Companion.USER_NAME_KEY
import com.ashish.locationtracker.ui.screen.HomeScreen
import com.ashish.locationtracker.ui.screen.UserInputScreen
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@OptIn(DelicateCoroutinesApi::class)
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun NavGraph(onLocateMe: () -> Unit) {
    val navController = rememberNavController()
    val coroutineScope = rememberCoroutineScope()

    var hasUser by remember {
        mutableStateOf(false)
    }
    val context = LocalContext.current
    val userManager = UserManager(context)


    coroutineScope.launch {
        userManager.isKeyStored(USER_NAME_KEY).collect{
        hasUser = it

    }
    }
    NavHost(
        navController,
        startDestination = if (hasUser) Screens.HomeScreen.route else Screens.InputScreen.route
    ) {

        composable(Screens.HomeScreen.route) {
            HomeScreen(onLocateMe = onLocateMe)
        }
        composable(Screens.InputScreen.route) {
            UserInputScreen(navController = navController)
        }

    }
}