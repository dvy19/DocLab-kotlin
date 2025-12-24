package com.example.doclab

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.google.firebase.auth.FirebaseAuth

@Composable
fun SignupScreen(
    onSignupSuccess:()->Unit
) {

    var email by remember{ mutableStateOf("") }
    var password by remember{ mutableStateOf("") }
    var confirmPassword by remember{ mutableStateOf("") }
    var isLoading by remember{ mutableStateOf(false) }

    val context=LocalContext.current
    val auth= FirebaseAuth.getInstance()


    Column(
        modifier= Modifier.fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        Text(
            text="Create Account",
            fontSize=2.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier=Modifier.height(24.dp))

        OutlinedTextField(
            value=email,
            onValueChange = {email = it},
            label={Text("Email")},
            singleLine = true,
            modifier=Modifier.fillMaxSize()
        )

        Spacer(modifier=Modifier.height(24.dp))

        OutlinedTextField(
            value=password,
            onValueChange = {password= it},
            label={Text("Password")},
            singleLine = true,
            modifier=Modifier.fillMaxSize()
        )

        Spacer(modifier=Modifier.height(24.dp))

        OutlinedTextField(
            value=confirmPassword,
            onValueChange = {confirmPassword = it},
            label={Text("Confirm Password")},
            singleLine = true,
            modifier=Modifier.fillMaxSize()
        )

        Spacer(modifier=Modifier.height(24.dp))

        Button(

            onClick = {
                isLoading=true

                auth.createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener{task->

                        isLoading=false

                        if (task.isSuccessful) {
                            Toast.makeText(context, "Signup Successful", Toast.LENGTH_SHORT).show()
                            onSignupSuccess()
                        } else {
                            Toast.makeText(
                                context,
                                "Signup Failed",
                                Toast.LENGTH_LONG
                            ).show()
                        }

                    }

            },

            modifier=Modifier.fillMaxWidth(),
            enabled=!isLoading

        ) {
            Text(if (isLoading) "Creating..." else "Sign Up")
        }

    }



}
