package com.example.firststepapp.ui.main.test

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.firststepapp.ui.component.Question
import com.example.firststepapp.viewmodel.AuthViewModel
import com.example.firststepapp.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Test(
    navControl: NavHostController,
    viewModel: MainViewModel,
    authViewModel: AuthViewModel,
    token: String
) {
    val context = LocalContext.current

    viewModel.getQuiz(context,token)
    val quizResponse = viewModel._quizResponse.value

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Header()
            quizResponse?.let { response ->
                LazyColumn {
                    items(response.questions.orEmpty()) { question ->
                        Question(question = question.orEmpty())
                    }
                }
            }
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
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Test",
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold
            )
        )
        Text(
            modifier = Modifier
                .padding(top = 5.dp),
            text = "Kerjakan dengan sejujurnya, karena jawaban anda dapat mempengaruhi keakuratan hasil",
            style = MaterialTheme.typography.bodyMedium.copy(
                fontStyle = FontStyle.Italic
            )
        )
    }
}