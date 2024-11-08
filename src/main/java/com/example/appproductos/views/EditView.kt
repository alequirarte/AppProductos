package com.example.appproductos.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.appproductos.R
import com.example.appproductos.dialogs.AlertaEdit
import com.example.appproductos.model.Producto
import com.example.appproductos.navigation.ListaProductos
import com.example.appproductos.viewmodels.ProductoViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun EditView(productId: Int, navController: NavController, viewModel: ProductoViewModel) {
    val snackbarHostState = remember { SnackbarHostState() }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { innerPadding ->
        BodyEdit(producto = viewModel.getProductById(productId), navController, viewModel, snackbarHostState)
    }
}


@Composable
fun BodyEdit(producto: Producto?, navController: NavController, viewModel: ProductoViewModel, snackbarHostState: SnackbarHostState) {
    val coroutineScope = rememberCoroutineScope()
    var name by remember { mutableStateOf(producto?.nombre ?: "") }
    var price by remember { mutableStateOf(producto?.precio.toString() ?: "") }
    var description by remember { mutableStateOf(producto?.descripcion ?: "") }
    var date by remember { mutableStateOf(producto?.fecha ?: "") }
    var showSuccessDialog by remember { mutableStateOf(false) }
    var successMsg by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1A4D2E)) // Fondo principal
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top // Espaciado entre elementos
    ) {

        IconButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier

                .align(Alignment.Start) // Alinear a la izquierda
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "Back",
                tint = Color.White // Cambiar el color de la flecha
            )
        }

        Image(
            painter = painterResource(id = R.drawable.logo), // Reemplaza con el nombre de tu logo
            contentDescription = "Logo",
            modifier = Modifier
                .size(200.dp) // Ajusta el tamaño del logo
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(Color(0xFFF5EFE6), shape = RoundedCornerShape(8.dp))
                .padding(16.dp) // Padding interno después del fondo
        ) {

            // Campos del formulario
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Nombre") },
                modifier = Modifier.fillMaxWidth(),
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = description,
                onValueChange = { description = it},
                label = { Text("Descripción") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = price,
                onValueChange = { price = it },
                label = { Text("Precio") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = date,
                onValueChange = { date = it },
                label = { Text("Fecha de Registro") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Botones
            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                Button(
                    onClick = {
                        viewModel.updateProduct(Producto(id = producto?.id!!, nombre = name, descripcion = description, precio = price.toInt(), fecha = date))
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar("Producto editado exitosamente")
                            navController.navigate(ListaProductos)

                        }
                              },
                    modifier = Modifier.weight(1f),
                    colors = ButtonColors(
                        containerColor = Color(0xFF2A442D),
                        contentColor = Color.White,
                        disabledContentColor = Color.LightGray,
                        disabledContainerColor = Color.LightGray
                    )
                ) {
                    Text(text = "Actualizar")
                }

                Spacer(modifier = Modifier.width(16.dp))

                Button(
                    onClick = { navController.navigate(ListaProductos) },
                    modifier = Modifier.weight(1f),
                    colors = ButtonColors(
                        containerColor = Color(0xFF2A442D),
                        contentColor = Color.White,
                        disabledContentColor = Color.LightGray,
                        disabledContainerColor = Color.LightGray
                    )
                ) {
                    Text(text = "Cancelar")
                }
            }
        }
    }
    AlertaEdit(
        onDismissRequest = {
            showSuccessDialog = false
            navController.navigate(ListaProductos)  // Redirigir a la lista de productos después de cerrar el diálogo
        },
        dialogText = successMsg,
        show = showSuccessDialog
    )
}



