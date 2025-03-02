package com.natxo.pruebafirebase

import android.util.Log
import android.util.Patterns
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

@Composable
fun SignUpScreen() {
    val auth = Firebase.auth
    val spacer = Modifier.padding(8.dp)
    var user by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var validate by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        OutlinedTextField(
            modifier = spacer,
            onValueChange = { user = it; validate = EmailValidate(it) },
            value = user,
            placeholder = { Text("Email") },
            label = { Text("Email") }
        )

        OutlinedTextField(
            modifier = spacer,
            onValueChange = { password = it },
            value = password,
            placeholder = { Text("Password") },//Hint
            label = { Text("Password") }
        )

        Button(
            modifier = spacer,
            onClick = { InsertUser(auth, user, password) },
        )
        {
            Text("Register")
        }

    }
}

fun EmailValidate(user: String): Boolean {
    //Forma de validar un email en android
    return Patterns.EMAIL_ADDRESS.matcher(user).matches() //&& TODO validacion de password
}

fun InsertUser(auth: FirebaseAuth, user: String, password: String) {
    EmailValidate(user)
    auth.createUserWithEmailAndPassword(user, password)
        .addOnCompleteListener {
            if (it.isSuccessful) {
                Log.i("Registro", "Usuario insertado correctamente")
            } else {
                Log.i("Registro", "Registro fallido")
            }
        }
}