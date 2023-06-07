package com.example.firststepapp.ui.main.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import com.example.firststepapp.ui.component.ClickableIconWithText
import com.example.firststepapp.ui.component.IconHome
import com.example.firststepapp.ui.theme.FirstStepAppTheme
import com.example.firststepapp.viewmodel.AuthViewModel
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid as LazyVerticalGrid

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home (
    navControl: NavHostController,
    viewModel: AuthViewModel
){
    Scaffold(

    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(20.dp)
                .fillMaxSize()
        ) {
            Headline()
            IconGrid(navController = navControl)
        }
    }
}

@Composable
fun Headline(
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier
            .padding(10.dp)
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Halo, Aris",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(20.dp))
            Image(
                modifier = Modifier.size(50.dp),
                painter = painterResource(R.drawable.onboarding_one),
                contentDescription = "Profile"
            )
        }
    }
}

@Composable
fun IconGrid(navController: NavHostController) {
    val icons = IconHome()

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(16.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        items(icons) { iconWithText ->
            ClickableIconWithText(
                icon = iconWithText.icon,
                text = iconWithText.title,
                destination = iconWithText.screen,
                navController = navController
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview(){
    FirstStepAppTheme {
        val navController = rememberNavController()
        //val viewModel = AuthViewModel
        //Home(navController)
        //Headline()
        IconGrid(navController = navController)
    }
}