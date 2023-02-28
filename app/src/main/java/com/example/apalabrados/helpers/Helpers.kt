package com.example.apalabrados.helpers

import com.example.apalabrados.model.Pregunta


fun elegirTresPreguntasAleatorias(preguntas: List<Pregunta>): List<Pregunta> {
    // Verificar que hay al menos 3 preguntas en la lista
    require(preguntas.size >= 3) { "La lista debe contener al menos 3 preguntas" }

    // Barajar aleatoriamente las preguntas
    val preguntasBarajadas = preguntas.shuffled()

    // Tomar las primeras tres preguntas barajadas
    return preguntasBarajadas.take(3)
}