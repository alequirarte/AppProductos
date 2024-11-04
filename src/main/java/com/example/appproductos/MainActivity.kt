package com.example.appproductos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.room.Room
import com.example.appproductos.navigation.NavManager
import com.example.appproductos.room.ProductDatabase
import com.example.appproductos.ui.theme.AppProductosTheme
import com.example.appproductos.viewmodels.ProductoViewModel
import com.example.appproductos.views.HomeView

class MainActivity : ComponentActivity() {
    private val db by lazy {
        Room.databaseBuilder(
            context = applicationContext,
            klass = ProductDatabase::class.java,
            name = "products.db"
        )
    }

    // Método que es llamado al crear la activity.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        // Instancia del viewModel.
        val viewModel: ProductoViewModel = ProductoViewModel(db.build().productsDao())

        // Establecer el contenido de la aplicación.
        setContent {
            AppProductosTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavManager(viewModel = viewModel, modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}


