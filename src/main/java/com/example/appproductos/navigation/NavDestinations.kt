package com.example.appproductos.navigation

import kotlinx.serialization.Serializable

@Serializable
object Home

@Serializable
object ListaProductos

@Serializable
object FormularioProductos

@Serializable
data class EditarProducto(val productId: Int)

@Serializable
object Presentacion