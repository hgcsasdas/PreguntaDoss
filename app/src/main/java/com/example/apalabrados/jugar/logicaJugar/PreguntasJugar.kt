package com.example.apalabrados.jugar.logicaJugar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.apalabrados.conexion.buscarPreguntas
import com.example.apalabrados.conexion.jugadorAcerto
import com.example.apalabrados.conexion.jugadorFallo
import com.example.apalabrados.helpers.elegirTresPreguntasAleatorias
import com.example.apalabrados.model.Pregunta
import com.example.apalabrados.mvvm.ViewModel
import com.example.apalabrados.navegacion.PantallasJugar
import com.example.apalabrados.session.Session
import com.example.apalabrados.ui.theme.AzulFondo
import com.example.apalabrados.ui.theme.CardPerfil
import com.example.apalabrados.ui.theme.Marcos1
import kotlinx.coroutines.delay
import kotlinx.coroutines.tasks.await

@Composable
fun JugarScreenLogica(navController: NavController, ViewModel: ViewModel,codigoSala: String, tema: String, jugador: String) {
    var ListaPreguntas by remember { mutableStateOf<List<Pregunta>>(emptyList()) }
    LaunchedEffect(tema) {
        val result = buscarPreguntas(tema).await()
        ListaPreguntas = result
        ListaPreguntas = elegirTresPreguntasAleatorias(ListaPreguntas)

    }

    MostrarPreguntas(ListaPreguntas, navController, codigoSala, jugador, tema)


}

@Composable
fun MostrarPreguntas(preguntas: List<Pregunta>, navController: NavController, codigoSala: String, jugador: String, tema: String) {

    var total = 0

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AzulFondo),

    ) {
        Card(
            modifier = Modifier
                .width(50.dp)
                .height(35.dp),
            backgroundColor = Marcos1,


        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally ,
                verticalArrangement = Arrangement.Center,


                ) {
                Text(text = "$tema")
            }

        }
        Spacer(modifier = Modifier.padding(7.dp))
        preguntas.forEachIndexed { index, pregunta ->
            // Mostrar la pregunta
            Text(pregunta.pregunta)

            // Crear un grupo de radiobuttons para las respuestas
            var respuestaSeleccionada by remember { mutableStateOf("") }
            Column (
                horizontalAlignment = Alignment.Start ,
                ){
                Row(
                    horizontalArrangement = Arrangement.Start

                ) {
                    RadioButton(
                        selected = respuestaSeleccionada == pregunta.respuesta1,
                        onClick = { respuestaSeleccionada = pregunta.respuesta1 },
                    )
                    Text(pregunta.respuesta1)
                }
                Row() {
                    RadioButton(
                        selected = respuestaSeleccionada == pregunta.respuesta2,
                        onClick = { respuestaSeleccionada = pregunta.respuesta2 },
                    )
                    Text(pregunta.respuesta2)
                }
                Row() {
                    RadioButton(
                        selected = respuestaSeleccionada == pregunta.respuesta3,
                        onClick = { respuestaSeleccionada = pregunta.respuesta3 },
                    )
                    Text(pregunta.respuesta3)
                }
            }


            // Comprobar si la respuesta seleccionada coincide con la respuesta correcta
            if (respuestaSeleccionada == pregunta.correcta) {
                total ++
            }

            // Mostrar un separador visual entre cada pregunta
            Divider()
            Spacer(modifier = Modifier.padding(15.dp))
        }

        Button(onClick = {
            // Mostrar el diálogo con los resultados
            println(total)
            if (total == 3){
                jugadorAcerto(codigoSala, jugador)
                total=0
                navController.navigate(route = PantallasJugar.SalaDeEspera.route + "/$codigoSala")
            }else {
                jugadorFallo(codigoSala)
                total=0
                navController.navigate(route = PantallasJugar.SalaDeEspera.route + "/$codigoSala")

            }

        }) {
            Text(text = "Enviar")
        }
    }
}
