package com.example.apalabrados.conexion

import android.util.Log
import android.widget.Toast
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.apalabrados.helpers.generarCodigoSala
import com.example.apalabrados.viewModel.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

@Composable
fun aniadirPreguntaButton(ViewModel: ViewModel){
    val context = LocalContext.current

    val db = FirebaseFirestore.getInstance()

    val campo by ViewModel.campo.observeAsState(initial = "")
    val respuesta by ViewModel.respuesta.observeAsState(initial = "")


    val pregunta by ViewModel.pregunta.observeAsState(initial = "")
    val respuesta1 by ViewModel.respuesta1.observeAsState(initial = "")
    val respuesta2 by ViewModel.respuesta2.observeAsState(initial = "")
    val respuesta3 by ViewModel.respuesta3.observeAsState(initial = "")

    Button(onClick = {
        if (campo.isEmpty()) {
            Toast
                .makeText(context, "campo en blanco", Toast.LENGTH_LONG)
                .show()
        }else if (pregunta.isEmpty()) {
            Toast
                .makeText(context, "respuesta1 en blanco", Toast.LENGTH_LONG)
                .show()
        }  else if (respuesta1.isEmpty()) {
            Toast
                .makeText(context, "respuesta1 en blanco", Toast.LENGTH_LONG)
                .show()
        } else if (respuesta2.isEmpty()) {
            Toast
                .makeText(context, "respuesta2 en blanco", Toast.LENGTH_LONG)
                .show()
        }  else if (respuesta3.isEmpty()) {
            Toast
                .makeText(context, "respuesta3 en blanco", Toast.LENGTH_LONG)
                .show()
        } else if (respuesta.isEmpty()) {
            Toast
                .makeText(context, "Respuesta corercta en blanco", Toast.LENGTH_LONG)
                .show()
        }else {
            db
                .collection(campo)
                .document()
                .set(mapOf(
                    "pregunta" to pregunta,
                    "respuesta1" to respuesta1,
                    "respuesta2" to respuesta2,
                    "respuesta3" to respuesta3,
                    "correcta" to respuesta
                ))
                .addOnSuccessListener {
                    ViewModel.limpiarCampos()
                    Toast
                        .makeText(context, "Añadido correctamente", Toast.LENGTH_LONG)
                        .show()
                }
                .addOnFailureListener {
                    Toast
                        .makeText(context, "No se ha podido añadir", Toast.LENGTH_LONG)
                        .show()
                }
        }
        println(ViewModel.respuesta.value)
        println(ViewModel.campo.value)
        println("alijdlisajdliajds")
    }) {
        Text(
            text = "Añadir Pregunta",
            fontSize = 26.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
        )
    }
}

fun aniadirPartida(jugador1: String, codigoSala: String){
    val db = FirebaseFirestore.getInstance()

    val partidaData = hashMapOf(
        "partida" to 1,
        "turno" to 1,
        "subturno" to 1,
        "logrosj1" to 0,
        "logrosj2" to 0,
        "j1" to jugador1,
        "j2" to "",
        "ganador" to "",
        "codigo" to codigoSala
    )

    db
        .collection("partida")
        .document()
        .set(partidaData)
        .addOnSuccessListener {
            print("Partida creada correctamente")
        }
        .addOnFailureListener {
            print("Partida no creada correctamente")
        }
}

@Composable
fun buscarJugadorLibre(nombre: String) {
    val db = FirebaseFirestore.getInstance()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        val querySnapshot = db.collection("partida")
            .whereEqualTo("J2", "")
            .limit(1)
            .get()
            .await()
        if (!querySnapshot.isEmpty) {
            // Hay al menos un jugador2 libre
            val docSnapshot = querySnapshot.documents.firstOrNull()
            if (docSnapshot != null) {
                // Actualizamos el documento para añadir a Pedro como jugador 2
                val docId = docSnapshot.id
                db.collection("partida").document(docId)
                    .update("J2", nombre)
                    .addOnSuccessListener {
                        Toast.makeText(context, "Unido a partida correctamente", Toast.LENGTH_LONG)
                            .show()
                    }
                    .addOnFailureListener {
                        Toast.makeText(context, "No se ha podido unir a la partida", Toast.LENGTH_LONG)
                            .show()
                    }
            }
        } else {
            // No hay ningún jugador2 libre
            Toast.makeText(context, "No hay partidas disponibles", Toast.LENGTH_LONG)
                .show()
        }
    }
}


