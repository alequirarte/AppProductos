package com.example.appproductos.navigation


import ListView
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.appproductos.viewmodels.ProductoViewModel
import com.example.appproductos.views.EditView
import com.example.appproductos.views.FormView
import com.example.appproductos.views.HomeView
import com.example.appproductos.views.PresentationView


@Composable
fun NavManager(viewModel: ProductoViewModel, modifier: Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Home
    ) {
        composable<Home> {
            HomeView(navController)
        }
        composable<ListaProductos> {
            ListView(viewModel, navController)
        }
        composable<FormularioProductos> {
            FormView(navController, viewModel)
        }
        composable<EditarProducto> { navBackStackEntry ->
            val args = navBackStackEntry.toRoute<EditarProducto>()
            EditView(args.productId, navController, viewModel)
        }
        composable<Presentacion> {
            PresentationView(navController)
        }
    }
}