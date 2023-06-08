package com.example.firststepapp.ui.main.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.firststepapp.R
import com.example.firststepapp.ui.theme.FirstStepAppTheme
import com.example.firststepapp.viewmodel.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Profile(
    navControl: NavHostController,
    authViewModel: AuthViewModel
) {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(20.dp)
        ) {
            Header()
            Identity()
            Status()
            Setting()
        }
    }
}

@Composable
fun Header (

){
    Column(
        modifier = Modifier
            .padding(
                start = 10.dp,
                bottom = 10.dp
            )
            .fillMaxWidth()
    ) {
        Text(
            text = "Profil",
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold
            )
        )
    }
}

@Composable
fun Identity (

){
    Column(
        modifier = Modifier
            .padding(
                top = 10.dp,
                bottom = 10.dp,
                start = 10.dp
            )
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(150.dp)
                .clip(CircleShape)
        ) {
            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 10.dp),
                painter = painterResource(R.drawable.onboarding_one),
                contentDescription = "Profile"
            )
        }
        Text(
            modifier = Modifier
                .padding(
                    top = 10.dp,
                    bottom = 5.dp
                ),
            text = "Xi Jinping",
            style = MaterialTheme.typography.headlineSmall
        )
        Text(
            modifier = Modifier
                .padding(
                    bottom = 10.dp
                ),
            text = "User",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
fun Status(

){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(
                top = 10.dp,
                bottom = 10.dp
            )
            .fillMaxWidth(),
    ) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primary
            ),
            modifier = Modifier
                .width(300.dp)
        ) {
            Row(
                modifier = Modifier
                    .padding(
                        horizontal = 15.dp,
                        vertical = 10.dp
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier
                        .padding(
                            start = 30.dp
                        )
                        .weight(1f),
                    text = "INTJ" ,
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                )
                Image(
                    modifier = Modifier
                        .size(50.dp)
                        .weight(1f),
                    painter = painterResource(R.drawable.onboarding_one),
                    contentDescription = "image"
                )
            }
        }
    }
}

@Composable
fun Setting(

){
    Column(
        modifier = Modifier
            .padding(
                top = 30.dp,
                bottom = 10.dp,
                start = 10.dp
            )
            .fillMaxWidth(),
    ) {
        Text(
            text = "Pengaturan",
            style = MaterialTheme.typography.titleLarge
        )
        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.inversePrimary
            ),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .padding(
                        horizontal = 15.dp,
                        vertical = 10.dp
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = "Gembok",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
                Text(
                    text = "Ganti password",
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePreview(){
    FirstStepAppTheme {
        //Status()
        Setting()
    }
}