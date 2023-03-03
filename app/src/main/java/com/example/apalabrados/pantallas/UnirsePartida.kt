package com.example.apalabrados.pantallas

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.apalabrados.conexion.*
import com.example.apalabrados.helpers.BottomBar
import com.example.apalabrados.mvvm.ViewModel
import com.example.apalabrados.session.Session
import com.example.apalabrados.ui.theme.AzulFondo
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@Composable
fun UnirsePartida(navController: NavController, Viewmodel: ViewModel){
    val sessionManager = Session(LocalContext.current)
    val scaffoldState = rememberScaffoldState()
    val context = LocalContext.current

    Scaffold (
        scaffoldState = scaffoldState,

        bottomBar = { BottomBar(navController, Viewmodel) }
    ) {
        //content area
        val codigoSalaUnirse by Viewmodel.codigoSalaUnirse.observeAsState(initial = "")

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(AzulFondo),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = codigoSalaUnirse,
                onValueChange = {
                    Viewmodel.rellenarCodigoSalaUnirse(
                        campo = it,
                    )
                },
                label = { Text("Introduce el código de sala") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
            )
            val coroutineScope = rememberCoroutineScope()

            
            Button(onClick = {

                    coroutineScope.launch {
                        val existe = buscarPartidaPorCodigo(codigoSalaUnirse)
                        if (existe){
                            buscarPartidaPorCodigoUnirse(sessionManager.getNick().toString(), Viewmodel, navController, codigoSalaUnirse)
                        }else{
                            Toast
                                .makeText(context, "Codigo de sala ya existe, inserte otro, por favor", Toast.LENGTH_LONG)
                                .show()
                        }
                    }


            }) {
                Text(text = "BuscarPartida")
            }
            
        }
    }
}