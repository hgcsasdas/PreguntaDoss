package com.example.apalabrados.navegacion

sealed class PantallasJugar(val route: String) {
    object RuleScreen : PantallasApp(route = "Ruleta")
    object SalaDeEspera : PantallasApp(route = "SalaDeEspera")
    object JugarScreen : PantallasApp(route = "JugarScreen")

    object GanadorScreen : PantallasApp(route = "GanadorScreen")

}
