package com.example.firststepapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.firststepapp.navigation.Screen
import com.example.firststepapp.ui.login.Login
import com.example.firststepapp.ui.register.Register
import com.example.firststepapp.ui.splashscreen.SplashScreen
import com.example.firststepapp.ui.theme.FirstStepAppTheme
import kotlin.system.exitProcess

@Composable
fun FirstStepApp () {
    val navController = rememberNavController()

    NavHost(navController, startDestination = Screen.SplashScreen.route) {
        composable(Screen.SplashScreen.route) {
            SplashScreen(navController)
        }
        composable(Screen.Login.route) {
            Login(navController) {
                exitProcess(0)
            }
        }
        composable(Screen.Register.route) {
            Register(navController) {
                exitProcess(0)
            }
        }
    }
}

//preview
@Preview(showBackground = true)
@Composable
fun FirstStepAppPreview(){
    FirstStepAppTheme {
        FirstStepApp()
    }
}