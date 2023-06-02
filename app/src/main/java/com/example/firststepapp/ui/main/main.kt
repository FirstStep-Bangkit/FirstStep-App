package com.example.firststepapp.ui.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FactCheck
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.QuestionAnswer
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.firststepapp.navigation.NavigationItem
import com.example.firststepapp.navigation.Screen
import com.example.firststepapp.ui.main.chat.Chat
import com.example.firststepapp.ui.main.home.Home
import com.example.firststepapp.ui.main.personality.Personality
import com.example.firststepapp.ui.main.test.Test
import com.example.firststepapp.ui.theme.FirstStepAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainApp(
    modifier: Modifier = Modifier,
    navControl: NavHostController = rememberNavController()
){
    Scaffold(
        bottomBar = {
            BottomBar(navControl)
        },
        modifier = Modifier
    ){ innerPadding ->
        NavHost(
            navController = navControl,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ){
            composable(Screen.Home.route) {
                Home()
            }
            composable(Screen.Chat.route) {
                Chat()
            }
            composable(Screen.Test.route) {
                Test()
            }
            composable(Screen.Personality.route){
                Personality()
            }
        }
    }
}

@Composable
fun BottomBar(
    navControl: NavHostController,
    modifier: Modifier = Modifier
){
    NavigationBar(
        modifier = modifier
    ) {
        val navListEntry by navControl.currentBackStackEntryAsState()
        val navRoute = navListEntry?.destination?.route

        val navItem = listOf(
            NavigationItem(
                icon = Icons.Default.Home,
                screen = Screen.Home
            ),
            NavigationItem(
                icon = Icons.Default.QuestionAnswer,
                screen = Screen.Chat
            ),
            NavigationItem(
                icon = Icons.Default.FactCheck,
                screen = Screen.Test
            ),
            NavigationItem(
                icon = Icons.Default.Lightbulb,
                screen = Screen.Personality
            )
        )
        NavigationBar {
            navItem.map { item ->
                NavigationBarItem(
                    icon = {
                        val iconTint = if (navRoute == item.screen.route) Color(0xFF8B4513) else LocalContentColor.current
                        Icon(
                            imageVector = item.icon,
                            contentDescription = null,
                            tint = iconTint
                        )
                    },
                    selected = false,
                    onClick = {
                        navControl.navigate(item.screen.route) {
                            popUpTo(navControl.graph.findStartDestination().id) {
                                saveState = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    FirstStepAppTheme {
        MainApp()
    }
}