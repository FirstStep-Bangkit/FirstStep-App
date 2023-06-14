package com.example.firststepapp.ui.main.personality

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.firststepapp.R
import com.example.firststepapp.ui.theme.FirstStepAppTheme
import com.example.firststepapp.viewmodel.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Personality (
    navControl: NavHostController,
    authViewModel: AuthViewModel
){
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Header()
            Identity()
        }
    }
}

@Composable
fun Header (){
    Column(
        modifier = Modifier
            .padding(
                start = 20.dp,
                top = 20.dp,
                bottom = 10.dp,
                end = 20.dp
            )
            .fillMaxWidth()
    ) {
        androidx.compose.material3.Text(
            text = "Your personality",
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold
            )
        )
    }
}

@Composable
fun Identity(

) {
    Column(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        Image(
            painter = painterResource(R.drawable.onboarding_one),
            contentDescription = "logo"
        )
        Text(
            modifier = Modifier
                .padding(top = 20.dp),
            text = "Type",
            style = MaterialTheme.typography.headlineSmall.copy(
                fontWeight = FontWeight.Bold
            )
        )
        Text(
            modifier = Modifier
                .padding(top = 5.dp),
            text = "(Singkatan)",
            style = MaterialTheme.typography.bodyMedium.copy(
                fontStyle = FontStyle.Italic
            )
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Content(
    header: String,
    description: String,
    color: Color, // color
) {
    var expanded by remember { mutableStateOf(false) } // Expand State
    val rotationState by animateFloatAsState(
        targetValue = if (expanded) 180f else 0f,
        label = "Rotation state of expand icon button",
    )

    Card(
        modifier = Modifier.padding(8.dp),
        shape = RoundedCornerShape(8.dp), // shape
        onClick = { expanded = !expanded }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .animateContentSize() // edit animation here
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween // control the header alignment over here.
            ) {
                Text(
                    text = header,
                    color = color, // header color
                    fontSize = 20.sp,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Normal,
                )
                IconButton(
                    modifier = Modifier.rotate(rotationState),
                    onClick = { expanded = !expanded }
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        tint = color, // Icon Color
                        contentDescription = "Drop Down Arrow"
                    )
                }
            }
            if (expanded) {
                Text(
                    text = description,
                    color = color, // description color
                    fontSize = 16.sp,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PersonalityPreview(){
    FirstStepAppTheme {
        //val navController = rememberNavController()
        //val authViewModel = AuthViewModel
        //Personality()
    }
}