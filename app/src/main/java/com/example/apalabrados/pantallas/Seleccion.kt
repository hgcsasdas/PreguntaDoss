package com.example.apalabrados.pantallas

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.apalabrados.conexion.aniadirPartida
import com.example.apalabrados.conexion.getall
import com.example.apalabrados.helpers.generarCodigo
import com.example.apalabrados.model.Usuario
import com.example.apalabrados.navegacion.PantallasApp

@Composable
fun Seleccion(navController: NavController, viewModel: ViewModel){
    val pepe = Usuario("Juan", 5)

    Column() {
        Button(onClick = {
            val codigoSala = generarCodigo()
            aniadirPartida(jugador1 = pepe.nombre, codigoSala)
            navController.navigate(PantallasApp.CrearPartida.route + "/$codigoSala")
            //getall()

        }) {
            Text(text = "Crear partida")
        }
        Button(onClick = {
            jugador = "Luis"
            navController.navigate(PantallasApp.UnirsePartida.route)
        }) {
            Text(text = "Unirse a partida")
        }
        Button(onClick = {
            jugador = "Luis"
            navController.navigate(PantallasApp.MisPartidas.route)
        }) {
            Text(text = "Mis partidas")
        }
    }

}