package com.example.firststepapp.ui.main.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.example.firststepapp.R
import com.example.firststepapp.viewmodel.AuthViewModel

@Composable
fun Profile (
    navControl: NavHostController,
    authViewModel: AuthViewModel
){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = "Profile"
        )
    }
}