package com.example.apalabrados.navegacion

sealed class PantallasJugar(val route: String) {
    object RuleScreen : PantallasApp(route = "Ruleta")
}
