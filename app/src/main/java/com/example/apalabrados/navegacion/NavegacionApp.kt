package com.example.apalabrados.navegacion

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.apalabrados.jugar.pantallasJugar.*
import com.example.apalabrados.login.ui.LoginScreen
import com.example.apalabrados.login.ui.registro.RegistroScreen
import com.example.apalabrados.login.ui.loginP.LoginViewModel
import com.example.apalabrados.login.ui.registro.RegistroViewModel
import com.example.apalabrados.pantallas.*
import com.example.apalabrados.mvvm.ViewModel

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
                LoginViewModel(),
                navController
            )
        }
        composable(route= PantallasApp.RegistroScreen.route){
            RegistroScreen(
                RegistroViewModel(),
                navController
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
                navController,
                ViewModel
            )
        }
        composable(route= PantallasApp.Seleccion.route){
            Seleccion(
                navController,
                ViewModel
            )
        }
        composable(route= PantallasApp.SeleccionMiniJuego.route){
            SeleccionMiniJuego(
                navController,
                ViewModel
            )
        }
        /*
        EJEMPLO DE COMO PASAR DATOS ENTRE PANTALLAS
        composable(route= PantallasApp.CrearPartida.route + "/{codigoSala}",
            arguments = listOf(navArgument(name = "codigoSala"){
                type = NavType.StringType
            })){
            CrearPartida(
                navController, it.arguments?.getString("codigoSala")
            )
        }*/
        composable(route= PantallasApp.CrearPartida.route){
            CrearPartida(
                navController,
                ViewModel
            )
        }
        composable(route= PantallasApp.UnirsePartida.route){
            UnirsePartida(
                navController,
                ViewModel
            )
        }
        composable(route= PantallasApp.MisPartidas.route){
            MisPartidas(
                navController,
                ViewModel
            )
        }
        composable(route= PantallasJugar.RuleScreen.route + "/{codigoSala}",
            arguments = listOf(navArgument(name = "codigoSala"){
                type = NavType.StringType
            })){
            Roulette(
                navController,
                ViewModel,
                it.arguments?.getString("codigoSala")
            )
        }
        composable(route= PantallasJugar.SalaDeEspera.route+ "/{codigoSala}",
            arguments = listOf(navArgument(name = "codigoSala"){
                type = NavType.StringType
            })){
            SalaDeEspera(
                navController,
                ViewModel,
                it.arguments?.getString("codigoSala")
            )
        }
        composable(route= PantallasJugar.JugarScreen.route+ "/{codigoSala}"+ "/{tema}",arguments = listOf(
            navArgument(name = "codigoSala") {
                type = NavType.StringType
            },
            navArgument(name = "tema") {
                type = NavType.StringType
            }
        )) { backStackEntry ->
            JugarScreen(
                navController,
                ViewModel,
                backStackEntry.arguments?.getString("codigoSala"),
                backStackEntry.arguments?.getString("tema")
            )

        }
        composable(route= PantallasJugar.GanadorScreen.route+ "/{nombreGanador}",
            arguments = listOf(navArgument(name = "nombreGanador"){
                type = NavType.StringType
            })){
                GanadorScreen(
                navController,
                ViewModel,
                it.arguments?.getString("nombreGanador")
            )
        }
    }
}