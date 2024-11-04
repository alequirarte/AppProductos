package com.example.appproductos.state

import com.example.appproductos.model.Producto

data class ProductoState(
    val productos: List<Producto> = emptyList(),
    val estaCargando: Boolean = true,
)