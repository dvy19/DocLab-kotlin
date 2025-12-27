package com.example.doclab.user

import android.graphics.drawable.Icon
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
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
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
import com.example.doclab.doctor.ServiceDetails
import com.example.doclab.user.userDetail
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import androidx.compose.foundation.lazy.items




@Composable
fun HomeScreen(uid:String){

    val services = remember { mutableStateListOf<ServiceDetails>() }

    LaunchedEffect(Unit) {
        FirebaseFirestore.getInstance()
            .collection("doctors")
            .document(uid)
            .collection("services")
            .get()
            .addOnSuccessListener { result ->
                services.clear()
                for (doc in result) {
                    services.add(
                        doc.toObject(ServiceDetails::class.java)
                    )
                }
            }
    }



    Image(
        painter = painterResource(id= R.drawable.appback),
        contentDescription = null,
        modifier=Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )


    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Image(
                    painter = painterResource(id = R.drawable.docotr_pro),
                    contentDescription = null,
                    modifier = Modifier
                        .size(64.dp)
                        .weight(1f)
                )

                Column(modifier = Modifier.weight(2f)) {
                    Text(text = "Welcome Back", fontSize = 16.sp)
                    Text(
                        text = "Henry Cavil",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Image(
                    painter = painterResource(id = R.drawable.chat),
                    contentDescription = null,
                    modifier = Modifier
                        .size(56.dp)
                        .weight(1f)
                )
            }
        }

        // 🔹 SERVICES LIST
        items(services) { service ->
            DoctorServiceItem(service = service)
        }
    }




}


@Preview
@Composable
fun HomePreview(){
    HomeScreen(uid="")
}
