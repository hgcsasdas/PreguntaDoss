package com.example.apalabrados.pantallas

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.apalabrados.navegacion.PantallasApp

@Composable
fun SeleccionNumJugador(navController: NavController, viewModel: ViewModel){

    Column() {
        Button(onClick = {
            jugador = "pepe"
            navController.navigate(PantallasApp.JugarP1.route)
        }) {
            Text(text = "J1")
        }
        Button(onClick = {
            jugador = "Luis"
            navController.navigate(PantallasApp.JugarP2.route)
        }) {
            Text(text = "J2")
        }
    }

}