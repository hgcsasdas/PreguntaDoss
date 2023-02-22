package com.example.apalabrados.login.ui.loginP

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.apalabrados.model.Usuario
import kotlinx.coroutines.delay

class LoginViewModel : ViewModel(){

    private val _usuario = MutableLiveData<String>()
    val usuario : LiveData<String> = _usuario

    private val _password = MutableLiveData<String>()
    val password : LiveData<String> = _password

    private val _loginEnable = MutableLiveData<Boolean>()
    val loginEnable : LiveData<Boolean> = _loginEnable

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading


    fun onLoginChanged(usuario: String, password: String) {

        _usuario.value = usuario
        _password.value = password
        _loginEnable.value = isValidUsuario(usuario) && isValidPassword(password)
    }

    private fun isValidPassword(password: String): Boolean = password.length > 6
    private fun isValidUsuario(usuario: String): Boolean = usuario.length>=6


    suspend fun onLoginSelected() {
        _isLoading.value = true

        delay(4000)
        _isLoading.value = false
    }

    fun limpiarCamposL(){
        _usuario.value = ""
        _password.value = ""
    }

}