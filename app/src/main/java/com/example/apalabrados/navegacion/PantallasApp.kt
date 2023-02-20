package com.example.apalabrados.navegacion

sealed class PantallasApp (val route: String) {
    object SplashScreen: PantallasApp(route = "SplashScreen")
    object Inicio: PantallasApp(route = "Inicio")
    object AniadirPreguntas: PantallasApp(route = "AniadirPreguntas")
    object LoginScreen: PantallasApp(route= "LoginScreen")
    object PerfilScreen: PantallasApp(route= "PerfilScreen")
    object Seleccion: PantallasApp(route= "Seleccion")
    object CrearPartida: PantallasApp(route= "CrearPartida")
    object UnirsePartida: PantallasApp(route= "UnirsePartida")

    object SeleccionMiniJuego: PantallasApp(route= "SeleccionMiniJuego")
    object MisPartidas: PantallasApp(route= "MisPartidas")

}