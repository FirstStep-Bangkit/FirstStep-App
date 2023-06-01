package com.example.firststepapp.ui.splashscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.firststepapp.R
import com.example.firststepapp.navigation.Screen
import com.example.firststepapp.ui.theme.FirstStepAppTheme
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController){
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
    LaunchedEffect(Unit) {
        delay(2000)
        navController.navigate(Screen.Login.route)
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview(){
    val navController = rememberNavController()
    FirstStepAppTheme {
        SplashScreen(navController)
    }
}