package com.example.apalabrados.conexion

import android.util.Log
import androidx.compose.runtime.Composable
import com.example.apalabrados.model.Partida
import com.example.apalabrados.mvvm.ViewModel
import com.google.android.gms.tasks.Tasks
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

val db = FirebaseFirestore.getInstance()

fun obtenerNombreJ2(codigo: String, varBuscar: String): String {
    var nombre: String = ""
    db.collection("partida")
        .get()
        .addOnSuccessListener { querySnapshot ->
            for (document in querySnapshot) {
                if (document.getString("codigo") == codigo) {
                    nombre = document.getString(varBuscar) ?: ""
                    break
                }
            }
        }
    return nombre
}

suspend fun buscarPartidasGanadasJugador(user: String, viewModel: ViewModel) {
    val db = FirebaseFirestore.getInstance()
    val coleccion = db.collection("partida")
    val consulta = coleccion.whereEqualTo("ganador", user)

    consulta.get().addOnSuccessListener { querySnapshot ->
        val partidasGanadas = querySnapshot.size()
        viewModel.rellenarPartidasGanadas(
            partidasGanadas = partidasGanadas
        )
    }.addOnFailureListener { exception ->


    }
}