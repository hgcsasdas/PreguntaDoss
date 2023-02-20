package com.example.apalabrados.login.ui.registro

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay

class RegistroViewModel : ViewModel() {

    private val _email = MutableLiveData<String>()
    val email : LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    val password : LiveData<String> = _password

    private val _resgistroEnable = MutableLiveData<Boolean>()
    val registroEnable : LiveData<Boolean> = _resgistroEnable

    private val _isLoadingR = MutableLiveData<Boolean>()
    val isLoadingR : LiveData<Boolean> = _isLoadingR



    fun onRegistroChanged(email: String, password: String) {

        _email.value = email
        _password.value = password
        _resgistroEnable.value = isValidEmail(email) && isValidPassword(password)
    }

    private fun isValidPassword(password: String): Boolean = password.length > 6
    private fun isValidEmail(email: String): Boolean = Patterns.EMAIL_ADDRESS.matcher(email).matches()


    suspend fun onRegistroSelected() {
        _isLoadingR.value = true

        delay(4000)
        _isLoadingR.value = false
    }


}