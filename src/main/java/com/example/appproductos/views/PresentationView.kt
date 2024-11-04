package com.example.appproductos.views

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.appproductos.R





@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PresentationView(navController: NavController) {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        BodyPresentation(navController)
    }
}
@Composable
fun BodyPresentation(navController: NavController) {
    Column(
        modifier = Modifier
            .background(Color(0xFFFFFFFF))
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        Spacer(modifier = Modifier.height(20.dp))

        IconButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier

                .align(Alignment.Start) // Alinear a la izquierda
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "Back",
                tint = Color(0xFF1A4D2E) // Color de la flecha
            )
        }


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {



            Image(
                painter = painterResource(id = R.drawable.foto),
                contentDescription = "",
                modifier = Modifier.width(240.dp)
                    .clip(RoundedCornerShape(120.dp))
                    .border(
                        width = 2.dp,
                        color = Color(0xFF4B662C),
                        shape = RoundedCornerShape(120.dp)
                    )
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Alejandra Quirarte Agüero",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp,
                color = Color(0xFF4B662C)
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = "Programadora Web",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color(0xFF576249)
            )

            Spacer(modifier = Modifier.height(50.dp))

            Row(
                modifier = Modifier
                    .background(Color(0xFFDBE7C8), RoundedCornerShape(50.dp))
                    .padding(16.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.telefono),
                    contentDescription = "",
                    modifier = Modifier.width(20.dp)
                )

                Text(
                    text = "  +52 622 103 9867",
                    textAlign = TextAlign.Center,
                    fontSize = 22.sp,
                    color = Color(0xFF4B662C)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier
                    .background(Color(0xFFDBE7C8), RoundedCornerShape(50.dp))
                    .padding(16.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.github),
                    contentDescription = "",
                    modifier = Modifier.width(20.dp)
                )

                Text(
                    text = "  @alequirarte",
                    textAlign = TextAlign.Center,
                    fontSize = 22.sp,
                    color = Color(0xFF4B662C)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier
                    .background(Color(0xFFDBE7C8), RoundedCornerShape(50.dp))
                    .padding(16.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.correo),
                    contentDescription = "",
                    modifier = Modifier.width(20.dp)
                )

                Text(
                    text = "  alequirarteh@gmail.com",
                    textAlign = TextAlign.Center,
                    fontSize = 22.sp,
                    color = Color(0xFF4B662C)
                )
            }

        }
    }
}



