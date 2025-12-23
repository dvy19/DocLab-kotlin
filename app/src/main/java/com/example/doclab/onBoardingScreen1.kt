package com.example.doclab

import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun onBoardingScreen1(navController: NavController) {
    OnboardingLayout(
        title = "Welcome",
        description = "Discover new features easily",
        buttonText = "Next"
    ) {
        navController.navigate("onboard2")
    }
}
