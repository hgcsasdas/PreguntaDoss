package com.example.apalabrados.pantallas

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.apalabrados.modelos.bottomNavItems
import com.example.apalabrados.navegacion.PantallasApp
import com.example.apalabrados.viewModel.ViewModel
import kotlinx.coroutines.launch


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
        Box(modifier = Modifier
            .background(Color(0xff546e7a))
            .fillMaxSize())
    }
}

@Composable
fun BottomBar(navController: NavController, ViewModel: ViewModel) {
    val selectedIndex by ViewModel.selectedIndex.observeAsState(initial = 0)

    BottomNavigation(elevation = 10.dp) {

        BottomNavigationItem(icon = {
            Icon(imageVector = Icons.Default.Home,"")
        },
            label = { Text(text = "Inicio") },
            selected = (ViewModel.selectedIndex.value == 0),
            onClick = {
                ViewModel.indexUpdate(
                    index = 0
                )
                navController.navigate(PantallasApp.Inicio.route)
            })

        BottomNavigationItem(icon = {
            Icon(imageVector = Icons.Default.Add,"")
        },
            label = { Text(text = "Preguntas") },
            selected = (ViewModel.selectedIndex.value == 1),
            onClick = {
                ViewModel.indexUpdate(
                    index = 1
                )
                navController.navigate(PantallasApp.AniadirPreguntas.route)
            })

        BottomNavigationItem(icon = {
            Icon(imageVector = Icons.Default.Person,"")
        },
            label = { Text(text = "Perfil") },
            selected = (ViewModel.selectedIndex.value == 2),
            onClick = {
                ViewModel.indexUpdate(
                    index = 2
                )
                navController.navigate(PantallasApp.PerfilScreen.route)
            })
    }
}
