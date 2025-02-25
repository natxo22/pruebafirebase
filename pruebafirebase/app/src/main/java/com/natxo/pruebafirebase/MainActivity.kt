package com.natxo.pruebafirebase

import android.os.Bundle
import android.util.Log
import android.util.Patterns
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class MainActivity : ComponentActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            auth = Firebase.auth
            Registro(auth)

        }
    }
}

@Composable
fun Registro(auth: FirebaseAuth) {
    val spacer = Modifier.padding(8.dp)
    var user by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var validate by rememberSaveable { mutableStateOf(false) }

    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    )

    {

        OutlinedTextField(
            modifier = spacer,
            onValueChange = { user = it; validate = Validate(it, password) },
            value = user,
            placeholder = { Text("Email") },
            label = { Text("Email") }
        )

        OutlinedTextField(
            modifier = spacer,
            onValueChange =
            {
                password = it
                validate = Validate(user, it)

            },

            value = password,
            placeholder = { Text("Password") },//Hint
            label = { Text("Password") }
        )

        Button(
            modifier = spacer,
            onClick = { Insert(auth, user, password) },
            enabled = validate
        )
        {
            Text("Log in")
        }
    }
}

fun Validate(user: String, password: String): Boolean {
    //Forma de validar un email en android
    return Patterns.EMAIL_ADDRESS.matcher(user).matches() //&& TODO validacion de password
}

fun Insert(auth: FirebaseAuth, user: String, password: String) {
    auth.createUserWithEmailAndPassword(user, password)
        .addOnCompleteListener {
            if (it.isSuccessful) {
                Log.i("Registro", "Usuario insertado correctamente")
            } else {
                Log.i("Registro", "Registro fallido")
            }
        }
    //auth.signInWithEmailAndPassword(user, password)
}