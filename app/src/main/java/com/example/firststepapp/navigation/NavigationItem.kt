package com.example.firststepapp.navigation

import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationItem (
    val icon: ImageVector,
    val title: String,
    val screen: Screen
)