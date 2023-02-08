package com.example.apalabrados.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class ViewModel : ViewModel() {

    private val _campo = MutableLiveData<String>()
    val campo: LiveData<String> = _campo

    private val _pregunta = MutableLiveData<String>()
    val pregunta: LiveData<String> = _pregunta

    private val _respuesta1 = MutableLiveData<String>()
    val respuesta1: LiveData<String> = _respuesta1

    private val _respuesta2 = MutableLiveData<String>()
    val respuesta2: LiveData<String> = _respuesta2

    private val _respuesta3 = MutableLiveData<String>()
    val respuesta3: LiveData<String> = _respuesta3

    private val _correcta = MutableLiveData<String>()
    val correcta: LiveData<String> = _correcta
    fun onCompletedFields(
        campo: String,
        pregunta: String,
        respuesta1: String,
        respuesta2: String,
        respuesta3: String,
        correcta: String
    ) {
        _campo.value = campo
        _pregunta.value = pregunta
        _respuesta1.value = respuesta1
        _respuesta2.value = respuesta2
        _respuesta3.value = respuesta3
        _correcta.value = correcta
    }

    fun limpiarCampos() {
        _campo.value = ""
        _pregunta.value = ""
        _respuesta1.value = ""
        _respuesta2.value = ""
        _respuesta3.value = ""
        _correcta.value = ""
    }
}