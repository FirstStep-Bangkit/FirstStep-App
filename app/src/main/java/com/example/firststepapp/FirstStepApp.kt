package com.example.firststepapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.firststepapp.navigation.Screen
import com.example.firststepapp.preferences.UserPreferences
import com.example.firststepapp.preferences.dataStore
import com.example.firststepapp.ui.login.Login
import com.example.firststepapp.ui.main.chat.Chat
import com.example.firststepapp.ui.main.home.Home
import com.example.firststepapp.ui.main.personality.Personality
import com.example.firststepapp.ui.main.profile.Profile
import com.example.firststepapp.ui.main.test.Test
import com.example.firststepapp.ui.register.Register
import com.example.firststepapp.ui.splashscreen.SplashScreen
import com.example.firststepapp.ui.theme.FirstStepAppTheme
import com.example.firststepapp.viewmodel.AuthViewModel
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
            composable(Screen.Test.route){
                Test(
                    navControl = navController,
                    viewModel = authViewModel
                )
            }
            composable(Screen.Personality.route){
                Personality(
                    navControl = navController,
                    authViewModel = authViewModel
                )
            }
            composable(Screen.Profile.route){
                Profile(
                    navControl = navController,
                    authViewModel = authViewModel
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