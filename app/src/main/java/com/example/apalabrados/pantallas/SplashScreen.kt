package com.example.apalabrados.pantallas

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.apalabrados.navegacion.PantallasApp
import com.example.apalabrados.viewModel.ViewModel
import kotlinx.coroutines.delay
import java.lang.reflect.Modifier

@Composable
fun SplashScreen(navController: NavController, ViewModel: ViewModel){
    LaunchedEffect(key1 = true){
        delay(5000)
        navController.popBackStack()
        navController.navigate(PantallasApp.Inicio.route)
    }

    Splash()
}

@Composable
fun Splash(){
    Column(
        modifier = androidx.compose.ui.Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        //Image(painter = , contentDescription = )
        Text(text = "Bienvenidos a la app preguntaos")
    }
}