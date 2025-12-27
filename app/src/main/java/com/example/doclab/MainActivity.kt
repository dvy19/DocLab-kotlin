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
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.doclab.doctor.AddScreen
import com.example.doclab.doctor.DoctorAddressScreen
import com.example.doclab.doctor.DoctorDetailScreen
import com.example.doclab.doctor.DoctorEducation
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

sealed class DoctorScreen(val route: String) {
    object Home : DoctorScreen("home")
    object Add : DoctorScreen("add")
    object Profile : DoctorScreen("profile/{uid}") {
        fun createRoute(uid: String) = "profile/$uid"
    }
}


@Composable
fun DoctorBottomAppBar() {
    val navController = rememberNavController()
    var selectedScreen by remember { mutableStateOf<DoctorScreen>(DoctorScreen.Home) }

    Scaffold(
        bottomBar = {
            BottomAppBar(
                containerColor = Color.LightGray
            ) {
                val iconModifier = Modifier.weight(1f)

                IconButton(
                    onClick = {
                        selectedScreen = DoctorScreen.Home
                        navController.navigate(DoctorScreen.Home.route) {
                            popUpTo(navController.graph.startDestinationId) { inclusive = true }
                        }
                    },
                    modifier = iconModifier
                ) {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = "Home",
                        tint = if (selectedScreen == DoctorScreen.Home) Color.Blue else Color.Black
                    )
                }

                IconButton(
                    onClick = {
                        selectedScreen = DoctorScreen.Add
                        navController.navigate(DoctorScreen.Add.route) {
                            popUpTo(navController.graph.startDestinationId) { inclusive = true }
                        }
                    },
                    modifier = iconModifier
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add",
                        tint = if (selectedScreen == DoctorScreen.Add) Color.Blue else Color.Black
                    )
                }

                IconButton(
                    onClick = {
                        selectedScreen = DoctorScreen.Profile
                        navController.navigate(DoctorScreen.Profile.route) {
                            popUpTo(navController.graph.startDestinationId) { inclusive = true }
                        }
                    },
                    modifier = iconModifier
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Profile",
                        tint = if (selectedScreen == DoctorScreen.Profile) Color.Blue else Color.Black
                    )
                }
            }
        }
    ) { innerPadding ->
        // Set up NavHost to switch screens
        NavHost(
            navController = navController,
            startDestination = DoctorScreen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(DoctorScreen.Home.route) { HomeScreen() }
            composable(DoctorScreen.Add.route) { AddScreen(
                navController
            ) }
            composable(
                route = DoctorScreen.Profile.route,
                arguments = listOf(
                    navArgument("uid") { type = NavType.StringType }
                )
            ) { backStackEntry ->

                val uid = backStackEntry.arguments?.getString("uid") ?: ""
                ProfileScreen(uid = uid)
            }
        }
    }
}


sealed class UserScreen(val route: String) {
    object Home : UserScreen("home")
    object Add : UserScreen("add")
    object Profile : UserScreen("profile/{uid}") {
        fun createRoute(uid: String) = "profile/$uid"
    }
}


@Composable
fun UserBottomAppBar() {
    val navController = rememberNavController()
    var selectedScreen by remember { mutableStateOf<UserScreen>(UserScreen.Home) }

    Scaffold(
        bottomBar = {
            BottomAppBar(
                containerColor = Color.LightGray
            ) {
                val iconModifier = Modifier.weight(1f)

                IconButton(
                    onClick = {
                        selectedScreen = UserScreen.Home
                        navController.navigate(UserScreen.Home.route) {
                            popUpTo(navController.graph.startDestinationId) { inclusive = true }
                        }
                    },
                    modifier = iconModifier
                ) {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = "Home",
                        tint = if (selectedScreen == UserScreen.Home) Color.Blue else Color.Black
                    )
                }

                IconButton(
                    onClick = {
                        selectedScreen = UserScreen.Add
                        navController.navigate(UserScreen.Add.route) {
                            popUpTo(navController.graph.startDestinationId) { inclusive = true }
                        }
                    },
                    modifier = iconModifier
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add",
                        tint = if (selectedScreen == UserScreen.Add) Color.Blue else Color.Black
                    )
                }

                IconButton(
                    onClick = {
                        selectedScreen = UserScreen.Profile
                        navController.navigate(UserScreen.Profile.route) {
                            popUpTo(navController.graph.startDestinationId) { inclusive = true }
                        }
                    },
                    modifier = iconModifier
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Profile",
                        tint = if (selectedScreen == UserScreen.Profile) Color.Blue else Color.Black
                    )
                }
            }
        }
    ) { innerPadding ->
        // Set up NavHost to switch screens
        NavHost(
            navController = navController,
            startDestination = UserScreen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(UserScreen.Home.route) { HomeScreen() }
            composable(UserScreen.Add.route) { AddScreen(
                navController
            ) }
            composable(
                route = UserScreen.Profile.route,
                arguments = listOf(
                    navArgument("uid") { type = NavType.StringType }
                )
            ) { backStackEntry ->

                val uid = backStackEntry.arguments?.getString("uid") ?: ""
                ProfileScreen(uid = uid)
            }
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
        composable("doctorDetail"){DoctorDetailScreen(navController)}
        composable("doctorDetailEdu"){DoctorEducation(navController)}
        composable("doctorDetailAdd"){DoctorAddressScreen(navController)}


        composable("doctorScreen"){DoctorBottomAppBar()}



    }

}

@Preview
@Composable
fun preview(){
    DoctorBottomAppBar()
}











