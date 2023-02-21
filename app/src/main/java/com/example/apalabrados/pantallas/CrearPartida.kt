package com.example.apalabrados.pantallas

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.apalabrados.conexion.aniadirPartida
import com.example.apalabrados.mvvm.ViewModel



@Composable
fun CrearPartida(navController: NavController, ViewModel: ViewModel){
// Crearemos una instanacia de partida a lgenerar un nombre de sala que nosotros queramos
// y tendremos la posibilidad de generar un código aleatorio con un botón

    val codigoSala by ViewModel.codigoSala.observeAsState(initial = "")

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = codigoSala,
            onValueChange = {
                ViewModel.rellenarCodigoSala(
                    campo = it,
                )
            },
            label = { Text("Pregunta") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
        )
        aniadirPartida("pepe", ViewModel)
    }
}
