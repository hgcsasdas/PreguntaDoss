package com.example.apalabrados.navegacion

sealed class PantallasApp (val route: String) {
    object SplashScreen: PantallasApp(route = "SplashScreen")
    object Inicio: PantallasApp(route = "Inicio")
    object AniadirPreguntas: PantallasApp(route = "AniadirPreguntas")
    object LoginScreen: PantallasApp(route= "LoginScreen")
    object PerfilScreen: PantallasApp(route= "PerfilScreen")
    object SeleccionNumJugador: PantallasApp(route= "SeleccionNumJugador")
    object JugarP1: PantallasApp(route= "JugarP1")
    object JugarP2: PantallasApp(route= "JugarP2")

    object SeleccionMiniJuego: PantallasApp(route= "SeleccionMiniJuego")

}