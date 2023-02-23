package com.example.apalabrados.jugar.pantallasJugar

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.apalabrados.R
import com.example.apalabrados.mvvm.ViewModel
import kotlinx.coroutines.launch
import java.util.*
import kotlin.random.Random

suspend fun rotateCycles(
    numberCycles: Int,
    rotation: Animatable<Float, AnimationVector1D>,
    incrementSpeedParam: Int = 100,
    initialSpeed: Int = 0
) {
    var speed: Int = 0
    var incrementSpeed: Int = 0
    var cyclesMade: Int = 0
    val cycle = arrayOf(90f, 180f, 270f, 360f)
    if ((incrementSpeedParam < 0) && (initialSpeed / (incrementSpeedParam * -1) < numberCycles)) {
        speed = 300
        incrementSpeed = 100
    } else {
        speed = initialSpeed
        incrementSpeed = incrementSpeedParam
    }
    val smoothIncrement = (incrementSpeed / 4) - 1
    while (cyclesMade < numberCycles) {

        for (angle in cycle) {
            rotation.animateTo(
                targetValue = angle,
                animationSpec = tween(speed, easing = LinearEasing)
            )
            speed += smoothIncrement
        }

        cyclesMade++
        rotation.snapTo(0f)
    }
}

@Composable
fun Roulette(navController: NavHostController, ViewModel: ViewModel) {
    var spin = false
    var flag by rememberSaveable {
        mutableStateOf(spin)
    }
    val rotation = remember { Animatable(0f) }
    val scope = rememberCoroutineScope()


    var modifier = Modifier
        .clip(shape = CircleShape)
        .size(300.dp)
        .rotate(rotation.value)
        .clickable {

            scope.launch {
                if (!flag) {
                    flag = !flag
                    val randomRotation = Random.nextInt(3, 11)
                    println("Ha salido" + randomRotation)

                    rotateCycles(randomRotation, rotation)
                    flag = !flag
                }

            }


        }


    Image(
        painter = painterResource(R.drawable.consola
        ),
        contentDescription = "",
        modifier = modifier
    )
}





