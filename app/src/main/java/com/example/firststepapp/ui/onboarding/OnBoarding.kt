package com.example.firststepapp.ui.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

data class OnboardingPage(val title: String, val description: String, val color: Color)

val onboardingPages = listOf(
    OnboardingPage(
        title = "Welcome",
        description = "Welcome to our app!",
        color = Color.Blue
    ),
    OnboardingPage(
        title = "Explore",
        description = "Discover amazing features!",
        color = Color.Green
    ),
    OnboardingPage(
        title = "Connect",
        description = "Connect with friends and family!",
        color = Color.Yellow
    )
)

@Composable
fun App() {
    SwipeOnboardingScreen(pages = onboardingPages)
}

@Composable
fun SwipeOnboardingScreen(pages: List<OnboardingPage>) {
    val currentPage = remember { mutableStateOf(0) }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyRow(
            modifier = Modifier.fillMaxSize()
        ) {
            itemsIndexed(pages) { index, page ->
                SwipeOnboardingPage(page = page)
            }
        }

        // Page Indicator
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.Center
            ) {
                pages.forEachIndexed { index, _ ->
                    PageIndicator(selected = index == currentPage.value)
                }
            }
        }

        // Skip Button
        if (currentPage.value != pages.size - 1) {
            Button(
                onClick = {
                    //currentPage.value = pages.size - 1
                },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp)
            ) {
                Text(text = "Skip")
            }
        }
    }
}

@Composable
fun SwipeOnboardingPage(page: OnboardingPage) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = page.title,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = page.description,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray,
                modifier = Modifier.padding(horizontal = 32.dp)
            )
        }
    }
}

@Composable
fun PageIndicator(selected: Boolean) {
    Box(
        modifier = Modifier
            .size(10.dp)
            .padding(4.dp)
            .clip(CircleShape)
            .background(if (selected) Color.White else Color.Gray)
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewApp() {
    App()
}

//@Preview(showBackground = true)
//@Composable
//fun OnBoardingPreview(){
//    FirstStepTheme {
//        OnBoarding(R.drawable.onboarding_one,"Welcome to FirstStep!")
//    }
//}