package com.example.appproductos.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.appproductos.model.Producto


@Database(
    entities = [Producto::class],
    version = 1,
    exportSchema = false)
abstract class ProductDatabase : RoomDatabase() {
    abstract fun productsDao(): ProductsDatabaseDao
}