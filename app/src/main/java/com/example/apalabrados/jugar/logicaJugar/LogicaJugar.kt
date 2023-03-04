package com.example.apalabrados.jugar.logicaJugar

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.apalabrados.conexion.aniadirGanador
import com.example.apalabrados.conexion.buscarJugadorPartida
import com.example.apalabrados.conexion.buscarTurnoOSubturno
import com.example.apalabrados.conexion.consultarGanador
import com.example.apalabrados.mvvm.ViewModel
import com.example.apalabrados.navegacion.PantallasJugar
import com.example.apalabrados.session.Session
import com.example.apalabrados.ui.theme.AzulClarito
import com.example.apalabrados.ui.theme.AzulFondo
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun ContenidoSalaDeEspera(codigoSala: String, sessionManager: Session, navController: NavController, ViewModel: ViewModel){

    val jugador1 by ViewModel.jugador1.observeAsState()
    val jugador2 by ViewModel.jugador2.observeAsState()
    val subturno by ViewModel.subturno.observeAsState()

    val coroutineScope = rememberCoroutineScope()

    coroutineScope.launch {
        while (true) {
            // Llamamos a la función "BuscarTurno()"

            ViewModel.rellenarDatosSalaDeEspera(
                jugador1 = buscarJugadorPartida(codigoSala, "j1").toString(),
                jugador2 = buscarJugadorPartida(codigoSala, "j2").toString(),
                subturno = buscarTurnoOSubturno(codigoSala, "subturno")!!,
            )

            println("logoros j1: " + consultarGanador(codigoSala, "logrosJ1"))
            println("logoros j2: " + consultarGanador(codigoSala, "logrosJ2"))

            if (consultarGanador(codigoSala, "logrosJ1") == 3){
                aniadirGanador(codigoSala, sessionManager.getNick().toString())
                navController.navigate(route = PantallasJugar.GanadorScreen.route + "/" + jugador1)
            } else if(consultarGanador(codigoSala, "logrosJ2") == 3){
                navController.navigate(route = PantallasJugar.GanadorScreen.route + "/" + jugador2)
            }
            // Esperamos cinco segundos antes de volver a llamar a la función
            delay(5000L)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AzulFondo)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
                .background(AzulFondo),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Card(
                modifier = Modifier
                    .width(400.dp)
                    .height(50.dp),
                backgroundColor = AzulClarito
            ) {
                Text(text = "El código de la sala es: $codigoSala",
                    style = TextStyle(
                        fontSize = 20.sp, fontWeight = FontWeight.Bold
                    ),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 10.dp)
                )
            }
            Spacer(modifier = Modifier.padding(10.dp))

            Card(
                modifier = Modifier
                    .width(400.dp)
                    .height(50.dp),
                backgroundColor = AzulClarito

            ) {
                Text(text = "Número de rondas jugadas: $subturno",
                    style = TextStyle(
                        fontSize = 20.sp, fontWeight = FontWeight.Bold
                    ),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 10.dp)
                )
            }

            Spacer(modifier = Modifier.padding(15.dp))
            Card(
                modifier = Modifier
                    .width(400.dp)
                    .height(50.dp),
                backgroundColor = AzulClarito

            ) {
                Text(text = "El jugador 1 es: $jugador1",
                    style = TextStyle(
                        fontSize = 20.sp, fontWeight = FontWeight.Bold
                    ),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 10.dp)
                )
            }
            Spacer(modifier = Modifier.padding(5.dp))

            Card(
                modifier = Modifier
                    .width(400.dp)
                    .height(50.dp),
                backgroundColor = AzulClarito
            ) {
                Text(text = "El jugador 2 es: $jugador2",
                    style = TextStyle(
                        fontSize = 20.sp, fontWeight = FontWeight.Bold
                    ),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 10.dp)
                )
            }

            Spacer(modifier = Modifier.padding(15.dp))

            if (subturno != null){
                if (sessionManager.getNick() == jugador1 && subturno!! % 2 != 0 && jugador2?.isEmpty() == false){

                    Card(
                        modifier = Modifier
                            .width(300.dp)
                            .height(50.dp),
                        backgroundColor = AzulClarito
                    ) {
                        Text(text = "Es tu turno jugador 1 $jugador1",
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(top = 15.dp),
                            style = TextStyle(
                                fontSize = 15.sp, fontWeight = FontWeight.Bold
                            )
                        )
                    }
                    
                    Button(onClick = {
                        navController.navigate(route = PantallasJugar.RuleScreen.route + "/$codigoSala/j1")

                    },
                        border = BorderStroke(1.dp, Color.LightGray),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red),
                    ) {
                        Text(text = "Jugar $jugador1")
                    }

                } else if (sessionManager.getNick() == jugador2 && subturno!! % 2 == 0 && jugador1?.isEmpty() == false){

                    Card(
                        modifier = Modifier
                            .width(300.dp)
                            .height(50.dp),
                        backgroundColor = AzulClarito
                    ) {
                        Text(text = "Es tu turno jugador 2 $jugador2",
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(top = 10.dp),
                            style = TextStyle(
                                fontSize = 15.sp, fontWeight = FontWeight.Bold
                            )
                        )
                    }

                    Button(onClick = {
                        navController.navigate(route = PantallasJugar.RuleScreen.route + "/$codigoSala/j2")

                    },
                        border = BorderStroke(1.dp, Color.LightGray),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red),
                    ) {
                        Text(text = "Jugar $jugador2")
                    }
                }else{
                    Text(text = "Espere a su turno o a que entren todos los jugadores a la sala")
                }
            }
        }

    }
}

