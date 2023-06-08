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
        title = "Meeting",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod."
    )

    object Second : PageOB(
        image = R.drawable.onboarding_one,
        title = "Coordination",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod."
    )

    object Third : PageOB(
        image = R.drawable.onboarding_one,
        title = "Dialogue",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod."
    )
}
