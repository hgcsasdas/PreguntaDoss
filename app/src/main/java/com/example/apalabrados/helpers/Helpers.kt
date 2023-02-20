package com.example.apalabrados.helpers

import com.example.apalabrados.conexion.buscarEnFirebase
import kotlin.random.Random

fun generarCodigoSala(): String {
    var creada = false
    val caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    var codigo = ""
    while (!creada){
        for (i in 0 until 4) {
            val aleatorio = generarNumeroAleatorio()
            codigo += caracteres[aleatorio].toString()
        }

        creada = buscarEnFirebase(codigo)
        print(creada)
    }
    return codigo
    codigo = ""
}

fun generarNumeroAleatorio(): Int {
    val random = Random
    return random.nextInt(27)
}