package com.example.apalabrados.login.ui.registro

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.apalabrados.R
import com.example.apalabrados.conexion.buscarUsuarioReference
import com.example.apalabrados.conexion.db
import com.example.apalabrados.navegacion.PantallasApp
import com.example.apalabrados.session.Session
import com.example.apalabrados.ui.theme.AzulFondo


@Composable
fun RegistroScreen(viewModel: RegistroViewModel, navController: NavController) {

    Column(
        Modifier.background(color = AzulFondo)
    ) {
        Box(
            Modifier
                .fillMaxSize()
                .padding(16.dp)
                .background(color = AzulFondo)
        ) {
            Registro(Modifier.align(Alignment.Center), viewModel, navController)
        }
    }
}

@Composable
fun Registro(modifier: Modifier, viewModel: RegistroViewModel, navController: NavController) {

    val email: String by viewModel.email.observeAsState(initial = "")
    val password: String by viewModel.password.observeAsState(initial = "")
    val usuario: String by viewModel.usuario.observeAsState(initial = "")
    val resgistroEnable: Boolean by viewModel.registroEnable.observeAsState(initial = false)
    val isLoadingP: Boolean by viewModel.isLoadingR.observeAsState(initial = false)

    if (isLoadingP) {
        Box(modifier.fillMaxSize()) {
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        }
    } else {
        Column(modifier = modifier) {
            HeaderImageR(Modifier.align(Alignment.CenterHorizontally))
            Spacer(modifier = Modifier.padding(16.dp))
            UsuarioFielR(usuario) { viewModel.onRegistroChanged(it, email, password) }
            Spacer(modifier = Modifier.padding(4.dp))
            EmailFielR(email) { viewModel.onRegistroChanged(usuario, it, password) }
            Spacer(modifier = Modifier.padding(4.dp))
            PasswordFielR(password) { viewModel.onRegistroChanged(usuario, email, it) }
            Spacer(modifier = Modifier.padding(8.dp))
            ForgotPasswordR(Modifier.align(Alignment.End))
            Spacer(modifier = Modifier.padding(16.dp))
            Row(
                modifier = Modifier.size(380.dp, 100.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                RegistroButtonR(resgistroEnable, viewModel, navController)
            }
        }

    }
}

@Composable
fun RegistroButtonR(
    loginEnable: Boolean,
    viewModel: RegistroViewModel,
    navController: NavController
) {
    val dbName = "usuarios"
    val context = LocalContext.current

    Button(
        onClick = {
            val sessionManager = Session(context)

            val dato = hashMapOf(
                "usuario" to viewModel.usuario.value,
                "email" to viewModel.email.value,
                "password" to viewModel.password.value
            )
            sessionManager.startSession(
                viewModel.usuario.value!!,
                viewModel.password.value!!,
                viewModel.email.value!!
            )
            buscarUsuarioReference(viewModel.usuario.value!!) { exists ->
                if (exists) {
                    // El usuario ya existe en la base de datos
                    Toast.makeText(
                        context,
                        "No se ha podido reistrar el usuario, por que ya existe",
                        Toast.LENGTH_LONG
                    )
                        .show()

                } else {
                    // El usuario no existe en la base de datos
                    viewModel.usuario.value?.let {
                        db.collection(dbName)
                            .document(it)
                            .set(dato)
                            .addOnSuccessListener {
                                Toast.makeText(
                                    context,
                                    "Usuario registrado correctamente",
                                    Toast.LENGTH_LONG
                                )
                                    .show()
                                viewModel.limpiarCampos()
                                sessionManager.loguear()

                                navController.navigate(PantallasApp.LoginScreen.route)
                            }
                            .addOnFailureListener {
                                Toast.makeText(
                                    context,
                                    "No se ha podido reistrar el usuario",
                                    Toast.LENGTH_LONG
                                )
                                    .show()
                            }
                    }
                }
            }

        },
        modifier = Modifier
            .width(150.dp)
            .height(55.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(0xFFFF4303),
            disabledBackgroundColor = Color(0xFFF78058),
            contentColor = Color.White,
            disabledContentColor = Color.White
        ), enabled = loginEnable
    ) {
        Text(text = "Registro")
    }
}


@Composable
fun ForgotPasswordR(modifier: Modifier) {
    Text(
        text = "Olvidaste la contraseña?",
        modifier = modifier.clickable { },
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFFFF4303)
    )
}

@Composable
fun PasswordFielR(password: String, onTextFielChanged: (String) -> Unit) {
    TextField(
        visualTransformation = PasswordVisualTransformation(),
        value = password, onValueChange = { onTextFielChanged(it) },
        placeholder = { Text(text = "Contraseña") },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color(0xFFCECECE),
            backgroundColor = Color(0xFF6F6F6F),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}

@Composable
fun EmailFielR(email: String, onTextFielChanged: (String) -> Unit) {
    TextField(
        value = email, onValueChange = { onTextFielChanged(it) },
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = "Email") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color(0xFFCECECE),
            backgroundColor = Color(0xFF6F6F6F),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}

@Composable
fun UsuarioFielR(usuario: String, onTextFielChanged: (String) -> Unit) {
    TextField(
        value = usuario, onValueChange = { onTextFielChanged(it) },
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = "Usuario") },
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color(0xFFCECECE),
            backgroundColor = Color(0xFF6F6F6F),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}

@Composable
fun HeaderImageR(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.preguntadoss),
        contentDescription = null,
        modifier = modifier
    )
}
