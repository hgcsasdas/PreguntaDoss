package com.example.apalabrados.pantallas

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.apalabrados.conexion.buscarJugadorLibre

var jugador = ""


@Composable
fun JugarP1(){
    Text(text = jugador)
    buscarJugadorLibre("Juanito")
}