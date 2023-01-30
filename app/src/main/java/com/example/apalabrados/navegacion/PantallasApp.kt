package com.example.apalabrados.navegacion

sealed class PantallasApp (val route: String) {
    object Inicio: PantallasApp(route = "Inicio")
    object SplashScreen: PantallasApp(route = "SplashScreen")

}