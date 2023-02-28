package com.example.apalabrados.jugar.logicaJugar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
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
import kotlinx.coroutines.tasks.await

@Composable
fun JugarScreenLogica(navController: NavController, ViewModel: ViewModel,codigoSala: String, tema: String, jugador: String) {
    var ListaPreguntas by remember { mutableStateOf<List<Pregunta>>(emptyList()) }
    println(tema)
    LaunchedEffect(tema) {
        val result = buscarPreguntas(tema).await()
        ListaPreguntas = result
        ListaPreguntas = elegirTresPreguntasAleatorias(ListaPreguntas)

    }

    MostrarPreguntas(ListaPreguntas, navController, codigoSala, jugador)


}

@Composable
fun MostrarPreguntas(preguntas: List<Pregunta>, navController: NavController, codigoSala: String, jugador: String) {

    var total = 0

    Column {
        preguntas.forEachIndexed { index, pregunta ->
            // Mostrar la pregunta
            Text(pregunta.pregunta)

            // Crear un grupo de radiobuttons para las respuestas
            var respuestaSeleccionada by remember { mutableStateOf("") }
            Column {
                Row() {
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
            if (total == 3){
                jugadorAcerto(codigoSala, jugador)
                navController.navigate(route = PantallasJugar.SalaDeEspera.route + "/$codigoSala")
            }else {
                jugadorFallo(codigoSala)
                navController.navigate(route = PantallasJugar.SalaDeEspera.route + "/$codigoSala")

            }

        }) {
            Text(text = "Enviar")
        }
    }
}
