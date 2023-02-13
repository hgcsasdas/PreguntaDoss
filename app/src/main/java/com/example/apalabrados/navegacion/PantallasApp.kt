package com.example.apalabrados.navegacion

sealed class PantallasApp (val route: String) {
    object SplashScreen: PantallasApp(route = "SplashScreen")
    object Inicio: PantallasApp(route = "Inicio")
    object AniadirPreguntas: PantallasApp(route = "AniadirPreguntas")
    object LoginScreen: PantallasApp(route= "LoginScreen")
    object PerfilScreen: PantallasApp(route= "PerfilScreen")


}