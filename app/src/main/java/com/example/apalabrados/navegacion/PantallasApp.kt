package com.example.apalabrados.navegacion

sealed class PantallasApp (val route: String) {
    object SplashScreen: PantallasApp(route = "SplashScreen")
    object Inicio: PantallasApp(route = "Inicio")
    object Login: PantallasApp(route = "LoginScreen")
    object AniadirPreguntas: PantallasApp(route = "AniadirPreguntas")
    object LoginScreen: PantallasApp(route= "LoginScreen")

}