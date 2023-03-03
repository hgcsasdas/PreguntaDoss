package com.example.apalabrados.helpers

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.apalabrados.R
import com.example.apalabrados.conexion.buscarPartidasGanadasJugador
import com.example.apalabrados.navegacion.PantallasApp
import com.example.apalabrados.mvvm.ViewModel
import com.example.apalabrados.session.Session
import com.example.apalabrados.ui.theme.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


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
//FUNCIÓN VISTA PARA OMSTRAR LOS DATOS DEL PERFIL
@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "CoroutineCreationDuringComposition")

@Composable
fun ProfileScreen(user: String, email: String, navController: NavController, viewModel: ViewModel) {
    val coroutineScope = rememberCoroutineScope()
    val sessionManager = Session(LocalContext.current)
    val partidasGandas by viewModel.partidasGanadas.observeAsState()


    coroutineScope.launch {
        buscarPartidasGanadasJugador(sessionManager.getNick()!!, viewModel)
        delay(5000L)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AzulFondo),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.preguntadoss),
            contentDescription = null,
        )
        Spacer(modifier = Modifier.padding(40.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            backgroundColor = CardPerfil,
            elevation = 8.dp
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Bienvenido, $user",
                    style = MaterialTheme.typography.h6,
                    color = AzulFondo
                )
             }
        }

        Spacer(modifier = Modifier.padding(7.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            backgroundColor = CardPerfil,
            elevation = 8.dp
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Email: \n" + "\n $email",
                    style = MaterialTheme.typography.h6,
                    color = AzulFondo
                )
            }
        }
        Spacer(modifier = Modifier.padding(7.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            backgroundColor = CardPerfil,
            elevation = 8.dp
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Partidas ganadas: \n" + "\n $partidasGandas ",
                    style = MaterialTheme.typography.h6,
                    color = AzulFondo
                )
            }
        }

        Spacer(modifier = Modifier.padding(7.dp))
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = {
                    navController.navigate(PantallasApp.LoginScreen.route)
                },  modifier = Modifier
                    .width(150.dp)
                    .height(55.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xFFFF4303),
                    contentColor = Color.White,
                )

            ) {
                Text(
                    text = "LogOut",
                    color = ColorBotonPerfil
                )
            }
        }
    }
}


