package com.example.firststepapp.ui.register

import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.firststepapp.R
import com.example.firststepapp.navigation.Screen
import com.example.firststepapp.ui.theme.FirstStepAppTheme

@Composable
fun Register(
    navController: NavHostController,
    onBackPressed: () -> Unit
){
    val backDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher

    DisposableEffect(backDispatcher) {
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                onBackPressed()
            }
        }
        backDispatcher?.addCallback(callback)
        onDispose {
            callback.remove()
        }
    }

    Column(
        modifier = Modifier
            .padding(40.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.onboarding_one),
            contentDescription = "logo"
        )
        Text(
            text = "Register",
            modifier = Modifier
                .padding(
                    top = 50.dp,
                    bottom = 50.dp
                ),
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold
            )
        )

        var firstName by remember { mutableStateOf(TextFieldValue("")) }
        var lastName by remember { mutableStateOf(TextFieldValue("")) }
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var confirmPassword by remember { mutableStateOf("") }

        //Nama Depan
        BasicTextField(
            value = firstName,
            onValueChange = { firstName = it },
            modifier = Modifier
                .padding(bottom = 10.dp)
                .border(
                    2.dp,
                    MaterialTheme.colorScheme.primary,
                    RoundedCornerShape(size = 10.dp)
                ),
            decorationBox = { innerTextField ->
                Row(
                    Modifier
                        .background(Color.Transparent)
                        .padding(10.dp)
                        .fillMaxWidth()
                ) {
                    if (firstName.text.isEmpty()) {
                        Text(
                            text = "Nama Depan",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                    innerTextField()
                }
            },
        )

        //Nama Belakang
        BasicTextField(
            value = lastName,
            onValueChange = { lastName = it },
            modifier = Modifier
                .padding(bottom = 10.dp)
                .border(
                    2.dp,
                    MaterialTheme.colorScheme.primary,
                    RoundedCornerShape(size = 10.dp)
                ),
            decorationBox = { innerTextField ->
                Row(
                    Modifier
                        .background(Color.Transparent)
                        .padding(10.dp)
                        .fillMaxWidth()
                ) {
                    if (lastName.text.isEmpty()) {
                        Text(
                            text = "Nama Belakang",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                    innerTextField()
                }
            },
        )

        //Email
        BasicTextField(
            value = email,
            onValueChange = { email = it },
            modifier = Modifier
                .padding(bottom = 10.dp)
                .border(
                    2.dp,
                    MaterialTheme.colorScheme.primary,
                    RoundedCornerShape(size = 10.dp)
                ),
            decorationBox = { innerTextField ->
                Row(
                    Modifier
                        .background(Color.Transparent)
                        .padding(10.dp)
                        .fillMaxWidth()
                ) {
                    if (email.isEmpty()) {
                        Text(
                            text = "Email",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                    innerTextField()
                }
            },
        )

        //Password
        BasicTextField(
            value = password,
            onValueChange = { password = it },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .padding(bottom = 10.dp)
                .border(
                    2.dp,
                    MaterialTheme.colorScheme.primary,
                    RoundedCornerShape(size = 10.dp)
                ),
            decorationBox = { innerTextField ->
                Row(
                    Modifier
                        .background(Color.Transparent)
                        .padding(10.dp)
                        .fillMaxWidth()
                ) {
                    if (password.isEmpty()) {
                        Text(
                            text = "Password",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                    innerTextField()
                }
            },
        )

        //Konfirmasi Password
        BasicTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .padding(bottom = 10.dp)
                .border(
                    2.dp,
                    MaterialTheme.colorScheme.primary,
                    RoundedCornerShape(size = 10.dp)
                ),
            decorationBox = { innerTextField ->
                Row(
                    Modifier
                        .background(Color.Transparent)
                        .padding(10.dp)
                        .fillMaxWidth()
                ) {
                    if (confirmPassword.isEmpty()) {
                        Text(
                            text = "Konfirmasi Password",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                    innerTextField()
                }
            },
        )

        //Tombol daftar
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .padding(top = 40.dp)
                .width(154.dp),
            shape = RoundedCornerShape(size = 10.dp),
            elevation =  ButtonDefaults.buttonElevation(
                defaultElevation = 5.dp,
                pressedElevation = 0.dp,
                disabledElevation = 0.dp
            )
        ) {
            Text(
                text = "Daftar",
                style = MaterialTheme.typography.bodyMedium
            )
        }

        //Konfirmasi punya akun atau belum
        Row(
            modifier = Modifier
                .padding(
                    top = 10.dp
                )
        ) {
            Text(
                text = "Sudah punya akun?",
                style = MaterialTheme.typography.bodySmall
            )
            ClickableText(
                onClick = {
                    navController.navigate(Screen.Login.route)
                },
                modifier = Modifier
                    .padding(
                        start = 5.dp
                    ),
                text = AnnotatedString("Masuk"),
                style = MaterialTheme.typography.bodySmall.copy(
                    color = MaterialTheme.colorScheme.primary
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterPreview(){
    val navController = rememberNavController()
    FirstStepAppTheme {
        Register(navController, onBackPressed = {})
    }
}