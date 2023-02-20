package com.example.apalabrados

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.example.apalabrados.login.ui.loginP.LoginViewModel
import com.example.apalabrados.navegacion.NavegacionApp
import com.example.apalabrados.ui.theme.ApalabradosTheme
import com.example.apalabrados.viewModel.ViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ApalabradosTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    NavegacionApp(ViewModel(), LoginViewModel())
                }
            }
        }
    }
}
