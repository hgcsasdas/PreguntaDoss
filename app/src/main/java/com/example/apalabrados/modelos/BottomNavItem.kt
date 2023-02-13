package com.example.apalabrados.modelos

import com.example.apalabrados.R
import com.example.apalabrados.navegacion.PantallasApp

data class BottomNavItem(
    val name: String,
    val route: PantallasApp,
    val icon: Int,
)

var bottomNavItems = listOf(
    BottomNavItem(
        name = "Home",
        route = PantallasApp.Inicio,
        icon = R.drawable.inicio
    ),
    BottomNavItem(
        name = "Añadir preguntas",
        route = PantallasApp.AniadirPreguntas,
        icon =  R.drawable.mas,
    ),
    BottomNavItem(
        name = "Settings",
        route = PantallasApp.PerfilScreen,
        icon =  R.drawable.usuario,
    ),
)