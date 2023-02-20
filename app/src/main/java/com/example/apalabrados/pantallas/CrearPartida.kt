package com.example.apalabrados.pantallas

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.apalabrados.conexion.buscarJugadorLibre
import com.example.apalabrados.conexion.obtenerNombreJ2
import com.example.apalabrados.model.Usuario

var jugador = ""


@Composable
fun CrearPartida(navController: NavController, codigoSala: String?){
    Text(text = jugador)
    buscarJugadorLibre("Juanito")
    codigoSala?.let{

        Spacer(modifier = Modifier.padding(20.dp))


    Button(onClick = {
        jugador = obtenerNombreJ2(it, "j1")
    }) {
        Text(text = "dsadasdad")
    }
    }
    codigoSala?.let{
        Text(text = it)}
}

@Composable
fun ContenidoCrearPartida(user: Usuario){
    Column() {
        Text(text = "Jugador 1= " + user.nombre)
    }
}