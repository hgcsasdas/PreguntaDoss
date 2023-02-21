package com.example.apalabrados.pantallas

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.apalabrados.R
import com.example.apalabrados.navegacion.PantallasApp
import com.example.apalabrados.ui.theme.AzulFondo
import com.example.apalabrados.mvvm.ViewModel


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Inicio(navController: NavController, ViewModel: ViewModel){
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold (
        scaffoldState = scaffoldState,

        bottomBar = {BottomBar(navController, ViewModel)}
    ) {
        //content area
        InicioContent(navController)
    }
}

@Composable
fun InicioContent(navController: NavController){
    Box(modifier = Modifier
        .background(Color(0xff546e7a))
        .fillMaxSize()){


        Column(
            Modifier
                .fillMaxSize()
                .background(AzulFondo),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            // IMAGEN LOGO

            Image(painter = painterResource(id = R.drawable.preguntadoss) , contentDescription = null, modifier = Modifier.size(300.dp))

            Spacer(modifier = Modifier.padding(20.dp))

            //JUGAR MINIJUEGOS
            Button(onClick = {
                navController.navigate(PantallasApp.SeleccionMiniJuego.route)
            },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xFF00C4F2),
                    contentColor = AzulFondo
                ),
                modifier = Modifier.size(160.dp, 60.dp)

            ) {
                Text(text = "Mini Juegos")
            }

            Spacer(modifier = Modifier.padding(20.dp))

            //JUGAR BUTTON

            Button(onClick = {
                navController.navigate(PantallasApp.Seleccion.route)
            },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xFF00C4F2),
                    contentColor = AzulFondo
                ),
                modifier = Modifier.size(160.dp, 60.dp)
            ) {
                Text(text = "Jugar")
            }
        }
    }
}
