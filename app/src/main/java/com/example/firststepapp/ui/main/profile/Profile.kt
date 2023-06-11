package com.example.firststepapp.ui.main.profile

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.firststepapp.R
import com.example.firststepapp.api.response.ProfileResult
import com.example.firststepapp.navigation.Screen
import com.example.firststepapp.ui.theme.FirstStepAppTheme
import com.example.firststepapp.viewmodel.AuthViewModel
import com.example.firststepapp.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Profile(
    navControl: NavHostController,
    viewModel: MainViewModel,
    authViewModel: AuthViewModel,
    token: String
) {
    Scaffold { innerPadding ->

        val profileResponse by viewModel._profileResponse.observeAsState()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Header()
            Identity(profileResponse?.profileResult)
            Status(profileResponse?.profileResult)
            Setting(navControl, authViewModel, viewModel)
            LogoutButton(
                onClick = {
                    authViewModel.clearUserPreferences()
                    navControl.navigate(Screen.Login.route) {
                        launchSingleTop = true
                        popUpTo(Screen.Profile.route) { inclusive = true }
                    }
                }
            )
        }
    }

    LaunchedEffectComponent(viewModel, token)
}

@Composable
fun LaunchedEffectComponent(viewModel: MainViewModel, token: String) {
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        viewModel.profile(context,token)
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
    profileResult: ProfileResult?
){
    Column(
        modifier = Modifier
            .padding(
                top = 10.dp,
                bottom = 10.dp,
                start = 20.dp,
                end = 20.dp
            )
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .size(150.dp)
                .clip(CircleShape)
        ) {
            val profilePicture = profileResult?.profilePicture ?: painterResource(R.drawable.onboarding_one)
            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 10.dp),
                painter = profilePicture as Painter,
                contentDescription = "Profile"
            )
        }
        Text(
            modifier = Modifier
                .padding(
                    top = 10.dp,
                    bottom = 5.dp
                ),
            text = profileResult?.name.toString(),
            style = MaterialTheme.typography.headlineSmall
        )
        Text(
            modifier = Modifier
                .padding(
                    bottom = 10.dp
                ),
            text = profileResult?.status.toString(),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
fun Status(
    profileResult: ProfileResult?
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(
                top = 10.dp,
                bottom = 10.dp,
                start = 20.dp,
                end = 20.dp
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
                    .padding(horizontal = 15.dp, vertical = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier
                        .padding(start = 20.dp)
                        .weight(1f),
                    text = "Personality Anda :",
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                )
                Spacer(modifier = Modifier.weight(0.1f))
                Text(
                    modifier = Modifier.padding(end = 20.dp),
                    text = profileResult?.mBTI.toString(),
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                )
            }
        }

    }
}

@Composable
fun Setting(
    navControl: NavHostController,
    authViewModel: AuthViewModel = viewModel(),
    mainViewModel: MainViewModel = viewModel()
) {

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .padding(
                top = 30.dp,
                bottom = 10.dp,
                start = 20.dp,
                end = 20.dp
            )
            .fillMaxWidth(),
    ) {
        Text(
            text = "Pengaturan",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .padding(bottom = 5.dp)
        )
        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.inverseOnSurface
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp)
                .clickable {
                    navControl.navigate(
                        Screen.ChangePassword.route
                    )
                }
        ) {
            Row(
                modifier = Modifier
                    .padding(
                        horizontal = 15.dp,
                        vertical = 15.dp,
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = "Gembok",
                    modifier = Modifier
                        .padding(
                            end = 15.dp
                        )
                )
                Text(
                    text = "Ganti password",
                )
            }
        }

        val showDialog = remember { mutableStateOf(false) }

        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.inverseOnSurface
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp)
                .clickable {
                    showDialog.value = true
                }
        ) {
            Row(
                modifier = Modifier
                    .padding(
                        horizontal = 15.dp,
                        vertical = 15.dp,
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Hapus",
                    modifier = Modifier
                        .padding(
                            end = 15.dp
                        )
                )
                Text(
                    text = "Hapus akun",
                )
            }
        }

        confirmDialog(
            showDialog = showDialog.value,
            onConfirm = {
                // Lakukan tindakan hapus akun di sini
                val token = authViewModel.getUserPreferences("token").value
                val username = authViewModel.getUserPreferences("username").value

                if (token != null && username != null) {
                    mainViewModel.deleteUser(context,token,username)
                    authViewModel.clearUserPreferences()
                    navControl.navigate(Screen.Login.route) {
                        launchSingleTop = true
                        popUpTo(Screen.Profile.route) { inclusive = true }
                    }
                } else {
                        Log.e("Username & password", "Tidak ditemukan")
                }
                showDialog.value = false
            },
            onCancel = {
                showDialog.value = false
            }
        )
    }
}

@Composable
fun confirmDialog(
    showDialog: Boolean,
    onConfirm: () -> Unit,
    onCancel: () -> Unit
) {
    if (showDialog) {
        AlertDialog(
            onDismissRequest = onCancel,
            title = { Text(text = "Konfirmasi Hapus Akun") },
            text = { Text(text = "Apakah Anda yakin ingin menghapus akun?") },
            confirmButton = {
                Button(
                    onClick = {onConfirm()},
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                ) {
                    Text(text = "OK")
                }
            },
            dismissButton = {
                Button(
                    onClick = {onCancel()},
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Gray),
                ) {
                    Text(text = "Batal")
                }
            }
        )
    }
}

@Composable
fun successDialog(
    showDialog: Boolean,
    onClose: () -> Unit
) {
    if (showDialog) {
        AlertDialog(
            onDismissRequest = onClose,
            title = { Text(text = "Hapus Akun Berhasil") },
            text = { Text(text = "Akun Anda telah berhasil dihapus.") },
            confirmButton = {
                Button(
                    onClick = onClose,
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                ) {
                    Text(text = "Tutup")
                }
            }
        )
    }
}

@Composable
fun LogoutButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .padding(30.dp)
            .fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.error
        )
    ) {
        Text(
            text = "Logout",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePreview(){
    FirstStepAppTheme {
        //Status()
        //Setting()
        //confirmDialog(showDialog = true, onConfirm = {}, onCancel = {})
        //successDialog(showDialog = true, onClose = {})
    }
}