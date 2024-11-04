package com.example.appproductos.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.appproductos.R
import com.example.appproductos.navigation.ListaProductos
import com.example.appproductos.navigation.Presentacion
import kotlin.io.encoding.Base64

@Composable
fun HomeView(navController: NavController) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        BodyHome(navController)
    }
}

@Composable
fun BodyHome(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1A4D2E)) // Fondo principal
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top // Espaciado entre elementos
    ) {

        Image(
            painter = painterResource(id = R.drawable.logo), // Reemplaza con el nombre de tu logo
            contentDescription = "Logo",
            modifier = Modifier
                .size(330.dp) // Ajusta el tamaño del logo

        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp)
                .background(Color(0xFFE8DFCA), shape = RoundedCornerShape(50.dp))
                .padding(14.dp) // Padding interno después del fondo
                .height(180.dp),
            verticalArrangement = Arrangement.Center
        ) {


            Button(
                onClick = {
                    navController.navigate(ListaProductos)
                },
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(60.dp)
                    .align(Alignment.CenterHorizontally),
                colors = ButtonColors(
                    containerColor = Color(0xFF2A442D),
                    contentColor = Color.White,
                    disabledContentColor = Color.LightGray,
                    disabledContainerColor = Color.LightGray
                )
            ) {
                Text(
                    text = "Productos",
                    fontSize = 20.sp // Tamaño más grande del texto
                )
            }

            Spacer(modifier = Modifier.height(25.dp))

            Button(
                onClick = {
                    navController.navigate(Presentacion)
                },
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(60.dp)
                    .align(Alignment.CenterHorizontally),
                colors = ButtonColors(
                    containerColor = Color(0xFF2A442D),
                    contentColor = Color.White,
                    disabledContentColor = Color.LightGray,
                    disabledContainerColor = Color.LightGray
                )
            ) {
                Text(
                    text = "Presentación",
                    fontSize = 20.sp // Tamaño más grande del texto
                )
            }




        }

        Spacer(modifier = Modifier.height(80.dp))
        Text(
            text = "Quirarte Agüero Alejandra",
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = Color(0xFFFFFFFF)
        )
    }
}

