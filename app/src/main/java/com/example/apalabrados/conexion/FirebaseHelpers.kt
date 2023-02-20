package com.example.apalabrados.conexion

import com.google.firebase.firestore.FirebaseFirestore

val db = FirebaseFirestore.getInstance()

fun buscarEnFirebase(codigoBuscado: String): Boolean {

    val docRef = db.collection("partida").document("documentId")
    var existe = false
    docRef.get()
        .addOnSuccessListener { documentSnapshot ->
            if (documentSnapshot.exists()) {
                val codigo = documentSnapshot.getString("codigo")
                if (codigo != null && codigo == codigoBuscado) {
                    // El código coincide, devolver false
                    existe = true
                    print(existe)
                }
            }
        }
        .addOnFailureListener { exception ->
            // Se produjo un error al buscar en Firebase
            print("No se puc mas")
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
