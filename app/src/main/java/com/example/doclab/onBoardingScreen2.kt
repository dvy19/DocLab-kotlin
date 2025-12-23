package com.example.doclab

import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun onBoardingScreen2(navController: NavController) {
    OnboardingLayout(
        title = "Stay Organized",
        description = "Manage everything in one place",
        buttonText = "Next"
    ) {
        navController.navigate("onboard3")
    }
}
