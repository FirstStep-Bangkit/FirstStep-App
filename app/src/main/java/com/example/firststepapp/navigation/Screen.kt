package com.example.firststepapp.navigation

sealed class Screen (val route: String) {
    object Login : Screen("login")
    object SplashScreen : Screen("splash")
    object Register : Screen("register")
}