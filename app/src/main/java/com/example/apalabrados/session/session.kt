package com.example.apalabrados.session

import android.content.Context
import android.content.SharedPreferences

class session (context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("MyApp", Context.MODE_PRIVATE)
    private var logued = false
    fun startSession(nick: String, password: String, email: String) {
        sharedPreferences.edit().apply {
            putString("nick", nick)
            putString("email", email)
            putString("password", password)
            apply()
        }
    }

    fun loguear(){
        logued = true
    }

    fun isLoggedIn(): Boolean {
        return sharedPreferences.getString("nick", null) != null
    }

    fun getNick(): String? {
        return sharedPreferences.getString("nick", null)
    }

    fun getEmail(): String? {
        return sharedPreferences.getString("email", null)
    }

    fun getPassword(): String? {
        return sharedPreferences.getString("password", null)
    }

    fun endSession() {
        sharedPreferences.edit().apply {
            remove("nick")
            remove("email")
            remove("password")
            apply()
        }
    }
}