package com.example.apalabrados.pantallas

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.apalabrados.conexion.buscarPartidasGanadasJugador
import com.example.apalabrados.mvvm.ViewModel
import com.example.apalabrados.session.Session

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Perfil(navController: NavController, ViewModel: ViewModel){
    val scaffoldState = rememberScaffoldState()
    val sessionManager = Session(LocalContext.current)
    var partidas_ganadas = 0

    buscarPartidasGanadasJugador("usuario123") { partidasGanadas ->
        partidas_ganadas = partidasGanadas
    }
    Scaffold (
        scaffoldState = scaffoldState,

        bottomBar = {BottomBar(navController, ViewModel)}
    ) {
        //content area
        Box(modifier = Modifier
            .background(Color(0xff546e7a))
            .fillMaxSize()){
            if (sessionManager.isLoggedIn()) {
                val nick = sessionManager.getNick()
                val email = sessionManager.getEmail()
                val password = sessionManager.getPassword()
                Column() {
                    Text(text = nick!!)
                    Text(text = email!!)
                    Text(text = password!!)
                    Text(text = "partdas ganadas: $partidas_ganadas")
                }
                // hacer algo con los datos de la sesión del usuario
            } else {
                // el usuario no ha iniciado sesión, redirigirlo a la pantalla de inicio de sesión
            }

        }
    }
}
