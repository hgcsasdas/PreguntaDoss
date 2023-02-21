package com.example.apalabrados.pantallas

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.apalabrados.mvvm.ViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Perfil(navController: NavController, ViewModel: ViewModel){
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold (
        scaffoldState = scaffoldState,

        bottomBar = {BottomBar(navController, ViewModel)}
    ) {
        //content area
        Box(modifier = Modifier
            .background(Color(0xff546e7a))
            .fillMaxSize())
    }
}
