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
fun DoctorDetailScreen(navController: NavController,
                isPreview:Boolean=false,
                doctorDetail: doctorDetail= doctorDetail()
){

    var age by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    var duration by remember { mutableStateOf("") }
    var about  by remember { mutableStateOf("") }
    var fees by remember { mutableStateOf("") }
    var mode by remember { mutableStateOf("") }


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
                    text = "Complete Profile",
                    color = Color.Blue,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,

                    )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Image(
                painter = painterResource(id = R.drawable.docotr_pro),
                contentDescription = null,
                modifier = Modifier.size(150.dp)
                    .clip(CircleShape)
                    .border(2.dp, Color.Blue, CircleShape)
                    .padding(8.dp),
                alignment = Alignment.Center,
                contentScale = ContentScale.Crop,
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Add Profile Photo",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = "Let us identify you",
                fontSize = 16.sp,
            )

            Spacer(modifier = Modifier.height(24.dp))
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Enter Age",
                modifier=Modifier.align(Alignment.Start),
                fontSize = 20.sp,
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = age,
                onValueChange = { age = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .background(
                        Color.White,
                        shape = RoundedCornerShape(16.dp)
                    ), // background + border radius
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

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Enter Phone Number",
                modifier=Modifier.align(Alignment.Start),
                fontSize = 20.sp,

                )
            Spacer(modifier = Modifier.height(8.dp))
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

            Text(
                text = "Enter a short bio",
                modifier=Modifier.align(Alignment.Start),
                fontSize = 20.sp,

                )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = about,
                onValueChange = { about=it},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(112.dp)
                    .background(Color.White, shape = RoundedCornerShape(20.dp)), // background + border radius
                placeholder = {
                    Text(
                        text = "Your details here",
                        color = Color.Gray  // placeholder text color
                    )
                },

                textStyle = TextStyle(
                    color = Color.Black, // input text color
                    fontSize = 16.sp
                ),
                maxLines = 5

            )

            Spacer(modifier = Modifier.height(16.dp))


            Text(
                text = "Select a consultation Mode",
                modifier=Modifier.align(Alignment.Start),
                fontSize = 20.sp,

                )

            Spacer(modifier = Modifier.height(8.dp))

            val Mode = listOf("Online", "Offline")

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Mode.forEach { mode ->
                    Card(
                        modifier = Modifier
                            .height(64.dp)
                            .weight(1f)
                            .padding(4.dp)
                            .border(
                                width = 1.dp,
                                color = Color(0x70137fec),
                                shape = RoundedCornerShape(12.dp)   // match card shape
                            ),



                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = if (mode==mode) Color.White else Color(0xFF137fec)
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
                                text = mode,
                                textAlign = TextAlign.Center,
                                fontSize = 18.sp,
                                style = MaterialTheme.typography.bodyLarge,
                                color = if (mode==mode) Color.Black else Color.White
                            )
                        }
                    }
                }
            }

            val gender = listOf("Male", "Female")
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Mode.forEach { gender ->
                    Card(
                        modifier = Modifier
                            .height(64.dp)
                            .weight(1f)
                            .padding(4.dp)
                            .border(
                                width = 1.dp,
                                color = Color(0x70137fec),
                                shape = RoundedCornerShape(12.dp)   // match card shape
                            ),



                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = if (gender==gender) Color.White else Color(0xFF137fec)
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
                                text = mode,
                                textAlign = TextAlign.Center,
                                fontSize = 18.sp,
                                style = MaterialTheme.typography.bodyLarge,
                                color = if (gender==gender) Color.Black else Color.White
                            )
                        }
                    }
                }
            }



            Button(

                onClick ={
                    if(isPreview) return@Button

                    val uid=auth?.currentUser?.uid
                    if(uid==null){
                        Toast.makeText(context,"user not logged in",Toast.LENGTH_SHORT).show()
                        return@Button
                    }

                    var d_age=age.toInt()

                    var doctor_detail= doctorDetail(
                        mode = mode,
                        age = d_age,
                        phoneNumber = phoneNumber,
                        about = about,
                    )

                    var full_details= doctorFullDetails(
                        doctorDetail=doctor_detail,
                        doctorAddress = doctorAddress(),      // empty for now
                        doctorEducation = doctorEducation()
                    )

                    FirebaseFirestore.getInstance()
                        .collection("doctors")
                        .document(uid)
                        .collection("profile")
                        .document("full_details")
                        .set(full_details)
                        .addOnSuccessListener{
                            Toast.makeText(context,"Saved Successfully",Toast.LENGTH_SHORT).show()

                            navController.navigate("doctorDetailEdu") {
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

                Text("Add Service", fontSize = 16.sp)
            }
        }
    }










    }




@Preview
@Composable
fun doctorDetailScreen(){

    // 2️⃣ Fake user data
    val previewDoctor= doctorDetail(
        age = 22,
        phoneNumber = "9898XXXXXX",
        mode = "online",
        fees = "232",
        duration = "1hrs",
        about = "hello its me",
        gender = "male"

        )
    DoctorDetailScreen(
        navController = rememberNavController(),
        isPreview = true,
        doctorDetail=previewDoctor
    )
}



