package com.example.apalabrados.pantallas

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
fun AniadirPreguntas(navController: NavController, ViewModel: ViewModel) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        bottomBar = {BottomBar(navController, ViewModel)}
    ) {
        aniadirPreguntasContent(ViewModel)
    }
}


@Composable
fun aniadirPreguntasContent(ViewModel: ViewModel) {
    val db = FirebaseFirestore.getInstance()

    val campo by ViewModel.campo.observeAsState(initial = "")
    val respuesta by ViewModel.respuesta.observeAsState(initial = "")


    val pregunta by ViewModel.pregunta.observeAsState(initial = "")
    val respuesta1 by ViewModel.respuesta1.observeAsState(initial = "")
    val respuesta2 by ViewModel.respuesta2.observeAsState(initial = "")
    val respuesta3 by ViewModel.respuesta3.observeAsState(initial = "")

    val gradientColors = listOf(Color(0xFF413846), Color(0xFF807C7C))


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.horizontalGradient(colors = gradientColors),
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Formulario añadir preguntas",
            fontWeight = FontWeight.ExtraBold
        )

        Spacer(modifier = Modifier.size(20.dp))

        SeleccionPregunta(ViewModel)

        Spacer(modifier = Modifier.size(5.dp))

        OutlinedTextField(
            value = pregunta,
            onValueChange = {
                ViewModel.onCompletedFields(
                    pregunta = it,
                    respuesta1 = respuesta1,
                    respuesta2 = respuesta2,
                    respuesta3 = respuesta3,
                )
            },
            label = { Text("Pregunta") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
        )

        Spacer(modifier = Modifier.size(5.dp))

        OutlinedTextField(
            value = respuesta1,
            onValueChange = {
                ViewModel.onCompletedFields(
                    pregunta = pregunta,
                    respuesta1 = it,
                    respuesta2 = respuesta2,
                    respuesta3 = respuesta3,
                )
            },
            label = { Text("Respuesta 1") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
        )

        Spacer(modifier = Modifier.size(5.dp))


        OutlinedTextField(
            value = respuesta2,
            onValueChange = {
                ViewModel.onCompletedFields(
                    pregunta = pregunta,
                    respuesta1 = respuesta1,
                    respuesta2 = it,
                    respuesta3 = respuesta3,
                )
            },
            label = { Text("Respuesta 2") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
        )

        Spacer(modifier = Modifier.size(5.dp))

        OutlinedTextField(
            value = respuesta3,
            onValueChange = {
                ViewModel.onCompletedFields(
                    pregunta = pregunta,
                    respuesta1 = respuesta1,
                    respuesta2 = respuesta2,
                    respuesta3 = it,
                )
            },
            label = { Text("Respuesta 3") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
        )

        Spacer(modifier = Modifier.size(5.dp))
        val context = LocalContext.current

        SeleccionRespuesta(ViewModel)

        Spacer(modifier = Modifier.padding(16.dp))

        Column(
            Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
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
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SeleccionPregunta(ViewModel: ViewModel) {
    val types = listOf("","Java", "JavaScript", "HTML", "CSS")
    val default = 0

    var expanded by remember { mutableStateOf(false) }
    var selectedType by remember { mutableStateOf(types[default]) } // (1)

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded // (2)
        },
        modifier = Modifier.width(150.dp)
    ) {
        TextField(
            readOnly = true, // (3)
            value = selectedType, // (4)
            onValueChange = { },
            label = { Text("Campo") },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon( // (5)
                    expanded = expanded
                )
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors()
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }
        ) {
            types.forEach { selectionOption ->
                DropdownMenuItem(
                    onClick = {
                        selectedType = selectionOption
                        expanded = false
                        ViewModel.campoSelected(
                            campo = selectedType
                        )
                    }
                ) {
                    Text(text = selectionOption)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SeleccionRespuesta(ViewModel: ViewModel) {
    val responseTypes = listOf("","respuesta1", "respuesta2", "respuesta3")
    val default = 0

    var expandedR by remember { mutableStateOf(false) }
    var selectedTypeR by remember { mutableStateOf(responseTypes[default]) } // (1)


    ExposedDropdownMenuBox(
        expanded = expandedR,
        onExpandedChange = {
            expandedR = !expandedR // (2)
        },
        modifier = Modifier.width(150.dp)
    ) {
        TextField(
            readOnly = true, // (3)
            value = selectedTypeR, // (4)
            onValueChange = { },
            label = { Text("Respuesta correcta") },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon( // (5)
                    expanded = expandedR
                )
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors()
        )
        ExposedDropdownMenu(
            expanded = expandedR,
            onDismissRequest = {
                expandedR = false
            }
        ) {
            responseTypes.forEach { selectionOption ->
                DropdownMenuItem(
                    onClick = {
                        selectedTypeR = selectionOption
                        expandedR = false
                        ViewModel.respuestaSelected(
                            respuesta = selectedTypeR
                        )
                    }
                ) {
                    Text(text = selectionOption)
                }
            }
        }
    }
}