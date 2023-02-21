package com.example.apalabrados.pantallas

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.apalabrados.conexion.buscarJugadorLibre
import com.example.apalabrados.conexion.obtenerNombreJ2
import com.example.apalabrados.model.Usuario

var jugador = ""


@Composable
fun CrearPartida(navController: NavController, codigoSala: String?){
    Column {
        Text(text = jugador)
        var count by remember { mutableStateOf("asd") }

        buscarJugadorLibre("Juanito")
        codigoSala?.let {

            Spacer(modifier = Modifier.padding(20.dp))


            Button(onClick = {
                count = obtenerNombreJ2(it, "j1")
            }) {
                Text(text = "asdasd")
            }
        }
        Spacer(modifier = Modifier.padding(20.dp))
        Text(text = count)
        codigoSala?.let {
            Text(text = it)
        }
    }
}

@Composable
fun ContenidoCrearPartida(user: Usuario){
    Column() {
        Text(text = "Jugador 1= " + user.nombre)
    }
}