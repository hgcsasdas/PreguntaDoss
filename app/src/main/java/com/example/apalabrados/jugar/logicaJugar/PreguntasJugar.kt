package com.example.apalabrados.jugar.logicaJugar

import androidx.compose.runtime.*
import androidx.navigation.NavController
import com.example.apalabrados.conexion.buscarPreguntas
import com.example.apalabrados.helpers.elegirTresPreguntasAleatorias
import com.example.apalabrados.model.Pregunta
import com.example.apalabrados.mvvm.ViewModel
import com.example.apalabrados.session.Session
import kotlinx.coroutines.tasks.await

@Composable
fun JugarScreenLogica(navController: NavController, ViewModel: ViewModel, SessionManager: Session,codigoSala: String, tema: String) {
    var ListaPreguntas by remember { mutableStateOf<List<Pregunta>>(emptyList()) }

    LaunchedEffect(tema) {
        val result = buscarPreguntas(tema).await()
        ListaPreguntas = result
    }

    val preguntas = elegirTresPreguntasAleatorias(ListaPreguntas)



    // Renderizar la lista de preguntas
}