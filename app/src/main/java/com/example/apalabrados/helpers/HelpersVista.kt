package com.example.apalabrados.pantallas

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.apalabrados.navegacion.PantallasApp
import com.example.apalabrados.mvvm.ViewModel


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
