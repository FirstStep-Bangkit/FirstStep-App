package com.example.firststepapp.ui.main.personality

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.firststepapp.R
import com.example.firststepapp.api.response.DashboardResult
import com.example.firststepapp.api.response.ProfileResult
import com.example.firststepapp.ui.theme.FirstStepAppTheme
import com.example.firststepapp.viewmodel.AuthViewModel

@Composable
fun Personality (
    navControl: NavHostController,
    authViewModel: AuthViewModel
){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = "Personality"
        )
    }
}

@Composable
fun Headline(
//    dashboardResult: DashboardResult?
){
    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
//                text = "Halo, ${dashboardResult?.name}",
                text = "Halo, Nama",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(20.dp))
//            val profilePicture = dashboardResult?.profilePicture ?: painterResource(R.drawable.onboarding_one)
            val profilePicture = painterResource(R.drawable.onboarding_one)
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
            ) {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = profilePicture as Painter,
                    contentDescription = "Profile"
                )
            }

        }
    }
}

@Composable
fun Personality(
//    dashboardResult: DashboardResult?
){
    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "INFJ",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = "(Introversion Intuition Feeling Judging)",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.weight(5f)
            )
            Text(
                text = "",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun Content(
//    dashboardResult: DashboardResult?
){
    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
//            val profilePicture = dashboardResult?.profilePicture ?: painterResource(R.drawable.onboarding_one)
            val profilePicture = painterResource(R.drawable.onboarding_one)
            Box(
                modifier = Modifier
                    .size(150.dp)
            ) {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = profilePicture as Painter,
                    contentDescription = "Profile"
                )
            }

        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(10.dp).fillMaxWidth()
        ){

        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
//                text = "Halo, ${dashboardResult?.name}",
                text = "Description",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.weight(1f)
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(0.dp,5.dp,0.dp,0.dp).fillMaxWidth()
        ) {
            Text(
//                text = "Halo, ${dashboardResult?.name}",
                text = "INFJ adalah tipe yang penuh empati, visioner, dan terdorong " +
                        "oleh nilai-nilai. Anda cenderung peka terhadap kebutuhan orang " +
                        "lain dan berdedikasi untuk membantu meningkatkan dunia.",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.weight(1f)
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(0.dp,5.dp,0.dp,0.dp).fillMaxWidth()
        ) {
            Text(
//                text = "Halo, ${dashboardResult?.name}",
                text = "Job",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.weight(1f)
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
//                text = "Halo, ${dashboardResult?.name}",
                text = "Profesi yang mungkin cocok sebagai konsultan, " +
                        "pengembang organisasi, atau penulis.",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PersonalityPreview(){
    FirstStepAppTheme {
        Column {
            Headline()
            Personality()
            Content()
        }
        //Status()
        //Setting()
        //confirmDialog(showDialog = true, onConfirm = {}, onCancel = {})
        //successDialog(showDialog = true, onClose = {})
    }
}