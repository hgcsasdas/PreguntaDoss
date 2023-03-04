package com.example.apalabrados.jugar.logicaJugar

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalProvider
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.apalabrados.conexion.buscarPreguntas
import com.example.apalabrados.conexion.jugadorAcerto
import com.example.apalabrados.conexion.jugadorFallo
import com.example.apalabrados.helpers.elegirTresPreguntasAleatorias
import com.example.apalabrados.model.Pregunta
import com.example.apalabrados.mvvm.ViewModel
import com.example.apalabrados.navegacion.PantallasJugar
import com.example.apalabrados.session.Session
import com.example.apalabrados.ui.theme.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.tasks.await

@Composable
fun JugarScreenLogica(
    navController: NavController,
    ViewModel: ViewModel,
    codigoSala: String,
    tema: String,
    jugador: String,
) {
    var ListaPreguntas by remember { mutableStateOf<List<Pregunta>>(emptyList()) }
    LaunchedEffect(tema) {
        val result = buscarPreguntas(tema).await()
        ListaPreguntas = result
        ListaPreguntas = elegirTresPreguntasAleatorias(ListaPreguntas)

    }

    MostrarPreguntas(ListaPreguntas, navController, codigoSala, jugador, tema)


}

@Composable
fun MostrarPreguntas(
    preguntas: List<Pregunta>,
    navController: NavController,
    codigoSala: String,
    jugador: String,
    tema: String,
) {

    var total = 0
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.93f)
            .background(AzulFondo)
            .verticalScroll(scrollState)
    ) {
        Column(
            Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Card(
                border = BorderStroke(1.dp, Color.LightGray),
                modifier = Modifier
                    .padding(top = 8.dp),
                backgroundColor = ColorNaranja,
                elevation = 6.dp,
            ) {
                Text(
                    text = "$tema",
                    modifier = Modifier.padding(5.dp)
                )
            }
        }
        Spacer(modifier = Modifier.padding(10.dp))
            preguntas.forEachIndexed { index, pregunta ->
                // Mostrar la pregunta
                Column(
                    modifier = Modifier
                        .padding(start = 7.dp)
                ) {
                    Text(
                        pregunta.pregunta,
                        style = TextStyle(
                            fontSize = 20.sp, fontWeight = FontWeight.Bold
                        )
                    )
                }


                // Crear un grupo de radiobuttons para las respuestas
                var respuestaSeleccionada by remember { mutableStateOf("") }
                Card(
                    Modifier
                        .fillMaxWidth()
                        .padding(start = 7.dp, end = 5.dp),
                    backgroundColor = AzulFondo

                ) {
                    Column(
                        horizontalAlignment = Alignment.Start
                    ) {
                        Card(
                            backgroundColor = AzulClarito,
                            border = BorderStroke(1.dp, Color.White),
                        ) {
                            Row(
                                Modifier.fillMaxWidth()
                            ) {
                                RadioButton(
                                    selected = respuestaSeleccionada == pregunta.respuesta1,
                                    onClick = { respuestaSeleccionada = pregunta.respuesta1 },
                                )
                                Text(
                                    pregunta.respuesta1,
                                    modifier = Modifier.padding(top = 11.dp)
                                )
                            }
                        }
                        Spacer(modifier = Modifier.padding(3.dp))
                        Card(
                            backgroundColor = AzulClarito,
                            border = BorderStroke(1.dp, Color.White),
                        ) {
                            Row(
                                Modifier.fillMaxWidth()
                            ) {
                                RadioButton(
                                    selected = respuestaSeleccionada == pregunta.respuesta2,
                                    onClick = { respuestaSeleccionada = pregunta.respuesta2 },
                                )
                                Text(
                                    pregunta.respuesta2,
                                    modifier = Modifier.padding(top = 11.dp)
                                )
                            }
                        }
                        Spacer(modifier = Modifier.padding(3.dp))
                        Card(
                            backgroundColor = AzulClarito,
                            border = BorderStroke(1.dp, Color.White),


                            ) {
                            Row(
                                Modifier.fillMaxWidth()
                            ) {
                                RadioButton(
                                    selected = respuestaSeleccionada == pregunta.respuesta3,
                                    onClick = { respuestaSeleccionada = pregunta.respuesta3 },
                                )
                                Text(
                                    pregunta.respuesta3,
                                    modifier = Modifier.padding(top = 11.dp)
                                )
                            }
                        }
                    }
                }
                // Comprobar si la respuesta seleccionada coincide con la respuesta correcta
                if (respuestaSeleccionada == pregunta.correcta) {
                    total++
                }

                // Mostrar un separador visual entre cada pregunta
                Spacer(modifier = Modifier.padding(15.dp))
            }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(AzulFondo)
            ) {
                Column(
                    modifier = Modifier.background(AzulFondo),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Button(
                        border = BorderStroke(1.dp, Color.LightGray),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red),
                        onClick = {
                            // Mostrar el diálogo con los resultados
                            if (total == 3) {
                                jugadorAcerto(codigoSala, jugador)
                                total = 0
                                navController.navigate(route = PantallasJugar.SalaDeEspera.route + "/$codigoSala")
                            } else {
                                jugadorFallo(codigoSala)
                                total = 0
                                navController.navigate(route = PantallasJugar.SalaDeEspera.route + "/$codigoSala")

                            }
                        }
                    ) {
                        Text(text = "Enviar")
                    }
            }
        }
    }
}
