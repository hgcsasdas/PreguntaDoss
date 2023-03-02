package com.example.apalabrados.jugar.logicaJugar

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
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

            if (consultarGanador(codigoSala, "logrosj1") == 3){
                navController.navigate(route = PantallasJugar.GanadorScreen.route + "/" + jugador1)
            } else if(consultarGanador(codigoSala, "logrosj2") == 3){
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
            modifier = Modifier.fillMaxSize()
                .padding(15.dp)
                .background(AzulClarito),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(text = "Sala: $codigoSala")

            Text(text = "Turno: $subturno")



            Spacer(modifier = Modifier.padding(15.dp))

            Text(text = "Jugador 1: $jugador1")
            Text(text = "Jugador 2: $jugador2")

            Spacer(modifier = Modifier.padding(15.dp))

            if (subturno != null){
                if (sessionManager.getNick() == jugador1 && subturno!! % 2 != 0 && jugador2?.isEmpty() == false){

                    Text(text = "Turno jugador 1")

                    Button(onClick = {
                        navController.navigate(route = PantallasJugar.RuleScreen.route + "/$codigoSala/j1")

                    },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = AzulClarito,
                            contentColor = AzulFondo
                        ) ) {
                        Text(text = "Jugar p1")
                    }

                } else if (sessionManager.getNick() == jugador2 && subturno!! % 2 == 0 && jugador1?.isEmpty() == false){

                    Text(text = "Turno jugador 2")

                    Button(onClick = {
                        navController.navigate(route = PantallasJugar.RuleScreen.route + "/$codigoSala/j2")

                    },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = AzulClarito,
                            contentColor = AzulFondo
                        ) ) {
                        Text(text = "Jugar p2")
                    }
                }else{
                    Text(text = "Espere a su turno o a que entren todos los jugadores a la sala")
                }
            }
        }

    }
}

