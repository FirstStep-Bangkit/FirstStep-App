package com.example.firststepapp.ui.main.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.firststepapp.R
import com.example.firststepapp.ui.theme.FirstStepAppTheme
import com.example.firststepapp.viewmodel.AuthViewModel

@Composable
fun Home (
    navControl: NavHostController
){

}

@Composable
fun Headline(
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier
            .padding(10.dp)
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier
        ){
            Text(
                text = "Halo, Aris",
                style = MaterialTheme.typography.headlineLarge
            )
            Image(
                modifier = Modifier
                    .size(50.dp),
                painter = painterResource(R.drawable.onboarding_one),
                contentDescription = "Profile"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview(){
    FirstStepAppTheme() {
        val navController = rememberNavController()
        Home(navController)
    }
}