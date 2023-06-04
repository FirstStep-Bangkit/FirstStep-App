package com.example.firststepapp.ui.main.personality

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.firststepapp.viewmodel.AuthViewModel

@Composable
fun Personality (
    modifier: Modifier,
    navControl: NavHostController
    //authViewModel: AuthViewModel
){
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = "Personality"
        )
    }
}