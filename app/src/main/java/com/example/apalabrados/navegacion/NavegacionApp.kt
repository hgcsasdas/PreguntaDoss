package com.example.apalabrados.navegacion

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.apalabrados.login.ui.LoginScreen
import com.example.apalabrados.login.ui.LoginViewModel
import com.example.apalabrados.pantallas.*
import com.example.apalabrados.viewModel.ViewModel

@Composable
fun NavegacionApp(ViewModel: ViewModel, LoginViewModel: LoginViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = PantallasApp.SplashScreen.route){
        composable(route= PantallasApp.SplashScreen.route){
            SplashScreen(
                navController
            )
        }
        composable(route= PantallasApp.Inicio.route){
            Inicio(
                navController,
                ViewModel
            )
        }
        composable(route= PantallasApp.LoginScreen.route){
            LoginScreen(
                LoginViewModel
            )
        }
        composable(route= PantallasApp.AniadirPreguntas.route){
            AniadirPreguntas(
                navController,
                ViewModel
            )
        }
        composable(route= PantallasApp.PerfilScreen.route){
            Perfil(

            )
        }
    }
}