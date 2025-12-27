package com.example.doclab.doctor

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.doclab.R
import com.example.doclab.user.UserDetails
import com.example.doclab.user.userDetail
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun DoctorAddressScreen(navController: NavController,
                    isPreview:Boolean=false,
                    doctorAddress: doctorAddress= doctorAddress()
){

    var hospital by remember { mutableStateOf("") }
    var clinic by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("") }


    val context=LocalContext.current

    // ✅ Correct: single nullable auth
    val auth: FirebaseAuth? = if (!isPreview) {
        FirebaseAuth.getInstance()
    } else {
        null
    }



    Box(
        modifier = Modifier.fillMaxSize()
    ){

        Image(
            painter = painterResource(id= R.drawable.appback),
            contentDescription = null,
            modifier=Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier=Modifier.fillMaxSize()
                .verticalScroll(rememberScrollState())
                .imePadding()
                .padding(24.dp),

            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Image(
                    painter = painterResource(id = R.drawable.arrow),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                        .padding(8.dp)

                )

                Text(
                    text = "Next",
                    color = Color.Blue,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,

                    )
            }

            Spacer(modifier = Modifier.height(24.dp))


            Spacer(modifier = Modifier.height(24.dp))
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Enter Hospital Name",
                modifier=Modifier.align(Alignment.Start),
                fontSize = 20.sp,
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = hospital,
                onValueChange = { hospital= it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .background(
                        Color.White,
                        shape = RoundedCornerShape(16.dp)
                    ), // background + border radius
                placeholder = {
                    Text(
                        text = "--",
                        color = Color.Gray  // placeholder text color
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
                text = "Enter Clinic Address",
                modifier=Modifier.align(Alignment.Start),
                fontSize = 20.sp,

                )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = clinic,
                onValueChange = { clinic=it},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .background(Color.White, shape = RoundedCornerShape(20.dp)), // background + border radius
                placeholder = {
                    Text(
                        text = "e.g.1 yrs",
                        color = Color.Gray  // placeholder text color
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
                text = "Enter City",
                modifier=Modifier.align(Alignment.Start),
                fontSize = 20.sp,

                )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = city,
                onValueChange = { city=it},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .background(Color.White, shape = RoundedCornerShape(20.dp)), // background + border radius
                placeholder = {
                    Text(
                        text = "--",
                        color = Color.Gray  // placeholder text color
                    )
                },

                textStyle = TextStyle(
                    color = Color.Black, // input text color
                    fontSize = 16.sp
                ),


                )

            Spacer(modifier = Modifier.height(16.dp))








            Button(

                onClick ={
                    if(isPreview) return@Button

                    val uid=auth?.currentUser?.uid
                    if(uid==null){
                        Toast.makeText(context,"user not logged in",Toast.LENGTH_SHORT).show()
                        return@Button
                    }


                    var doctor_edu_detail= doctorAddress(
                        hospitalName = hospital,
                        clinic = clinic,
                        city = city


                    )

                    FirebaseFirestore.getInstance()
                        .collection("doctors")
                        .document(uid)
                        .collection("profile")
                        .document("address details")
                        .set(doctor_edu_detail)
                        .addOnSuccessListener{
                            Toast.makeText(context,"Saved Successfully",Toast.LENGTH_SHORT).show()

                            navController.navigate("doctorScreen") {
                                popUpTo("signup") { inclusive = true }
                            }
                        }
                        .addOnFailureListener{e->
                            Toast.makeText(context,"${e.message}",Toast.LENGTH_SHORT).show()


                        }

                },


                modifier=Modifier.fillMaxWidth()
                    .height(56.dp),

                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF137FEC), // background color
                    contentColor = Color.White          // text/icon color
                ),


                ) {

                Text("Finish Profile", fontSize = 16.sp)
            }
        }
    }










}




@Preview
@Composable
fun doctorAddressPreview(){

    // 2️⃣ Fake user data
    val previewDoctorAdd= doctorAddress(
        hospitalName = "Heart and Lung",
        clinic = "MBBS",
        city = "3yrs"

    )
    DoctorAddressScreen(
        navController = rememberNavController(),
        isPreview = true,
        doctorAddress=previewDoctorAdd

    )
}



