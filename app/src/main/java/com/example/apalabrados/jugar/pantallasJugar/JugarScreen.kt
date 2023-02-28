package com.example.apalabrados.jugar.pantallasJugar

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.apalabrados.helpers.BottomBar
import com.example.apalabrados.jugar.logicaJugar.JugarScreenLogica
import com.example.apalabrados.mvvm.ViewModel
import com.example.apalabrados.session.Session

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun JugarScreen(navController: NavController, ViewModel: ViewModel, codigoSala: String?, tema: String?, jugador: String?){
    val scaffoldState = rememberScaffoldState()
    val sessionManager = Session(LocalContext.current)
    Scaffold(
        scaffoldState = scaffoldState,

        bottomBar = { BottomBar(navController, ViewModel) }
    ) {

        JugarScreenLogica(navController, ViewModel, codigoSala!!, tema!!, jugador!!)

        if (sessionManager.isLoggedIn() ){

        }
    }
}