package com.example.doclab

import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun onBoardingScreen3(navController: NavController) {
    OnboardingLayout(
        title = "Get Started",
        description = "Create an account to continue",
        buttonText = "Sign Up"
    ) {
        navController.navigate("signup") {
            popUpTo("onboard1") { inclusive = true }
        }
    }
}
