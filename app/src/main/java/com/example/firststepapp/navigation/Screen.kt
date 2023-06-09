package com.example.firststepapp.navigation

sealed class Screen (val route: String) {
    object Login : Screen("login")
    object SplashScreen : Screen("splash")
    object Register : Screen("register")
    object Home : Screen("home")
    object Chat : Screen("chat")
    object Personality : Screen("personality")
    object Test : Screen("test")
    object Profile : Screen("profile")
    object ChangePassword : Screen("change_password")
}