package com.example.firststepapp.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FactCheck
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.PersonOutline
import androidx.compose.material.icons.filled.QuestionAnswer
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.firststepapp.navigation.NavigationItem
import com.example.firststepapp.navigation.Screen

@Composable
fun ClickableIconWithText(
    icon: ImageVector,
    text: String,
    destination: Screen,
    navController: NavHostController
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(8.dp)
    ) {
        IconButton(
            onClick = {
                navController.navigate(destination.route)
            }
        ) {
            Icon(
                imageVector = icon,
                contentDescription = "icon",
                tint = Color.Black
            )
        }
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

fun IconHome(): List<NavigationItem>{
    return listOf(
        NavigationItem(
            icon = Icons.Default.QuestionAnswer,
            title = "Chat",
            screen = Screen.Chat
        ),
        NavigationItem(
            icon = Icons.Default.FactCheck,
            title = "Test",
            screen = Screen.Test
        ),
        NavigationItem(
            icon = Icons.Default.Lightbulb,
            title = "Personality",
            screen = Screen.Personality
        ),
        NavigationItem(
            icon = Icons.Default.PersonOutline,
            title = "Profile",
            screen = Screen.Profile
        )
    )
}
