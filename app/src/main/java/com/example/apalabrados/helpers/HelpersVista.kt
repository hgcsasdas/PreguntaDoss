package com.example.apalabrados.helpers

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.apalabrados.R
import com.example.apalabrados.navegacion.PantallasApp
import com.example.apalabrados.mvvm.ViewModel
import com.example.apalabrados.ui.theme.AzulClarito
import com.example.apalabrados.ui.theme.AzulFondo
import com.example.apalabrados.ui.theme.ColorBotonPerfil
import com.example.apalabrados.ui.theme.ColorPerfil


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
@Composable
fun ProfileScreen(user: String, email: String, partidasGandas: Int, navController: NavController) {

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
        Spacer(modifier = Modifier.padding(15.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            backgroundColor = AzulClarito,
            elevation = 8.dp
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Perfil de: $user",
                    style = MaterialTheme.typography.h6,
                    color = AzulFondo
                )
             }
        }
      /* Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            backgroundColor = AzulClarito,
            elevation = 8.dp
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Usuario",
                    style = MaterialTheme.typography.h5,
                    color = AzulFondo
                )
            }
        }*/
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            backgroundColor = AzulClarito,
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
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            backgroundColor = AzulClarito,
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

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            backgroundColor = AzulFondo,
            elevation = 8.dp
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = {
                        navController.navigate(PantallasApp.LoginScreen.route)
                },
                    modifier = Modifier
                        .width(150.dp)
                        .height(55.dp),

                ) {
                    Text(
                        text = "LogOut",
                        color = ColorPerfil
                    )
                }
            }
        }
    }
}
