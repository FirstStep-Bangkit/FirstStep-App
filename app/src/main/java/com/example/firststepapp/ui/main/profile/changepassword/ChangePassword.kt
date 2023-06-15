package com.example.firststepapp.ui.main.profile.changepassword

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.firststepapp.navigation.Screen
import com.example.firststepapp.viewmodel.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ChangePassword(
    navControl: NavHostController,
    viewModel: MainViewModel,
    token: String
){
    Column(
        modifier = Modifier
            .padding(40.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier
                .padding(bottom = 20.dp),
            text = "Ganti Password",
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold
            )
        )

        var oldPassword by remember { mutableStateOf("") }
        var newPassword by remember { mutableStateOf("") }
        var confirmPassword by remember { mutableStateOf("") }
        var passwordMatch by remember { mutableStateOf(false) }

        val context = LocalContext.current

        var changePasswordStatus by remember { mutableStateOf(ChangePasswordStatus.NONE) }
        var changePasswordMessage by remember { mutableStateOf("") }
        var showChangePasswordStatus by remember { mutableStateOf(false) }

        //Old Password
        BasicTextField(
            value = oldPassword,
            onValueChange = {
                oldPassword = it
            },
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
                    if (oldPassword.isEmpty()) {
                        Text(
                            text = "Password",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    innerTextField()
                }
            },
        )

        BasicTextField(
            value = newPassword,
            onValueChange = {
                newPassword = it
            },
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
                    if (newPassword.isEmpty()) {
                        Text(
                            text = "Password",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    innerTextField()
                }
            },
        )

        //Konfirmasi Password
        BasicTextField(
            value = confirmPassword,
            onValueChange = {
                confirmPassword = it
                passwordMatch = newPassword != confirmPassword
            },
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
                    if (confirmPassword.isEmpty()) {
                        Text(
                            text = "Konfirmasi Password",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    innerTextField()
                }
            },
        )

        // Validasi konfirmasi password
        if (passwordMatch) {
            Text(
                text = "Password tidak sesuai",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(start = 8.dp, top = 4.dp)
            )
        }

        //Tombol daftar
        val isButtonEnabled by remember {
            derivedStateOf {
                oldPassword.isNotBlank() &&
                confirmPassword.isNotBlank() &&
                !passwordMatch
            }
        }

        if (changePasswordStatus != ChangePasswordStatus.NONE) {
            AlertDialog(
                onDismissRequest = {
                    changePasswordStatus = ChangePasswordStatus.NONE
                },
                title = {
                    Text(text = if (changePasswordStatus == ChangePasswordStatus.SUCCESS) "Sukses" else "Gagal")
                },
                text = {
                    Text(text = changePasswordMessage)
                },
                confirmButton = {
                    Button(
                        onClick = {
                            changePasswordStatus = ChangePasswordStatus.NONE
                        }
                    ) {
                        Text("OK")
                    }
                }
            )
        }

        Button(
            onClick = {
                viewModel.changePassword(context, oldPassword, newPassword, token) { success ->
                    if (success) {
                        changePasswordStatus = ChangePasswordStatus.SUCCESS
                        changePasswordMessage = "Register berhasil"
                        showChangePasswordStatus = true

                        CoroutineScope(Dispatchers.Main).launch {
                            delay(5000L)
                            changePasswordStatus = ChangePasswordStatus.NONE
                            showChangePasswordStatus = false
                        }

                        navControl.navigate(Screen.Profile.route) {
                            launchSingleTop = true
                            popUpTo(Screen.ChangePassword.route) { inclusive = true }
                        }

                    } else {
                        changePasswordStatus = ChangePasswordStatus.FAILURE
                        changePasswordMessage = "Register gagal"
                        showChangePasswordStatus = true
                    }
                }
            },
            modifier = Modifier
                .padding(top = 40.dp)
                .width(154.dp),
            shape = RoundedCornerShape(size = 10.dp),
            elevation =  ButtonDefaults.buttonElevation(
                defaultElevation = 5.dp,
                pressedElevation = 0.dp,
                disabledElevation = 0.dp
            ),
            enabled = isButtonEnabled
        ) {
            Text(
                text = "Ganti Password",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}