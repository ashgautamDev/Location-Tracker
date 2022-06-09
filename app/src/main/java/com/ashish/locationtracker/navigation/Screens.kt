package com.ashish.locationtracker.navigation

sealed class Screens(val route : String) {

    object InputScreen: Screens("input")
    object HomeScreen : Screens("home")


}
