package com.example.firststepapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.firststepapp.navigation.Screen
import com.example.firststepapp.preferences.UserPreferences
import com.example.firststepapp.preferences.dataStore
import com.example.firststepapp.ui.login.Login
import com.example.firststepapp.ui.register.Register
import com.example.firststepapp.ui.splashscreen.SplashScreen
import com.example.firststepapp.ui.theme.FirstStepAppTheme
import com.example.firststepapp.viewmodel.AuthViewModel
import com.example.firststepapp.viewmodel.ViewModelFactory
import kotlin.system.exitProcess

@Composable
fun FirstStepApp () {
    val navController = rememberNavController()

    NavHost(navController, startDestination = Screen.SplashScreen.route) {
        composable(Screen.SplashScreen.route) {
            SplashScreen(navController)
        }
        composable(Screen.Login.route) {
            val viewModel: AuthViewModel = viewModel(
                factory = ViewModelFactory(UserPreferences.getInstance(LocalContext.current.dataStore))
            )
            Login(navController, onBackPressed =  {
                exitProcess(0)
            },viewModel = viewModel)
        }
        composable(Screen.Register.route) {
            val viewModel: AuthViewModel = viewModel(
                factory = ViewModelFactory(UserPreferences.getInstance(LocalContext.current.dataStore))
            )
            Register(navController, onBackPressed = {
                exitProcess(0)
            }, viewModel = viewModel)
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