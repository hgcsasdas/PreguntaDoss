package com.example.apalabrados.pantallas

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.apalabrados.viewModel.ViewModel
import com.example.apalabrados.R

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SeleccionMiniJuego(navController: NavController, ViewModel: ViewModel){
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold (
        scaffoldState = scaffoldState,

        bottomBar = {BottomBar(navController, ViewModel)}
    ) {
        //content area
        Box(modifier = Modifier
            .background(Color(0xff546e7a))
            .fillMaxSize()){
            MiniJuegoOpcs()
        }
    }
}

@Composable
fun MiniJuegoOpcs(){
    Column() {
        Row() {
            Box() {
                Image(painter = painterResource(id = R.drawable.tictactoe), contentDescription = null)
                Spacer(modifier = Modifier.padding(12.dp))
                Text(text = "Tic Tac Toe")
            }
        }
    }
}