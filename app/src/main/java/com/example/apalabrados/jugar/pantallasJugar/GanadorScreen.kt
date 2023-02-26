package com.example.apalabrados.jugar.pantallasJugar

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.apalabrados.helpers.BottomBar
import com.example.apalabrados.mvvm.ViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun GanadorScreen(navController: NavController, ViewModel: ViewModel, nombreGanador: String?){

    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,

        bottomBar = { BottomBar(navController, ViewModel) }
    ) {

        Text(text = "Ganador: $nombreGanador")
    }
}