package com.example.firststepapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.firststepapp.navigation.Screen
import com.example.firststepapp.preferences.UserPreferences
import com.example.firststepapp.preferences.dataStore
import com.example.firststepapp.ui.login.Login
import com.example.firststepapp.ui.main.chat.Chat
import com.example.firststepapp.ui.main.home.Home
import com.example.firststepapp.ui.register.Register
import com.example.firststepapp.ui.splashscreen.SplashScreen
import com.example.firststepapp.ui.theme.FirstStepAppTheme
import com.example.firststepapp.viewmodel.AuthViewModel
import com.example.firststepapp.viewmodel.ViewModelFactory
import kotlin.system.exitProcess

@Composable
fun FirstStepApp() {
    val navController = rememberNavController()
    val userPreferences = UserPreferences.getInstance(LocalContext.current.dataStore)
    val authViewModel = remember { AuthViewModel(userPreferences) }
    val viewModelStoreOwner = LocalContext.current as ViewModelStoreOwner
    navController.setViewModelStore(viewModelStoreOwner.viewModelStore)
    val LocalAuthViewModel = staticCompositionLocalOf<AuthViewModel?> { null }

    CompositionLocalProvider(LocalAuthViewModel provides authViewModel) {
        NavHost(navController, startDestination = Screen.SplashScreen.route) {
            composable(Screen.SplashScreen.route) {
                SplashScreen(
                    navController = navController,
                    viewModel = authViewModel
                )
            }
            composable(Screen.Login.route) {
                Login(
                    navController = navController,
                    onBackPressed = { exitProcess(0) },
                    viewModel = authViewModel
                )
            }
            composable(Screen.Register.route) {
                Register(
                    navController = navController,
                    viewModel = authViewModel
                )
            }
            composable(Screen.Home.route) {
                Home(
                    navControl = navController,
                    viewModel = authViewModel
                )
            }
            composable(Screen.Chat.route) {
                Chat(
                    navControl = navController,
                    viewModel = authViewModel
                )
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