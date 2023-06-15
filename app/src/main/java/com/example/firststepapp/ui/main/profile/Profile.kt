package com.example.firststepapp.ui.main.profile

import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import coil.compose.rememberImagePainter
import com.example.firststepapp.R
import com.example.firststepapp.api.response.ProfileResult
import com.example.firststepapp.navigation.Screen
import com.example.firststepapp.ui.theme.FirstStepAppTheme
import com.example.firststepapp.viewmodel.AuthViewModel
import com.example.firststepapp.viewmodel.MainViewModel
import java.io.File

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
            //Identity(profileResponse?.profileResult)//, onProfilePictureClick = {  })
            Identity(profileResponse?.profileResult, viewModel, token)
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
fun Identity(
    profileResult: ProfileResult?,
    viewModel: MainViewModel,
    token: String
) {
    val profilePicture: Painter = if (profileResult?.profilePicture == null) {
        painterResource(R.drawable.onboarding_one)
    } else {
        rememberImagePainter(data = profileResult.profilePicture)
    }

    var isDropdownExpanded by remember { mutableStateOf(false) }

    val context = LocalContext.current
    LaunchedEffect(profileResult) {
        viewModel.profile(context, token)
    }

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
            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 10.dp),
                painter = profilePicture,
                contentDescription = "Profile"
            )

            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(15.dp)
            ) {
                Box(
                    modifier = Modifier
                        .background(
                            color = Color.Yellow.copy(alpha = 0.5f),
                            shape = CircleShape
                        )
                        .size(36.dp)
                ) {
                    IconButton(
                        modifier = Modifier.size(36.dp),
                        onClick = { isDropdownExpanded = true }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "Edit Profile Picture",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }

                val context = LocalContext.current

                val launcherIntentGallery = rememberLauncherForActivityResult(
                    contract = ActivityResultContracts.StartActivityForResult()
                ) { result ->
                    if (result.resultCode == Activity.RESULT_OK) {
                        val selectedImg = result.data?.data as Uri
                        selectedImg.let { uri ->
                            val file = getFileFromUri(context, uri)
                            if (file != null) {
                                viewModel.uploadPhotoProfile(context, token, file) { success ->
                                    if (success) {
                                        Toast.makeText(context, "Berhasil upload foto", Toast.LENGTH_SHORT).show()
                                        viewModel.profile(context, token)
                                    } else {
                                        Toast.makeText(context, "Gagal upload foto", Toast.LENGTH_SHORT).show()
                                        Log.e(TAG, "Failed to upload profile photo")
                                    }
                                }
                            } else {
                                Log.e(TAG, "Failed to get file from URI: $uri")
                            }
                        }

                    }
                }

                DropdownMenu(
                    expanded = isDropdownExpanded,
                    onDismissRequest = { isDropdownExpanded = false }
                ) {
                    DropdownMenuItem(onClick = {
                        isDropdownExpanded = false

                        val intent = Intent(Intent.ACTION_GET_CONTENT)
                        intent.type = "image/*"
                        val chooser = Intent.createChooser(intent, "Pilih gambar")
                        launcherIntentGallery.launch(chooser)

                    }) {
                        Text(text = "Ganti Foto")
                    }
                    DropdownMenuItem(onClick = {
                        isDropdownExpanded = false
                        viewModel.deleteProfile(context, token) { success ->
                            if (success) {
                                Toast.makeText(context, "Berhasil hapus foto", Toast.LENGTH_SHORT).show()
                                viewModel.profile(context, token)
                            } else {
                                Toast.makeText(context, "Gagal hapus foto", Toast.LENGTH_SHORT).show()
                                Log.e(TAG, "Failed to upload profile photo")
                            }
                        }
                    }) {
                        Text(text = "Hapus Foto")
                    }
                }
            }
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

private fun getFileFromUri(context: Context, uri: Uri): File? {
    val contentResolver = context.contentResolver
    val inputStream = contentResolver.openInputStream(uri)
    val file = File(context.cacheDir, "temp_image.jpg")
    file.outputStream().use { outputStream ->
        inputStream?.copyTo(outputStream)
    }
    return file
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
                    text = profileResult?.mbti.toString(),
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
        val showSuccessDialog = remember { mutableStateOf(false) }

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

                val token = authViewModel.getUserPreferences("token").value
                val username = authViewModel.getUserPreferences("username").value

                if (token != null && username != null) {
                    mainViewModel.deleteUser(context, token, username) { success ->
                        if (success) {
                            showSuccessDialog.value = true
                            authViewModel.clearUserPreferences()
                        } else {
                            Log.e("Delete User", "Gagal menghapus akun")
                        }
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

        successDialog(
            showDialog = showSuccessDialog.value,
            onClose = {
                showSuccessDialog.value = false
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
    onClose: () -> Unit,
    navController: NavHostController? = null
) {
    if (showDialog) {
        DisposableEffect(Unit) {
            onClose()
            onDispose {}
        }
        AlertDialog(
            onDismissRequest = {},
            title = { Text(text = "Hapus Akun Berhasil") },
            text = { Text(text = "Akun Anda telah berhasil dihapus.") },
            confirmButton = {
                Button(
                    onClick = {
                        onClose()
                        navController?.navigate(Screen.Login.route) {
                            launchSingleTop = true
                            popUpTo(Screen.Profile.route) { inclusive = true }
                        }
                    },
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