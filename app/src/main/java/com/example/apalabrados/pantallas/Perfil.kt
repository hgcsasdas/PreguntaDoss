 package com.example.apalabrados.pantallas

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.apalabrados.conexion.buscarPartidasGanadasJugador
import com.example.apalabrados.helpers.BottomBar
import com.example.apalabrados.helpers.ProfileScreen
import com.example.apalabrados.mvvm.ViewModel
import com.example.apalabrados.session.Session
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

 @SuppressLint("UnusedMaterialScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@Composable
fun Perfil(navController: NavController, ViewModel: ViewModel) {
    val scaffoldState = rememberScaffoldState()
    val sessionManager = Session(LocalContext.current)


    Scaffold(
        scaffoldState = scaffoldState,

        bottomBar = { BottomBar(navController, ViewModel) }
    ) {

        //content area
        if (sessionManager.isLoggedIn()) {
            val nick = sessionManager.getNick()
            val email = sessionManager.getEmail()
            ProfileScreen(nick!!, email!!, navController, ViewModel)
            // hacer algo con los datos de la sesión del usuario
        } else {
            // el usuario no ha iniciado sesión, redirigirlo a la pantalla de inicio de sesión
        }
    }
}
