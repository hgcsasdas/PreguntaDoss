package com.example.apalabrados.pantallas

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.apalabrados.viewModel.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AniadirPreguntas(navController: NavController, ViewModel: ViewModel){
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold (
        scaffoldState = scaffoldState,
        topBar = {
            TopBarGeneral(
                onMenuButtonClick = {
                    scope.launch {
                        scaffoldState.drawerState.open()
                    }
                },
                onActionButtonClick = { /* */ }
            )
        },
        drawerContent = { // Contenido del drawer
            DrawerContent { // Cerrar drawer
                scope.launch { scaffoldState.drawerState.close() }
            }
        }

    ){
        aniadirPreguntasContent(ViewModel)
    }
}


@Composable
fun aniadirPreguntasContent(ViewModel:ViewModel){
    val db = FirebaseFirestore.getInstance()

    var nombre_coleccion = "preguntas"

    val campo by ViewModel.campo.observeAsState(initial = "")
    val pregunta by ViewModel.pregunta.observeAsState(initial = "")
    val respuesta1 by ViewModel.respuesta1.observeAsState(initial = "")
    val respuesta2 by ViewModel.respuesta2.observeAsState(initial = "")
    val respuesta3 by ViewModel.respuesta3.observeAsState(initial = "")
    val correcta by ViewModel.correcta.observeAsState(initial = "")

    //var genero_dragon = remember { mutableStateOf("") }

    val gradientColors = listOf(Color(0xFF413846), Color(0xFF807C7C))
    val roundCornerShape = RoundedCornerShape(topEnd = 30.dp, bottomStart = 30.dp)



    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.horizontalGradient(colors = gradientColors),
            )
        ,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "Campo",
            fontWeight = FontWeight.ExtraBold
        )

        Spacer(modifier = Modifier.size(20.dp))

        OutlinedTextField(
            value = campo ,
            onValueChange ={
                ViewModel.onCompletedFields(
                    campo = it,
                    pregunta = pregunta,
                    respuesta1 = respuesta1,
                    respuesta2 = respuesta2,
                    respuesta3 = respuesta3,
                    correcta = correcta
                )
            },
            label = { Text("Campo") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
        )

        Spacer(modifier = Modifier.size(5.dp))

        OutlinedTextField(
            value = pregunta,
            onValueChange ={
                ViewModel.onCompletedFields(
                    campo = campo,
                    pregunta = it,
                    respuesta1 = respuesta1,
                    respuesta2 = respuesta2,
                    respuesta3 = respuesta3,
                    correcta = correcta
                )
            },
            label = { Text("Pregunta") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
        )

        Spacer(modifier = Modifier.size(5.dp))

        OutlinedTextField(
            value = respuesta1 ,
            onValueChange ={
                ViewModel.onCompletedFields(
                    campo = campo,
                    pregunta = pregunta,
                    respuesta1 = it,
                    respuesta2 = respuesta2,
                    respuesta3 = respuesta3,
                    correcta = correcta
                )
            },
            label = { Text("Respuesta 1") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
        )

        Spacer(modifier = Modifier.size(5.dp))


        OutlinedTextField(
            value = respuesta2 ,
            onValueChange ={
                ViewModel.onCompletedFields(
                    campo = campo,
                    pregunta = pregunta,
                    respuesta1 = respuesta1,
                    respuesta2 = it,
                    respuesta3 = respuesta3,
                    correcta = correcta
                )
            },
            label = { Text("Respuesta 2") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
        )

        Spacer(modifier = Modifier.size(5.dp))

        OutlinedTextField(
            value = respuesta3 ,
            onValueChange ={
                ViewModel.onCompletedFields(
                    campo = campo,
                    pregunta = pregunta,
                    respuesta1 = respuesta1,
                    respuesta2 = respuesta2,
                    respuesta3 = it,
                    correcta = correcta
                )
            },
            label = { Text("Respuesta 3") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
        )

        Spacer(modifier = Modifier.size(5.dp))
        val context = LocalContext.current

        OutlinedTextField(
            value = correcta,
            onValueChange ={
                ViewModel.onCompletedFields(
                    campo = campo,
                    pregunta = pregunta,
                    respuesta1 = respuesta1,
                    respuesta2 = respuesta2,
                    respuesta3 = respuesta3,
                    correcta = it
                )
            },
            label = { Text("Respuesta correcta") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
        )

        Spacer(modifier = Modifier.padding(16.dp))

        Column(
            Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = {
                if (campo.isEmpty()) {
                    Toast
                        .makeText(context, "Nombre en blanco", Toast.LENGTH_LONG)
                        .show()
                } else if (pregunta.isEmpty()) {
                    Toast
                        .makeText(context, "Raza en blanco", Toast.LENGTH_LONG)
                        .show()
                } else if (respuesta1.isEmpty()) {
                    Toast
                        .makeText(context, "Color en blanco", Toast.LENGTH_LONG)
                        .show()
                } else if (respuesta2.isEmpty()) {
                    Toast
                        .makeText(context, "Peso en blanco", Toast.LENGTH_LONG)
                        .show()
                } else if (respuesta3.isEmpty()) {
                    Toast
                        .makeText(context, "Genero en blanco", Toast.LENGTH_LONG)
                        .show()
                } else {
                    db
                        .collection(campo)
                        .document()
                        .set(mapOf(
                            "pregunta" to pregunta,
                            "respuesta1" to respuesta1,
                            "respuesta2" to respuesta2,
                            "respuesta3" to respuesta3,
                            "correcta" to correcta
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
    }
}