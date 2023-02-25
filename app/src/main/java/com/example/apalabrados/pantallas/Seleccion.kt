package com.example.apalabrados.pantallas

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.apalabrados.R
import com.example.apalabrados.helpers.BottomBar
import com.example.apalabrados.navegacion.PantallasApp
import com.example.apalabrados.mvvm.ViewModel
import com.example.apalabrados.ui.theme.AzulClarito
import com.example.apalabrados.ui.theme.AzulFondo

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Seleccion(navController: NavController, ViewModel: ViewModel){
    val scaffoldState = rememberScaffoldState()

    Scaffold (
        scaffoldState = scaffoldState,

        bottomBar = { BottomBar(navController, ViewModel) }
    ) {
        //content area
        buttonsSeleccion(navController)
    }


}

@Composable
fun buttonsSeleccion(navController: NavController){

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AzulFondo),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Image(painter = painterResource(id = R.drawable.preguntadoss) , contentDescription = null, modifier = Modifier.size(300.dp))

        Spacer(modifier = Modifier.padding(20.dp))

        Button(onClick = {
            navController.navigate(PantallasApp.CrearPartida.route)
            //getall()
        },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = AzulClarito,
                contentColor = AzulFondo
            ),
            modifier = Modifier.size(160.dp, 60.dp)) {
            Text(text = "Crear partida")
        }
        Spacer(modifier = Modifier.padding(20.dp))

        Button(onClick = {
            navController.navigate(PantallasApp.UnirsePartida.route)
        },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = AzulClarito,
                contentColor = AzulFondo
            ),
            modifier = Modifier.size(160.dp, 60.dp)) {
            Text(text = "Unirse a partida")
        }
        Spacer(modifier = Modifier.padding(20.dp))

        Button(onClick = {
            navController.navigate(PantallasApp.MisPartidas.route)
        },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = AzulClarito,
                contentColor = AzulFondo
            ),
            modifier = Modifier.size(160.dp, 60.dp)) {
            Text(text = "Mis partidas")
        }
    }
}