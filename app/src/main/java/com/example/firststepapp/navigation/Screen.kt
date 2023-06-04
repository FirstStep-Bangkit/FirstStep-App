package com.example.firststepapp.navigation

sealed class Screen (val route: String) {
    object Login : Screen("login")
    object SplashScreen : Screen("splash")
    object Register : Screen("register")
    object Main : Screen("main")
    object Home : Screen("home")
    object Chat : Screen("chat")
    object Test : Screen("test")
    object Personality : Screen("personality")
    object Profile : Screen("home/{profile}"){
        fun createRoute(profile: Long) = "home/$profile"
    }
}