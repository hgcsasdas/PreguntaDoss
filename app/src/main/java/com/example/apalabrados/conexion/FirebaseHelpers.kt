package com.example.apalabrados.conexion

import android.util.Log
import androidx.compose.runtime.Composable
import com.example.apalabrados.model.Partida
import com.google.android.gms.tasks.Tasks
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

val db = FirebaseFirestore.getInstance()

fun buscarEnFirebase(codigoBuscado: String): Boolean {
    var existe = false
    val docRef = db.collection("partida")
    docRef.get()
        .addOnSuccessListener { queryDocumentSnapshots ->
            if (!queryDocumentSnapshots.isEmpty) {
                val list = queryDocumentSnapshots.documents
                for (d in list) {
                    val partida: Partida? = d.toObject(Partida::class.java)
                    if (codigoBuscado == partida?.codigo) {
                        existe = true
                        println("falso")
                        break // exit the loop
                    } else {
                        existe = false
                    }
                }
            }
        }
        .addOnFailureListener { exception ->
            // handle exception
        }
    return existe
}
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
    print(nombre + "siuu")
    return nombre
}
fun getall(){

    val docRef = db.collection("partida")
    docRef.
    get()
        .addOnSuccessListener { result ->
            for (document in result) {
                val partida: Partida? = document.toObject(Partida::class.java)
                Log.d("DATOS: ","${partida?.j1}")
                println("asldsalhdlsahdlsahdaisdlisahda")
                /*if (partida?.codigo == "NRVG"){
                    println("PERRAAA")
                }*/

            }
        }
        .addOnFailureListener { exception ->

        }
}
fun generarCodigo(): String {
    val caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    val longitudCodigo = 4
    val codigo = StringBuilder()
    var existe: Boolean
    do {
        codigo.clear()
        for (i in 0 until longitudCodigo) {
            val aleatorio = (caracteres.indices).random()
            codigo.append(caracteres[aleatorio])
        }
        existe = try {
            val docRef = db.collection("partida")
            val querySnapshot = Tasks.await(docRef.whereEqualTo("codigo", codigo.toString()).get())
            !querySnapshot.isEmpty
        } catch (e: Exception) {
            false
        }
    } while (existe)
    return codigo.toString()
}
fun buscarTodasPartidasJugador(){

}
@Composable
fun buscarPartidasGanadasJugador(user: String, onComplete: (Int) -> Unit) {
    val db = FirebaseFirestore.getInstance()
    val coleccion = db.collection("partida")
    val consulta = coleccion.whereEqualTo("ganador", user)

    consulta.get().addOnSuccessListener { querySnapshot ->
        val partidasGanadas = querySnapshot.size()
        onComplete(partidasGanadas)
    }.addOnFailureListener { exception ->
        onComplete(0)
    }
}