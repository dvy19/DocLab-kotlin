package com.example.doclab

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.doclab.ui.theme.DoclabTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DoclabTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->





                }
            }
        }
    }
}

@Composable
fun LoginScreen(onLoginSuccess: () -> Unit) {

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var context=LocalContext.current.applicationContext

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 26.dp, vertical = 140.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            shape= RoundedCornerShape(20.dp),
            leadingIcon = {
                Icon(imageVector = Icons.Default.Person, contentDescription = "Username")
            },

            modifier=Modifier.fillMaxWidth()
                .padding(bottom=8.dp)
        )
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 26.dp, vertical = 140.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Username") },
            shape= RoundedCornerShape(20.dp),
            leadingIcon = {
                Icon(imageVector = Icons.Default.Person, contentDescription = "Password")
            },

            modifier=Modifier.fillMaxWidth()
                .padding(bottom=8.dp),
            visualTransformation = PasswordVisualTransformation()
        )

        Button(onClick = {
            if(auth(username,password)){
                onLoginSuccess()
                Toast.makeText(context,"Done",Toast.LENGTH_SHORT).show()
            }
        }

        ) { }
    }
}



private fun auth(username:String,password:String):Boolean
{
    val validName="amdin"
    val validPass="123"

    return username==validName && password==validPass
}



