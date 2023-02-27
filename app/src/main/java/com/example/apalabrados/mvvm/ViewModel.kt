package com.example.apalabrados.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


open class ViewModel : ViewModel() {

    private val _pregunta = MutableLiveData<String>()
    val pregunta: LiveData<String> = _pregunta

    private val _respuesta1 = MutableLiveData<String>()
    val respuesta1: LiveData<String> = _respuesta1

    private val _respuesta2 = MutableLiveData<String>()
    val respuesta2: LiveData<String> = _respuesta2

    private val _respuesta3 = MutableLiveData<String>()
    val respuesta3: LiveData<String> = _respuesta3



    private val _campo = MutableLiveData<String>()
    val campo: LiveData<String> = _campo

    private val _respuesta = MutableLiveData<String>()
    val respuesta: LiveData<String> = _respuesta


    private val _selectedIndex = MutableLiveData<Int>()
    val selectedIndex: LiveData<Int> = _selectedIndex

    fun indexUpdate(
        index: Int
    ){
        _selectedIndex.value = index
    }

    fun onCompletedFields(
        pregunta: String,
        respuesta1: String,
        respuesta2: String,
        respuesta3: String,
    ) {
        _pregunta.value = pregunta
        _respuesta1.value = respuesta1
        _respuesta2.value = respuesta2
        _respuesta3.value = respuesta3
    }
    fun campoSelected(
        campo: String
    ) {
        _campo.value = campo
    }

    fun respuestaSelected(
        respuesta: String
    ) {
        _respuesta.value = respuesta
    }
    fun limpiarCampos() {
        _campo.value = ""
        _pregunta.value = ""
        _respuesta1.value = ""
        _respuesta2.value = ""
        _respuesta3.value = ""
        _respuesta.value = ""
    }

    /*PARTIDA CRUD MVVM*/

    private val _codigoSala = MutableLiveData<String>()
    val codigoSala: LiveData<String> = _codigoSala

    fun rellenarCodigoSala(
        campo: String
    ){
        _codigoSala.value = campo
    }
    fun limpiarCodigoSala(){
        _codigoSala.value = ""
    }
    /* UNIRSE CON CODIGO MVVM*/

    private val _codigoSalaUnirse = MutableLiveData<String>()
    val codigoSalaUnirse: LiveData<String> = _codigoSalaUnirse

    fun rellenarCodigoSalaUnirse(
        campo: String
    ){
        _codigoSalaUnirse.value = campo
    }
    fun limpiarCodigoSalaUnirse(){
        _codigoSalaUnirse.value = ""
    }

    /* SALA DE ESPERA MVVM */

    private val _jugador1 = MutableLiveData<String>()
    val jugador1: LiveData<String> = _jugador1
    private val _jugador2 = MutableLiveData<String>()
    val jugador2: LiveData<String> = _jugador2

    private val _subturno = MutableLiveData<Int>()
    val subturno: LiveData<Int> = _subturno

    fun rellenarDatosSalaDeEspera(
        jugador1: String,
        jugador2: String,
        subturno: Int,
    ){
        _jugador1.value = jugador1
        _jugador2.value = jugador2
        _subturno.value = subturno


    }

}