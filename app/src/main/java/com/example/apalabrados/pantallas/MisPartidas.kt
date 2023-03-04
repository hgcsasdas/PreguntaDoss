package com.example.apalabrados.pantallas

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.apalabrados.conexion.buscarPartidasUsuario
import com.example.apalabrados.helpers.BottomBar
import com.example.apalabrados.model.Partida
import com.example.apalabrados.mvvm.ViewModel
import com.example.apalabrados.navegacion.PantallasJugar
import com.example.apalabrados.session.Session
import com.example.apalabrados.ui.theme.AzulClarito
import com.example.apalabrados.ui.theme.AzulFondo
import com.example.apalabrados.ui.theme.ColorNaranja
import com.example.apalabrados.ui.theme.Marcos1

import kotlinx.coroutines.tasks.await

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@Composable
fun MisPartidas(navController: NavController, viewModel: ViewModel) {

    val scaffoldState = rememberScaffoldState()

    val sessionManager = Session(LocalContext.current)

    var ListaPartidas by remember { mutableStateOf<List<Partida?>>(emptyList()) }

    Scaffold(
        scaffoldState = scaffoldState,
        bottomBar = { BottomBar(navController, viewModel) }
    ) {
        LaunchedEffect(true) {
            val partidas = buscarPartidasUsuario(sessionManager).await()
            ListaPartidas = partidas
        }

        MostrarPartidas(listaPartidas = ListaPartidas.toMutableStateList(), navController)
    }
}

@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MostrarPartidas(listaPartidas: SnapshotStateList<Partida?>, navController: NavController) {
    //val roundCornerShape = RoundedCornerShape(topEnd = 30.dp, bottomStart = 30.dp)
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.93f)
            .background(AzulFondo),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Card(
                border = BorderStroke(1.dp, Color.LightGray),
                modifier = Modifier
                    .padding(8.dp),
                elevation = 6.dp,
                backgroundColor = ColorNaranja

            ) {
                Text(
                    text = "Mis partidas",
                    modifier = Modifier.padding(5.dp)
                )
            }

        }
        //Llamo a la lazyColumn
        LazyColumn() {
            // de cada dato creo una carta
            itemsIndexed(listaPartidas) { index, item ->
                Card(
                    border = BorderStroke(1.dp, Color.LightGray),
                    onClick = {
                        navController.navigate(route = PantallasJugar.SalaDeEspera.route + "/" + listaPartidas[index]?.codigo)

                        Toast.makeText(
                            context,
                            listaPartidas[index]?.codigo + " selected..",
                            Toast.LENGTH_SHORT
                        ).show()
                    },
                    modifier = Modifier
                        .padding(8.dp),
                    elevation = 6.dp
                ) {
                    Column(
                        modifier = Modifier
                            .padding(1.dp)
                            .fillMaxWidth()
                            .background(AzulClarito)

                    ) {
                        Spacer(modifier = Modifier.width(5.dp))
                        //hago un display del nombre
                        listaPartidas[index]?.codigo?.let {
                            Column(
                                Modifier.fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally,

                                ) {
                                Text(
                                    text = "Codigo de sala: $it",
                                    modifier = Modifier.padding(4.dp),
                                    color = Color.White,
                                    textAlign = TextAlign.Center,
                                    style = TextStyle(
                                        fontSize = 20.sp, fontWeight = FontWeight.Bold
                                    )
                                )

                            }
                        }

                        Spacer(modifier = Modifier.height(5.dp))

                        listaPartidas[index]?.j1?.let {
                            Text(
                                text = "Jugador 1 = $it",

                                modifier = Modifier.padding(4.dp),

                                color = Color.White,
                                textAlign = TextAlign.Center,
                                style = TextStyle(
                                    fontSize = 15.sp
                                )
                            )
                        }

                        Spacer(modifier = Modifier.width(5.dp))

                        listaPartidas[index]?.j2?.let {
                            Text(
                                text = "Jugador 2 = $it",

                                modifier = Modifier.padding(4.dp),

                                color = Color.White,
                                textAlign = TextAlign.Center,
                                style = TextStyle(fontSize = 15.sp)
                            )
                        }

                        Spacer(modifier = Modifier.width(5.dp))

                        listaPartidas[index]?.subturno?.let {
                            Text(
                                text = "Turno = $it",
                                modifier = Modifier.padding(4.dp),
                                color = Color.White,
                                textAlign = TextAlign.Center,
                                style = TextStyle(fontSize = 15.sp)
                            )
                        }
                    }
                }
            }
        }
    }
}