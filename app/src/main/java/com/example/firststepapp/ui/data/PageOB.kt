package com.example.firststepapp.ui.data

import androidx.annotation.DrawableRes
import com.example.firststepapp.R

sealed class PageOB(
    @DrawableRes val image: Int,
    val title: String,
    val description: String
) {
    object First : PageOB(
        image = R.drawable.onboarding_one,
        title = "Welcome",
        description = "Selamat datang diaplikasi FirstStep. Aplikasi untuk mengetahui kepribadian Kamu."
    )

    object Second : PageOB(
        image = R.drawable.onboarding_one,
        title = "Know your personality ",
        description = "Cari tahu kepribadian kamu disini dengan mengikuti beberapa tes. Dan lebih jauh mengenal diri kamu sendiri."
    )

    object Third : PageOB(
        image = R.drawable.onboarding_one,
        title = "Lets Started",
        description = "Mulai Gaskeun."
    )
}
