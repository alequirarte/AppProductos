package com.example.appproductos.dialogs

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable


@Composable
fun AlertaEliminar(
    onConfirmDelete: () -> Unit,
    onCancelDelete: () -> Unit,
    showDialog: Boolean
) {
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { /* No hacer nada al tocar fuera del diálogo */ },
            title = {
                Text(text = "Eliminar Producto")
            },
            text = {
                Text("¿Estás seguro de que deseas eliminar este producto")
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        onConfirmDelete()  // Acción cuando se confirma la eliminación
                    }
                ) {
                    Text("Eliminar")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        onCancelDelete()  // Acción cuando se cancela la eliminación
                    }
                ) {
                    Text("Cancelar")
                }
            }
        )
    }
}


@Composable
fun AlertaExito(
    onDismissRequest: () -> Unit,
    dialogText: String,
    show: Boolean
) {
    if (show) {
        AlertDialog(
            onDismissRequest = {
                // No hacer nada al tocar fuera del diálogo, se cierra solo con "OK"
            },
            title = {
                Text(text = "Producto Agregado")
            },
            text = {
                Text(text = dialogText)
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        onDismissRequest()  // Cierra el diálogo solo al presionar "OK"
                    }
                ) {
                    Text("OK")
                }
            }
        )
    }
}
@Composable
fun AlertaEdit(
    onDismissRequest: () -> Unit,
    dialogText: String,
    show: Boolean
) {
    if (show) {
        AlertDialog(
            onDismissRequest = {
                // No hacer nada al tocar fuera del diálogo, se cierra solo con "OK"
            },
            title = {
                Text(text = "Producto Editado")
            },
            text = {
                Text(text = dialogText)
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        onDismissRequest()  // Cierra el diálogo solo al presionar "OK"
                    }
                ) {
                    Text("OK")
                }
            }
        )
    }
}