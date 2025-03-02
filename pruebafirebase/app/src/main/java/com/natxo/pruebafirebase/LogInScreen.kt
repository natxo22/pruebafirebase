package com.natxo.pruebafirebase

import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

@Composable
fun LogInScreen(
    auth: FirebaseAuth,
    navigateToSingUp: () -> Unit = {},
    navigateToHome: () -> Unit = {}
){
//    val context = LocalContext.current
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
            onValueChange = { user = it;},
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

        Row {
            Button(
                modifier = spacer,
                onClick = {
//                    auth.signInWithEmailAndPassword(user, password).addOnCompleteListener{
//                        if(it.isSuccessful){
//                            navigateToHome()
//                        }
//                    }
                }
            )
            {
                Text("LogIn")
            }

            Button(
                modifier = spacer,
                onClick = { navigateToSingUp()},
            )
            {
                Text("Register")
            }
        }
    }
}

