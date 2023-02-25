package com.example.apalabrados.pantallas

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.apalabrados.conexion.aniadirPartida
import com.example.apalabrados.helpers.BottomBar
import com.example.apalabrados.mvvm.ViewModel
import com.example.apalabrados.session.Session
import com.example.apalabrados.ui.theme.AzulFondo

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CrearPartida(navController: NavController, ViewModel: ViewModel){
// Crearemos una instanacia de partida a lgenerar un nombre de sala que nosotros queramos
// y tendremos la posibilidad de generar un código aleatorio con un botón
    val sessionManager = Session(LocalContext.current)
    val scaffoldState = rememberScaffoldState()

    Scaffold (
        scaffoldState = scaffoldState,

        bottomBar = { BottomBar(navController, ViewModel) }
    ) {
        //content area
        val codigoSala by ViewModel.codigoSala.observeAsState(initial = "")

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(AzulFondo),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = codigoSala,
                onValueChange = {
                    ViewModel.rellenarCodigoSala(
                        campo = it,
                    )
                },
                label = { Text("Inventa un código de sala") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
            )
            aniadirPartida(sessionManager.getNick().toString(), ViewModel)
        }
    }
}
