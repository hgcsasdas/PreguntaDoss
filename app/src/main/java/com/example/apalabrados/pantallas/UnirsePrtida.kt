package com.example.apalabrados.pantallas

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.apalabrados.conexion.buscarJugadorLibre



@Composable
fun JugarP2(){
    Text(text = jugador)
    buscarJugadorLibre("Juanito")
}