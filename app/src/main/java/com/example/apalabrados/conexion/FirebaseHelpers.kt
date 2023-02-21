package com.example.apalabrados.conexion

import android.util.Log
import com.example.apalabrados.model.Partida
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject

val db = FirebaseFirestore.getInstance()

fun buscarEnFirebase(codigoBuscado: String): Boolean {
    var existe = false
    val docRef = db.collection("partida")
    docRef.
    get()
        .addOnSuccessListener {
                queryDocumentSnapshots ->
            if (!queryDocumentSnapshots.isEmpty) {
                /*Si los datos no están vacíos
                * añadimos los datos a una lista*/
                val list = queryDocumentSnapshots.documents
                for (d in list) {
                val partida: Partida? = d.toObject(Partida::class.java)
                if(codigoBuscado == partida?.codigo){
                    existe = true
                    println("verdadero")
                }
            }
            }else{

            }
        }
        .addOnFailureListener { exception ->

        }
    return existe
}

fun obtenerNombreJ2(codigo: String, varBuscar: String): String {
    var nombre: String = ""
    print(codigo + "    " + varBuscar)
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