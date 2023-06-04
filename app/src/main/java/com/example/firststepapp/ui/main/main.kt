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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.firststepapp.navigation.NavigationItem
import com.example.firststepapp.navigation.Screen
import com.example.firststepapp.preferences.UserPreferences
import com.example.firststepapp.preferences.dataStore
import com.example.firststepapp.ui.main.chat.Chat
import com.example.firststepapp.ui.main.home.Home
import com.example.firststepapp.ui.main.personality.Personality
import com.example.firststepapp.ui.main.test.Test
import com.example.firststepapp.viewmodel.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainApp(
    modifier: Modifier = Modifier,
    navControl: NavHostController,
    viewModel: AuthViewModel
) {
    val viewModelStoreOwner = LocalContext.current as ViewModelStoreOwner
    navControl.setViewModelStore(viewModelStoreOwner.viewModelStore)

    CompositionLocalProvider(LocalAuthViewModel provides viewModel) {
        Scaffold(
            bottomBar = {
                BottomBar(navControl)
            },
            modifier = modifier
        ) { innerPadding ->
            NavHost(
                navController = navControl,
                startDestination = Screen.Home.route,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(Screen.Home.route) {
                    Home(modifier, navControl)
                }
                composable(Screen.Chat.route) {
                    Chat(modifier, navControl)
                }
                composable(Screen.Test.route) {
                    Test(modifier, navControl)
                }
                composable(Screen.Personality.route) {
                    Personality(modifier, navControl)
                }
            }
        }
    }
}


private val LocalAuthViewModel = staticCompositionLocalOf<AuthViewModel> {
    error("No AuthViewModel provided")
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

//@Preview(showBackground = true)
//@Composable
//fun HomePreview() {
//    FirstStepAppTheme {
//        MainApp()
//    }
//}