package com.example.apalabrados.jugar.pantallasJugar

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.apalabrados.helpers.BottomBar
import com.example.apalabrados.jugar.logicaJugar.ContenidoSalaDeEspera
import com.example.apalabrados.session.Session
import com.example.apalabrados.mvvm.ViewModel
import kotlinx.coroutines.*


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SalaDeEspera(navController: NavController, ViewModel: ViewModel, codigoSala: String?){
    val scaffoldState = rememberScaffoldState()
    val sessionManager = Session(LocalContext.current)
    Scaffold(
        scaffoldState = scaffoldState,

        bottomBar = { BottomBar(navController, ViewModel) }
    ) {

        if (sessionManager.isLoggedIn() ){
            codigoSala?.let {

                ContenidoSalaDeEspera( codigoSala, sessionManager, navController, ViewModel)
            }
        }
    }
}
