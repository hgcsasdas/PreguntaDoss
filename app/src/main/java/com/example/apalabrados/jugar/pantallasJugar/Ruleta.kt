package com.example.apalabrados.jugar.pantallasJugar

import android.annotation.SuppressLint
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.apalabrados.R
import com.example.apalabrados.mvvm.ViewModel
import com.example.apalabrados.pantallas.BottomBar
import com.example.apalabrados.pantallas.aniadirPreguntasContent
import kotlinx.coroutines.launch
import kotlin.random.Random


suspend fun rotatePartial(
    rotationAngle: Float,
    rotation: Animatable<Float, AnimationVector1D>,
    incrementSpeed: Int = 50
) {
    var partialRotation = 0f
    val cycle = arrayOf(5f, 10f, 15f, 20f, 25f, 30f, 35f, 40f, 45f, 50f, 55f, 60f, 65f, 70f, 75f, 80f, 85f, 90f, 95f, 100f, 105f, 110f, 115f, 120f, 125f, 130f, 135f, 140f, 145f, 150f, 155f, 160f, 165f, 170f, 175f, 180f, 185f, 190f, 195f, 200f, 205f, 210f, 215f, 220f, 225f, 230f, 235f, 240f, 245f, 250f, 255f, 260f, 265f, 270f, 275f, 280f, 285f, 290f, 295f, 300f, 305f, 310f, 315f, 320f, 325f, 330f, 335f, 340f, 345f, 350f, 355f, 360f)
    var speed = incrementSpeed
    while (partialRotation < rotationAngle) {
        for (angle in cycle) {
            partialRotation += angle
            if (partialRotation >= rotationAngle) {
                val remainingAngle = partialRotation - rotationAngle
                rotation.animateTo(
                    targetValue = rotationAngle,
                    animationSpec = tween((speed * remainingAngle).toInt(), easing = LinearEasing)
                )
                break
            } else {
                rotation.animateTo(
                    targetValue = partialRotation,
                    animationSpec = tween(speed, easing = LinearEasing)
                )
            }
        }
    }
}
@Composable
fun Roulette(navController: NavController, ViewModel: ViewModel) {
    var spin = false
    var flag by rememberSaveable {
        mutableStateOf(spin)
    }
    val rotation = remember { Animatable(0f) }
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var modifier = Modifier
            .clip(shape = CircleShape)
            .size(300.dp)
            .rotate(rotation.value)
            .clickable {

                scope.launch {
                    if (!flag) {
                        flag = !flag
                        val randomRotation: Int = Random.nextInt(100, 300)
                        val rotationAngle = randomRotation * 45f
                        println("Ha salido" + randomRotation)

                        rotatePartial(rotationAngle, rotation)
                        flag = !flag
                        println("HA SALIDOOOOOOOOOOOOO" + rotationAngle)
                    }

                }
            }

        Image(
            painter = painterResource(R.drawable.ruletaa),
            contentDescription = "",
            modifier = modifier
        )

        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .padding(top = 16.dp)
        ) {
            Text(text = "Aceptar")
        }
    }
}
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun RouletteScreen(navController: NavController, ViewModel: ViewModel){
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        bottomBar = { BottomBar(navController, ViewModel) }
    ) {
        Roulette(navController, ViewModel)
    }
}