package com.example.doclab.doctor

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.doclab.R
import com.example.doclab.user.UserDetails
import com.example.doclab.user.userDetail
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun DoctorScreen(navController: NavController,
                 isPreview: Boolean=false,
                 doctorDetail: doctorDetail


){

    var age by remember{ mutableStateOf("") }
    var name by remember{ mutableStateOf("") }
    var phoneNumber by remember{ mutableStateOf("") }
    var gender by remember{mutableStateOf("")}

    val context=LocalContext.current

    // ✅ Correct: single nullable auth
    val auth: FirebaseAuth? = if (!isPreview) {
        FirebaseAuth.getInstance()
    } else {
        null
    }

    Image(
        painter = painterResource(id= R.drawable.appback),
        contentDescription = null,
        modifier=Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )

    Column(
        modifier=Modifier.fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier=Modifier.height(12.dp))

        Row ( modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically ){

            Image(
                painter = painterResource(id= R.drawable.arrow),
                contentDescription = null,
                modifier = Modifier.size(24.dp)
                    .padding(8.dp)

            )

            Text(
                text = "Complete Profile",
                color = Color.Blue,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,

                )
        }

        Spacer(modifier=Modifier.height(24.dp))

        Image(
            painter = painterResource(id= R.drawable.signup),
            contentDescription = null,
            modifier=Modifier.size(150.dp)
                .clip(CircleShape)
                .border(2.dp, Color.Blue,CircleShape)
                .padding(8.dp),
            alignment =Alignment.Center,
            contentScale = ContentScale.Crop,
        )

        Spacer(modifier=Modifier.height(12.dp))

        Text(
            text = "Add Profile Photo",
            fontSize =20.sp,
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = "Let us identify you",
            fontSize =16.sp,
        )

        Spacer(modifier = Modifier.height(24.dp))
        Spacer(modifier = Modifier.height(8.dp))


        OutlinedTextField(
            value = age,
            onValueChange = { age=it},
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .background(Color.White, shape = RoundedCornerShape(16.dp)), // background + border radius
            placeholder = {
                Text(
                    text = "Enter your Age",
                    color = Color.Gray  // placeholder text color
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.DateRange, // person icon
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

        val genders = listOf("Male", "Female", "Other")

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            genders.forEach { gender ->
                Card(
                    modifier = Modifier
                        .height(80.dp)
                        .width(120.dp)
                        .weight(1f)
                        .padding(8.dp)
                    .border(
                        width = 2.dp,
                color = Color(0x70137fec),
                shape = RoundedCornerShape(12.dp)   // match card shape
                ),



                shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = if (gender == gender) Color.White else Color(0xFF137fec)
                    )
                ){
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally

                    ) {
                        Text(
                            text = gender,
                            textAlign = TextAlign.Center,
                            fontSize = 14.sp,
                            style = MaterialTheme.typography.bodyLarge,
                            color = if (gender == gender) Color.Black else Color.White
                        )
                    }
                }
            }
        }



        Spacer(modifier = Modifier.height(24.dp))



        OutlinedTextField(
            value = phoneNumber,
            onValueChange = { phoneNumber=it},
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .background(Color.White, shape = RoundedCornerShape(20.dp)), // background + border radius
            placeholder = {
                Text(
                    text = "+91 9898xxxxx",
                    color = Color.Gray  // placeholder text color
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Call, // person icon
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
            onClick ={
                if(isPreview) return@Button

                val uid=auth?.currentUser?.uid
                if(uid==null){
                    Toast.makeText(context,"user not logged in",Toast.LENGTH_SHORT).show()
                    return@Button
                }

                var doctorAge=age.toInt()
                var doctorProfile= doctorDetail(
                    uid = uid,
                    age = doctorAge,
                    phoneNumber = phoneNumber,
                    gender = gender
                )

                FirebaseFirestore.getInstance()
                    .collection("doctors")
                    .document(uid)
                    .set(doctorProfile)
                    .addOnSuccessListener{
                        Toast.makeText(context,"Saved Successfully",Toast.LENGTH_SHORT).show()

                    }
                    .addOnFailureListener{e->
                        Toast.makeText(context,"failed",Toast.LENGTH_SHORT).show()


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
            Text("Continue", fontSize = 16.sp)
        }

    }
}

@Preview
@Composable
fun docotorDetailScreen(){

    // 2️⃣ Fake user data
    val previewUser = doctorDetail(
        age = 22,
        phoneNumber = "9898XXXXXX",
        gender ="male"

        )
    DoctorScreen(
        navController = rememberNavController(),
        isPreview = true,
        doctorDetail =previewUser
    )
}