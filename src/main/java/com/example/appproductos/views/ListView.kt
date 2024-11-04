import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.appproductos.R
import com.example.appproductos.dialogs.AlertaEliminar
import com.example.appproductos.model.Producto
import com.example.appproductos.navigation.EditarProducto
import com.example.appproductos.navigation.FormularioProductos
import com.example.appproductos.viewmodels.ProductoViewModel
import com.example.appproductos.navigation.Home
import com.example.appproductos.navigation.ListaProductos


@Composable
fun ListView(viewModel: ProductoViewModel, navController: NavController) {

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(FormularioProductos) },
                containerColor = Color(0xFFE8DFCA)
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Agregar")
            }
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        BodyList(viewModel, navController)
    }
}

@Composable
fun BodyList(viewModel: ProductoViewModel, navController: NavController) {
    // Usamos LazyColumn para que todo sea scrollable si es necesario
    var productIdToDelete by remember { mutableStateOf(0) }
    var showDeleteDialog by remember { mutableStateOf(false) }
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1A4D2E)) // Fondo principal
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp) // Espaciado entre elementos
    ) {

        item {
            Spacer(modifier = Modifier.height(16.dp))
            Box(modifier = Modifier.fillMaxWidth()) {
                IconButton(
                    onClick = { navController.navigate(Home) },
                    modifier = Modifier.align(Alignment.CenterStart) // Alinear a la izquierda
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White // Cambiar el color de la flecha
                    )
                }
            }
        }



        // Sección de lista de productos (solo si hay productos)
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
                    .padding(bottom = 16.dp),
                contentAlignment = Alignment.Center // Centra el contenido dentro del Box
            ) {
                Text(
                    text = "Lista de Productos",
                    fontSize = 27.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                )


            }
        }

        val estado = viewModel.estado
        // Estado cargando
        if (estado.estaCargando) {
            item {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(color = Color.White)
                }
            }
        }
        // Estado sin productos
        else if (estado.productos.isEmpty()) {
            item {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(
                        text = "No hay productos disponibles.",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
        // Estado con productos
        else {
            items(estado.productos) { producto ->
                ProductoItem(
                    producto = producto,
                    navController,
                    onDeleteClick = {
                        productIdToDelete = producto.id
                        showDeleteDialog = true
                    }
                )
            }
        }
    }

    // Diálogo de confirmación de eliminación
    if (showDeleteDialog) {
        AlertaEliminar(
            onConfirmDelete = {
                viewModel.deleteProduct(Producto(productIdToDelete, "", "", 0, ""))
                showDeleteDialog = false
            },
            onCancelDelete = {
                showDeleteDialog = false // Cerrar el diálogo sin eliminar
            },
            showDialog = showDeleteDialog
        )
    }
}



// Producto individual
@Composable
fun ProductoItem(producto: Producto, navController: NavController, onDeleteClick: () -> Unit) {
    var productIdToDelete by remember { mutableStateOf(0) }
    var showDeleteDialog by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp)) // Esquinas redondeadas
            .background(Color(0xFFE8DFCA)) // Fondo del producto
            .padding(16.dp) // Padding interno para el contenido
    ) {

        Text(
            text = "${producto.nombre} - $${producto.precio} MXN",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = Color(0xFF314A12)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = producto.descripcion,
            fontSize = 16.sp,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(4.dp))
        // Fila de botones para Actualizar y Eliminar
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Button(
                onClick = {
                    navController.navigate(EditarProducto(productId = producto.id))
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2A442D)),
                modifier = Modifier.padding(end = 8.dp)
            ) {
                Icon(Icons.Filled.Edit, contentDescription = "Editar producto", tint = Color.White, modifier = Modifier.size(20.dp))
                Spacer(modifier = Modifier.width(5.dp))
                Text("Editar", color = Color.White)
            }

            Button(
                onClick = onDeleteClick,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2A442D))
            ) {
                Icon(Icons.Filled.Close, contentDescription = "Eliminar producto", tint = Color.White, modifier = Modifier.size(20.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text("Eliminar", color = Color.White)
            }
        }

    }

}