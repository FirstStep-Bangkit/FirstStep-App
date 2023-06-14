package com.example.firststepapp.ui.main.personality

import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.animation.animateContentSize
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
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.firststepapp.R
import com.example.firststepapp.api.response.PersonalityResponse
import com.example.firststepapp.navigation.Screen
import com.example.firststepapp.ui.theme.FirstStepAppTheme
import com.example.firststepapp.viewmodel.AuthViewModel
import com.example.firststepapp.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Personality (
    navControl: NavHostController,
    authViewModel: AuthViewModel,
    viewModel: MainViewModel,
    token: String
){
    Scaffold { innerPadding ->

        val personalityResponse by viewModel._personalityResponse.observeAsState()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Header()
            Identity(personalityResponse)
            personalityResponse?.description?.let {
                Content(
                    header = "Deskripsi diri anda",
                    description = it,
                )
            }
            personalityResponse?.job?.let {
                Content(
                    header = "Pekerjaan yang cocok",
                    description = it,
                )
            }
        }
    }
    LaunchedEffectComponent(viewModel, token)

    val backCallback = remember {
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                navControl.popBackStack(route = Screen.Home.route, inclusive = false)
            }
        }
    }

    val lifecycleOwner = LocalLifecycleOwner.current
    val onBackPressedDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher

    DisposableEffect(Unit) {
        onBackPressedDispatcher?.addCallback(lifecycleOwner, backCallback)
        onDispose {
            backCallback.remove()
        }
    }
}

@Composable
fun LaunchedEffectComponent(viewModel: MainViewModel, token: String) {
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        viewModel.personality(context,token)
    }
}

@Composable
fun Header (

){
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
        Text(
            text = "Your personality",
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold
            )
        )
    }
}

@Composable
fun Identity(
    personalityResponse: PersonalityResponse?
) {
    val mbti = personalityResponse?.mbti

    val personalityPict = when (mbti) {
        "ENTJ" -> R.drawable.entj
        "ENTP" -> R.drawable.entp
        "ESFP" -> R.drawable.esfp
        "ESTJ" -> R.drawable.estj
        "ESTP" -> R.drawable.estp
        "INFJ" -> R.drawable.infj
        "INTJ" -> R.drawable.intj
        "INTP" -> R.drawable.intp
        "ISFJ" -> R.drawable.isfj
        "ISFP" -> R.drawable.isfp
        "ISTP" -> R.drawable.istp
        else -> R.drawable.onboarding_one
    }

    Column(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        Image(
            painter = painterResource(personalityPict),
            contentDescription = "logo"
        )
        personalityResponse?.mbti?.let {
            Text(
                modifier = Modifier
                    .padding(top = 20.dp),
                text = it,
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.Bold
                )
            )
        }
        Text(
            modifier = Modifier
                .padding(top = 5.dp),
            text = "(${personalityResponse?.acronym})",
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
) {
    var expanded by remember { mutableStateOf(false) }
    val rotationState by animateFloatAsState(
        targetValue = if (expanded) 180f else 0f,
        label = "Rotasi icon",
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
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = header,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Normal
                    )
                )
                IconButton(
                    modifier = Modifier.rotate(rotationState),
                    onClick = { expanded = !expanded }
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = "Drop Down icon"
                    )
                }
            }
            if (expanded) {
                Text(
                    text = description,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Normal
                    ),
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