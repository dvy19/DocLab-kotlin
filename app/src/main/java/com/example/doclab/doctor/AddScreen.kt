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
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import com.example.doclab.user.userDetail
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun AddScreen(
    navController: NavController,
    isPreview:Boolean=false,
    serviceDetails: ServiceDetails

){

    var serviceDescription by remember{ mutableStateOf("") }
    var serviceName by remember{ mutableStateOf("") }
    var serviceMode by remember{ mutableStateOf("") }
    var serviceDuration by remember{ mutableStateOf("") }
    var servicePrice by remember{ mutableStateOf("") }

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

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = painterResource(id = R.drawable.arrow),
                contentDescription = null,
                modifier = Modifier.size(32.dp)
                    .padding(8.dp)
                    .weight(1f)

            )

            Text(
                text = "Add Service",
                color = Color.Blue,
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(5f)
                )

            Text(
                text = "Cancel",
                color = Color.Black,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(1f)
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
            text = "Dr. Sambit Patra",
            fontSize =20.sp,
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = "Cardiologist | MBBS, MD",
            fontSize =16.sp,
        )


        Spacer(modifier = Modifier.height(24.dp))
        Spacer(modifier = Modifier.height(8.dp))

        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = serviceName,
            onValueChange = { serviceName=it},
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .background(Color.White, shape = RoundedCornerShape(16.dp)), // background + border radius
            placeholder = {
                Text(
                    text = "e.g. Cardiac Checkup",
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



        OutlinedTextField(
            value = serviceDescription,
            onValueChange = { serviceDescription=it},
            modifier = Modifier
                .fillMaxWidth()
                .height(112.dp)
                .background(Color.White, shape = RoundedCornerShape(16.dp)), // background + border radius
            placeholder = {
                Text(
                    text = "Briefly describe what this service covers",
                    color = Color.Gray  // placeholder text color
                )
            },
            textStyle = TextStyle(
                color = Color.Black, // input text color
                fontSize = 16.sp
            ),

            maxLines = 5

            )

        val Mode = listOf("Online", "Offline")

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
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


        Row (modifier=Modifier.fillMaxWidth()
            .padding(10.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)){


            OutlinedTextField(
                value = serviceDuration,
                onValueChange = { serviceDuration=it},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .weight(1f)
                    .background(Color.White, shape = RoundedCornerShape(16.dp)), // background + border radius
                placeholder = {
                    Text(
                        text = "1 hrs.",
                        color = Color.Gray  // placeholder text color
                    )
                },
                textStyle = TextStyle(
                    color = Color.Black, // input text color
                    fontSize = 16.sp
                ),



            )

            OutlinedTextField(
                value = servicePrice,
                onValueChange = { servicePrice=it},
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .height(56.dp)
                    .background(Color.White, shape = RoundedCornerShape(16.dp)), // background + border radius
                placeholder = {
                    Text(
                        text = "$500",
                        color = Color.Gray  // placeholder text color
                    )
                },
                textStyle = TextStyle(
                    color = Color.Black, // input text color
                    fontSize = 16.sp
                ),

                maxLines = 1

            )

            Spacer(modifier = Modifier.height(24.dp))



        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick ={
                if(isPreview) return@Button

                val uid=auth?.currentUser?.uid
                if(uid==null){
                    Toast.makeText(context,"user not logged in",Toast.LENGTH_SHORT).show()
                    return@Button
                }

                var service= ServiceDetails(
                    serviceName = serviceName,
                    serviceMode = serviceMode,
                    serviceDes = serviceDescription,
                    servicePrice = servicePrice,
                    serviceDuration = serviceDuration,
                    doctorSpecial = "MBBS",
                    doctorName = "Sambit patra"
                )

                FirebaseFirestore.getInstance()
                    .collection("doctors")
                    .document(uid)
                    .set(service)
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
            Text("Add Service", fontSize = 16.sp)
        }
    }






}


@Preview
@Composable
fun previewScreen(){

    val previewService= ServiceDetails(
        serviceDes = "idvidb",
        servicePrice = "232",
        serviceDuration = "1hrs",
        serviceMode = "Online",
        serviceName = "heart checkup",
        doctorName = "sambit patra",
        doctorSpecial = "cardio"

    )
    AddScreen(navController = rememberNavController(),
    isPreview=true,
        serviceDetails = previewService

    )



}
