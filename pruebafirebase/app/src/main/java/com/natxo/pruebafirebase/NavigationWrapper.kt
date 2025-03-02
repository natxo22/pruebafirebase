package com.natxo.pruebafirebase

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.google.firebase.auth.FirebaseAuth

@Composable
fun NavigationWrapper(navHostController: NavHostController, auth: FirebaseAuth) {
    NavHost(navController = navHostController, startDestination = "LogIn"){
        composable("LogIn"){
            LogInScreen(auth,
                navigateToHome = {navHostController.navigate("Home")},
                navigateToSingUp = {navHostController.navigate("SignUp")}

            )
        }
        composable("SignUp"){
            SignUpScreen()
        }
        composable("Home"){
            HomeScreen()
        }
    }
}