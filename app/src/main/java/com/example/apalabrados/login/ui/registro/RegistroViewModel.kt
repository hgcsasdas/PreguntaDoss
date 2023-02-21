package com.example.apalabrados.login.ui.registro

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.delay

class RegistroViewModel : ViewModel() {

    private val _email = MutableLiveData<String>()
    val email : LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    val password : LiveData<String> = _password

    private val _usuario = MutableLiveData<String>()
    val usuario : LiveData<String> = _usuario

    private val _resgistroEnable = MutableLiveData<Boolean>()
    val registroEnable : LiveData<Boolean> = _resgistroEnable

    private val _isLoadingR = MutableLiveData<Boolean>()
    val isLoadingR : LiveData<Boolean> = _isLoadingR



    fun onRegistroChanged(usuario: String, email: String, password: String) {

        _usuario.value = usuario
        _email.value = email
        _password.value = password
        _resgistroEnable.value = isValidUser(usuario) && isValidEmail(email) && isValidPassword(password)
    }


    suspend fun onRegistroSelected() {
        _isLoadingR.value = true

        delay(4000)
        _isLoadingR.value = false
    }

    private fun isValidUser(usuario: String): Boolean = usuario.length>4
    private fun isValidPassword(password: String): Boolean = password.length > 6
    private fun isValidEmail(email: String): Boolean = Patterns.EMAIL_ADDRESS.matcher(email).matches()

    fun limpiarCampos(){
        _usuario.value = ""
        _email.value = ""
        _password.value = ""
    }

}