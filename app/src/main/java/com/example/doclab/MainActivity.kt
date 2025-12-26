package com.example.doclab

import android.os.Bundle
import androidx.compose.ui.graphics.Color
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.doclab.doctor.AddScreen
import com.example.doclab.doctor.HomeScreen
import com.example.doclab.doctor.ProfileScreen
import com.example.doclab.ui.theme.DoclabTheme
import com.example.doclab.user.UserDetails
import com.google.firebase.FirebaseApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        FirebaseApp.initializeApp(this)

        setContent {
            DoclabTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        Onboarding()
                    }
                }
            }
        }
    }
}

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Add : Screen("add")
    object Profile : Screen("profile")
}


@Composable
fun DoctorBottomAppBar() {
    val navController = rememberNavController()
    var selectedScreen by remember { mutableStateOf<Screen>(Screen.Home) }

    Scaffold(
        bottomBar = {
            BottomAppBar(
                containerColor = Color.LightGray
            ) {
                val iconModifier = Modifier.weight(1f)

                IconButton(
                    onClick = {
                        selectedScreen = Screen.Home
                        navController.navigate(Screen.Home.route) {
                            popUpTo(navController.graph.startDestinationId) { inclusive = true }
                        }
                    },
                    modifier = iconModifier
                ) {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = "Home",
                        tint = if (selectedScreen == Screen.Home) Color.Blue else Color.Black
                    )
                }

                IconButton(
                    onClick = {
                        selectedScreen = Screen.Add
                        navController.navigate(Screen.Add.route) {
                            popUpTo(navController.graph.startDestinationId) { inclusive = true }
                        }
                    },
                    modifier = iconModifier
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add",
                        tint = if (selectedScreen == Screen.Add) Color.Blue else Color.Black
                    )
                }

                IconButton(
                    onClick = {
                        selectedScreen = Screen.Profile
                        navController.navigate(Screen.Profile.route) {
                            popUpTo(navController.graph.startDestinationId) { inclusive = true }
                        }
                    },
                    modifier = iconModifier
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Profile",
                        tint = if (selectedScreen == Screen.Profile) Color.Blue else Color.Black
                    )
                }
            }
        }
    ) { innerPadding ->
        // Set up NavHost to switch screens
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) { HomeScreen() }
            composable(Screen.Add.route) { AddScreen(
                navController,
            ) }
            composable(Screen.Profile.route) { ProfileScreen() }
        }
    }
}


@Composable
fun Onboarding(){

    val navController=rememberNavController()

    NavHost(
        navController=navController,
        startDestination = "onboard1"

    )
    {
        composable("onboard1"){onBoardingScreen1(navController)}
        composable("onboard2"){onBoardingScreen2(navController)}
        composable("onboard3"){onBoardingScreen3(navController)}
        composable("signup"){SignupScreen(navController)}
        composable("login"){LoginScreen()}
        composable("userDetail"){
            UserDetails(
                navController,
                isPreview = TODO(),
                userDetail = TODO()
            )
        }
        composable("home"){HomeScreen()}
        composable("doctorScreen"){DoctorBottomAppBar()}



    }

}

@Preview
@Composable
fun preview(){
    DoctorBottomAppBar()
}











