package com.example.firststepapp.ui.login

import android.content.ContentValues.TAG
import android.util.Log
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.AlertDialog
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavHostController
import com.example.firststepapp.R
import com.example.firststepapp.navigation.Screen
import com.example.firststepapp.viewmodel.AuthViewModel

@Composable
fun Login(
    navController: NavHostController,
    onBackPressed: () -> Unit,
    viewModel: AuthViewModel
) {
    val lifecycleOwner = LocalLifecycleOwner.current
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
            text = "Login",
            modifier = Modifier
                .padding(
                    top = 50.dp,
                    bottom = 50.dp
                ),
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold
            )
        )

        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }

        val context = LocalContext.current

        // Email
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
                Box(
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

        // Password
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
                Box(
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

        var loginClicked by remember { mutableStateOf(false) }
        val showDialog = remember { mutableStateOf(false) }

        Button(
            onClick = {
                if (!loginClicked) {
                    loginClicked = true
                    viewModel.login(context, email, password)

                    viewModel._loginResponse.removeObservers(lifecycleOwner)

                    viewModel._loginResponse.observeOnce(lifecycleOwner) { loginResponse ->
                        loginResponse.let { response ->
                            if (response.error != true) {
                                val token = response.loginResult?.token
                                val name = response.loginResult?.name
                                val email = response.loginResult?.email
                                val username = response.loginResult?.username

                                Log.e(TAG,"Login username $username")

                                if (token != null && name != null && email != null && username != null) {
                                    //viewModel.saveLoginSession(token, name, email, username)
                                    Log.e(TAG,"Login name $name")
                                    navController.navigate(Screen.Home.route) {
                                        launchSingleTop = true
                                        popUpTo(Screen.Login.route) { inclusive = true }
                                    }
                                }
                            } else {
                                showDialog.value = true
                            }
                        }
                        loginClicked = false
                    }
                }
            },
            modifier = Modifier
                .padding(top = 40.dp)
                .width(154.dp),
            shape = RoundedCornerShape(size = 10.dp),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 5.dp,
                pressedElevation = 0.dp,
                disabledElevation = 0.dp
            ),
        ) {
            Text(
                text = "Login",
                style = MaterialTheme.typography.bodyMedium
            )
        }

        if (showDialog.value) {
            AlertDialog(
                onDismissRequest = { showDialog.value = false },
                title = {
                    Text(text = "Kesalahan")
                },
                text = {
                    Text(text = "Login gagal, silahkan coba lagi !!!")
                },
                confirmButton = {
                    Button(
                        onClick = { showDialog.value = false },
                    ) {
                        Text(text = "Tutup")
                    }
                }
            )
        }

        // Konfirmasi punya akun atau belum
        Row(
            modifier = Modifier
                .padding(top = 10.dp)
        ) {
            Text(
                text = "Belum punya akun?",
                style = MaterialTheme.typography.bodySmall
            )
            ClickableText(
                onClick = {
                    navController.navigate(Screen.Register.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                },
                modifier = Modifier
                    .padding(start = 5.dp),
                text = AnnotatedString("Daftar"),
                style = MaterialTheme.typography.bodySmall.copy(
                    color = MaterialTheme.colorScheme.primary
                )
            )
        }
    }
}

fun <T> LiveData<T>.observeOnce(lifecycleOwner: LifecycleOwner, observer: Observer<T>) {
    observe(lifecycleOwner, object : Observer<T> {
        override fun onChanged(value: T) {
            observer.onChanged(value)
            removeObserver(this)
        }
    })
}

//@Preview(showBackground = true)
//@Composable
//fun LoginPreview(){
//    val navController = rememberNavController()
//    FirstStepAppTheme {
//        Login(navController, onBackPressed = {})
//    }
//}