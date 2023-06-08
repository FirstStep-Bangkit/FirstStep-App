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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.firststepapp.R
import com.example.firststepapp.api.response.DashboardResult
import com.example.firststepapp.preferences.UserPreferences
import com.example.firststepapp.preferences.dataStore
import com.example.firststepapp.ui.component.ClickableIconWithText
import com.example.firststepapp.ui.component.IconHome
import com.example.firststepapp.ui.theme.FirstStepAppTheme
import com.example.firststepapp.viewmodel.AuthViewModel
import com.example.firststepapp.viewmodel.MainViewModel
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid as LazyVerticalGrid

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home (
    navControl: NavHostController,
    viewModel: MainViewModel,
    token: String
) {
    Scaffold(

    ) { innerPadding ->

        val dashboardResponse by viewModel._dashboardResponse.observeAsState()

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(20.dp)
                .fillMaxSize()
        ) {
            Headline(dashboardResponse?.dashboardResult)
            IconGrid(navController = navControl)
        }
    }

    LaunchedEffectComponent(viewModel, token)
}

@Composable
fun LaunchedEffectComponent(viewModel: MainViewModel, token: String) {
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        viewModel.dashboard(context,token)
    }
}

@Composable
fun Headline(
    dashboardResult: DashboardResult?
){
    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Halo, ${dashboardResult?.name}",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(20.dp))
            val profilePicture = dashboardResult?.profilePicture ?: painterResource(R.drawable.onboarding_one)
            Image(
                modifier = Modifier.size(50.dp),
                painter = profilePicture as Painter,
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
        //val navController = rememberNavController()
        //val viewModel = AuthViewModel
        //Home(navController,viewModel)
        //Headline()
        //IconGrid(navController = navController)
    }
}