package com.example.firststepapp.ui.splashscreen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.firststepapp.R
import com.example.firststepapp.navigation.Screen
import com.example.firststepapp.preferences.UserPreferences
import com.example.firststepapp.ui.theme.FirstStepAppTheme
import com.example.firststepapp.viewmodel.AuthViewModel
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController, viewModel: AuthViewModel) {
    val tokenLiveData = viewModel.getUserPreferences("token")

    val context = LocalContext.current

    LaunchedEffect(Unit) {
        delay(2000)

        tokenLiveData.observe(context as LifecycleOwner) { token ->
            Log.e("AutentikasiActivity", "Token changed to $token")

            if (token == UserPreferences.preferenceDefaultValue) {
                navController.navigate(Screen.Login.route) {
                    popUpTo(Screen.SplashScreen.route) { inclusive = true }
                }
            } else {
                navController.navigate(Screen.Home.route) {
                    popUpTo(Screen.SplashScreen.route) { inclusive = true }
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.onboarding_one),
            contentDescription = "SplashScreen",
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}

//@Preview(showBackground = true)
//@Composable
//fun SplashScreenPreview(){
//    val navController = rememberNavController()
//    FirstStepAppTheme {
//        SplashScreen(navController)
//    }
//}