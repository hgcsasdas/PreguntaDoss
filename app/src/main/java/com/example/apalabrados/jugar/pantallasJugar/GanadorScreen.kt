package com.example.apalabrados.jugar.pantallasJugar

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.apalabrados.helpers.BottomBar
import com.example.apalabrados.mvvm.ViewModel
import com.example.apalabrados.navegacion.PantallasApp
import com.example.apalabrados.ui.theme.AzulClarito
import com.example.apalabrados.ui.theme.AzulFondo

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun GanadorScreen(navController: NavController, ViewModel: ViewModel, nombreGanador: String?){

    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,

        bottomBar = { BottomBar(navController, ViewModel) }
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(AzulFondo),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center

            ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp)
                    .padding(start = 8.dp, end = 8.dp),
                border = BorderStroke(1.dp, Color.White),

                ) {
                Text(
                    text = "Enhorabuena",
                    Modifier.background(AzulClarito).padding(top = 10.dp),
                    textAlign = TextAlign.Center,
                    )
            }
            Spacer(modifier = Modifier.padding(10.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp)
                    .padding(start = 8.dp, end = 8.dp),
            border = BorderStroke(1.dp, Color.White),
            ) {
                Text(text = "Ganador: $nombreGanador",
                    Modifier.background(AzulClarito).padding(top = 10.dp),
                    textAlign = TextAlign.Center
                    )
            }
            Spacer(modifier = Modifier.padding(10.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(65.dp)
                    .padding(start = 8.dp, end = 8.dp),
                border = BorderStroke(1.dp, Color.White),
            ) {
                Text(text = "Para consultar el numero de partidas ganadas pulse aqui",
                    Modifier.background(AzulClarito).padding(top = 10.dp),
                    textAlign = TextAlign.Center,

                    )
            }
            Spacer(modifier = Modifier.padding(10.dp))
            Button(
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red),
                onClick = {
                    navController.navigate(PantallasApp.PerfilScreen.route)
                }) {
                Text(text = "Pulse aquí")
            }
        }
    }
}