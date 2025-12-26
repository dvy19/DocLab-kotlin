package com.example.doclab

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth

@Composable
fun SignupScreen(
    navController: NavController,
    isPreview: Boolean = false
) {
    var email by remember { mutableStateOf("") }
    var name by remember{mutableStateOf("")}
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }

    val context = LocalContext.current

    // ✅ Correct: single nullable auth
    val auth: FirebaseAuth? = if (!isPreview) {
        FirebaseAuth.getInstance()
    } else {
        null
    }


    Image(
        painter = painterResource(id = R.drawable.back), // your drawable resource
        contentDescription = null,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop // scales the image to fill the screen
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
    ) {


        Spacer(modifier = Modifier.height(34.dp))
        


        Text(
            text = "Let's Get Started",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Left
        )

        Spacer(modifier = Modifier.height(10.dp))


        Text(
            text="Create an account to get appointments with the best doctors",
            fontSize=16.sp,
            color = Color.DarkGray,
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text="Full Name",
            fontSize=16.sp,
            color = Color.Black,
        )
        Spacer(modifier = Modifier.height(8.dp))


            OutlinedTextField(
                value = name,
                onValueChange = { name=it},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .background(Color.White, shape = RoundedCornerShape(14.dp)), // background + border radius
                placeholder = {
                    Text(
                        text = "John Doe",
                        color = Color.Gray  // placeholder text color
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Person, // person icon
                        contentDescription = "Person Icon",
                        tint = Color.Gray
                    )
                },
                textStyle = TextStyle(
                    color = Color.Black, // input text color
                    fontSize = 16.sp
                ),
                singleLine = true,



            )


        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text="Email",
            fontSize=16.sp,
            color = Color.Black,
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email=it},
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .background(Color.White, shape = RoundedCornerShape(14.dp)), // background + border radius
            placeholder = {
                Text(
                    text = "Enter Your Email",
                    color = Color.Gray // placeholder text color
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Email, // person icon
                    contentDescription = "Person Icon",
                    tint =Color.Gray
                )
            },
            textStyle = TextStyle(
                color = Color.Black, // input text color
                fontSize = 16.sp
            ),
            singleLine = true,



            )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text="Password",
            fontSize=16.sp,
            color = Color.Black,
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password=it},
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .background(Color.White, shape = RoundedCornerShape(14.dp)), // background + border radius
            placeholder = {
                Text(
                    text = "Create your Password",
                    color = Color.Gray // placeholder text color
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock, // person icon
                    contentDescription = "Person Icon",
                    tint = Color.Gray
                )
            },
            textStyle = TextStyle(
                color = Color.Black, // input text color
                fontSize = 16.sp
            ),
            singleLine = true,



            )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                if (isPreview) return@Button

                isLoading = true

                auth?.createUserWithEmailAndPassword(email.trim(), password)
                    ?.addOnCompleteListener { task ->

                        isLoading = false

                        if (task.isSuccessful) {
                            Toast.makeText(
                                context,
                                "Signup Successful",
                                Toast.LENGTH_SHORT
                            ).show()

                            navController.navigate("doctorScreen") {
                                popUpTo("signup") { inclusive = true }
                            }
                        } else {
                            val errorMessage = task.exception?.message ?: "Signup Failed"

                            Toast.makeText(
                                context,
                                errorMessage,
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),

            shape = RoundedCornerShape(14.dp), // set border radius here
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF137FEC), // background color
                contentColor = Color.White          // text/icon color
            ),

            enabled = !isLoading
        ) {
            Text(if (isLoading) "Creating..." else "Sign Up")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row ( modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,   // centers children horizontally
            verticalAlignment = Alignment.CenterVertically ){
            Text(
                textAlign = TextAlign.Center,
                text = "Already have an account? ",
                color = Color.Gray
            )

            Text(
                text = "Login",
                color = Color.Blue,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable {
                    navController.navigate("login")
                }
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun SignupScreenPreview() {
    SignupScreen(
        navController = rememberNavController(),
        isPreview = true
    )
}
