package com.example.apalabrados.pantallas

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.apalabrados.viewModel.ViewModel
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Inicio(navController: NavController, ViewModel: ViewModel){
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold (
        scaffoldState = scaffoldState,
        topBar = {
            TopBarGeneral(
                onMenuButtonClick = {
                    scope.launch {
                        scaffoldState.drawerState.open()
                    }
                },
                onActionButtonClick = { /* */ }
            )
        },
        drawerContent = { // Contenido del drawer
            DrawerContent { // Cerrar drawer
                scope.launch { scaffoldState.drawerState.close() }
            }
        }

    ){

    }
}

@Composable
fun TopBarGeneral(
    onMenuButtonClick: () -> Unit, // Acciones a ejecutar
    onActionButtonClick: (String) -> Unit
) {

    TopAppBar(
        navigationIcon = {
            IconButton(onClick = onMenuButtonClick ) {
                Icon(imageVector = Icons.Filled.Menu, contentDescription = "Abrir menú desplegable")
            }
        },
        title = { Text(text = "PreguntaDoss" +
                "") },
        actions = {
            IconButton(onClick = { }) {
                Icon(imageVector = Icons.Filled.MoreVert, contentDescription = "Más")
            }
        }
    )
}

@Composable
fun DrawerContent(closeDrawer: () -> Unit) {
    val sections = listOf(
        "Perfil",
        "Jugar",
        "Mini Juegos",
        "Añadir preguntas",
    )
    Column(
        Modifier
            .padding(vertical = 8.dp)
    ) {
        sections.forEach { section ->
            TextButton(
                onClick = closeDrawer, // Ejecución
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.CenterStart
                ) {

                    val textColor = MaterialTheme.colors.onSurface
                    Text(
                        text = section,
                        style = MaterialTheme.typography.body2.copy(color = textColor)
                    )
                }
            }
        }
    }
}