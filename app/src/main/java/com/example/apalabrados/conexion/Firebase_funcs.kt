package com.example.apalabrados.conexion

import android.view.View
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.apalabrados.mvvm.ViewModel
import com.example.apalabrados.ui.theme.AzulClarito
import com.example.apalabrados.ui.theme.AzulFondo
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
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
    },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = AzulClarito,
            contentColor = AzulFondo
        )        ) {
        Text(
            text = "Añadir Pregunta",
            fontSize = 26.sp,

            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
        )
    }
}

//Con esta función se crea una partida
@Composable
fun aniadirPartida(jugador1: String, ViewModel: ViewModel){
    val context = LocalContext.current

    Button(onClick = {

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
            "codigo" to ViewModel.codigoSala.value
        )

        GlobalScope.launch(Dispatchers.Main) {
            val partidaEncontrada = ViewModel.codigoSala.value?.let { buscarPartidaPorCodigo(it) }
            if (partidaEncontrada == true) {
                Toast
                    .makeText(context, "Codigo de sala ya existe, inserte otro, por favor", Toast.LENGTH_LONG)
                    .show()
            } else {
                ViewModel.codigoSala.value?.let {
                    db
                        .collection("partida")
                        .document(it)
                        .set(partidaData)
                        .addOnSuccessListener {
                            ViewModel.limpiarCampos()
                            Toast
                                .makeText(context, "Añadido correctamente", Toast.LENGTH_LONG)
                                .show()

                            ViewModel.limpiarCodigoSala()
                        }
                        .addOnFailureListener {
                            Toast
                                .makeText(context, "No se ha podido añadir", Toast.LENGTH_LONG)
                                .show()
                        }
            }
        }
    }
    },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = AzulClarito,
            contentColor = AzulFondo
        )) {
        Text(text = "Crear partida")
    }

}

//Se llama a esta función para buscar si está libre ese código
suspend fun buscarPartidaPorCodigo(codigoSala: String): Boolean {
    val db = FirebaseFirestore.getInstance()
    val coleccion = db.collection("partida")
    val consulta = coleccion.whereEqualTo("codigo", codigoSala)

    return try {
        val documentos = consulta.get().await()
        !documentos.isEmpty
    } catch (excepcion: Exception) {
        false
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

fun buscarUsuarioReference(nick: String, onComplete: (Boolean) -> Unit) {
    val db = FirebaseFirestore.getInstance()
    val usersCollection = db.collection("usuarios")
    val query = usersCollection.whereEqualTo("usuario", nick)

    query.get().addOnSuccessListener { querySnapshot ->
        if (!querySnapshot.isEmpty) {
            onComplete(true) // El documento ya contiene el nick proporcionado
        } else {
            onComplete(false) // El documento no contiene el nick proporcionado
        }
    }.addOnFailureListener { exception ->
        onComplete(false) // Error al obtener el documento
    }
}


fun verSiExisteUsuario(usuario: String, contrasenia: String, callback: (Boolean) -> Unit) {
    val db = FirebaseFirestore.getInstance()

    // Consulta en la base de datos si existe un usuario con el nombre y la contraseña proporcionados
    db.collection("usuarios")
        .whereEqualTo("usuario", usuario)
        .whereEqualTo("password", contrasenia)
        .get()
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val result = task.result
                if (!result?.isEmpty!!) {
                    callback(true)
                } else {
                    // Si no existe un usuario con el nombre y la contraseña proporcionados, devuelve falso
                    callback(false)
                }
            } else {
                callback(false)
            }
        }
}

fun cogerEmailSessionUsuario(user: String, onComplete: (String?) -> Unit) {
    val db = FirebaseFirestore.getInstance()
    val coleccion = db.collection("usuarios")
    val consulta = coleccion.whereEqualTo("usuario", user)

    consulta.get().addOnSuccessListener { querySnapshot ->
        if (!querySnapshot.isEmpty) {
            val documento = querySnapshot.documents[0]
            val email = documento.getString("email")
            onComplete(email)
        } else {
            onComplete(null)
        }
    }.addOnFailureListener { exception ->
        onComplete(null)
    }
}

fun getStringFirebase(coleccion: String, keyDato: String, datoProporcionado: String): String{
    val db = FirebaseFirestore.getInstance()
    val coleccion = db.collection(coleccion)
    val consulta = coleccion.whereEqualTo(keyDato, datoProporcionado)

    return try {
        "sadasd"
    } catch (excepcion: Exception) {
        "$excepcion"
    }
}