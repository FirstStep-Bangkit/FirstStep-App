package com.example.firststepapp.ui.main.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.firststepapp.R
import com.example.firststepapp.api.response.DashboardResult
import com.example.firststepapp.ui.component.ClickableIconWithText
import com.example.firststepapp.ui.component.IconHome
import com.example.firststepapp.ui.theme.FirstStepAppTheme
import com.example.firststepapp.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home (
    navControl: NavHostController,
    viewModel: MainViewModel,
    token: String
) {

    var isLoading by remember { mutableStateOf(false) }

    viewModel.isLoading.observeAsState().value?.let {
        isLoading = it
    }

    Scaffold(

    ) { innerPadding ->

        val dashboardResponse by viewModel._dashboardResponse.observeAsState()

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.inverseOnSurface)
        ) {
            Headline(dashboardResponse?.dashboardResult)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
                Box(
                    modifier = Modifier
                        .background(Color.Blue)
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                )
                Box(
                    modifier = Modifier
                        .background(Color.White, shape = RoundedCornerShape(10.dp))
                        .padding(20.dp)
                ) {
                    Column {
                        IconGrid(navController = navControl)
                        Article()
                    }
                }
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(vertical = 20.dp)
                ) {
                    if (isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }
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
) {
    val profilePicture: Painter = if (dashboardResult?.profilePicture == null) {
        painterResource(R.drawable.onboarding_one)
    } else {
        rememberImagePainter(data = dashboardResult.profilePicture)
    }

    Column(
        modifier = Modifier
            .padding(
                top = 40.dp,
                start = 20.dp,
                end = 20.dp,
                bottom = 40.dp
            )
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Halo, ${dashboardResult?.name}",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(20.dp))
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
            ) {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = profilePicture,
                    contentDescription = "Profile"
                )
            }
        }
    }
}

@Composable
fun IconGrid(navController: NavHostController) {
    val icons = IconHome()

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(16.dp),
        modifier = Modifier
            .padding(vertical = 20.dp)
            .fillMaxWidth()
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

@Composable
fun Article(){
    Column(
        modifier = Modifier
            .padding(horizontal = 10.dp, vertical = 30.dp)
    ) {
        Text(
            style = MaterialTheme.typography.titleLarge,
            text = "Artikel",
            modifier = Modifier
                .padding(bottom = 20.dp)
        )
        Text(
            text = "Belum ada artikel yang bisa ditampilkan",
            style = MaterialTheme.typography.bodyMedium.copy(
                fontStyle = FontStyle.Italic
            ),
            modifier = Modifier
                .fillMaxWidth(),
        )
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