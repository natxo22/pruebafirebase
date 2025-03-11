package com.natxo.pruebafirebase

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.natxo.pruebafirebase.presentation.home.HomeScreen
import com.natxo.pruebafirebase.presentation.initial.InitialScreen
import com.natxo.pruebafirebase.presentation.login.LoginScreen
import com.natxo.pruebafirebase.presentation.signup.SignUpScreen
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.natxo.pruebafirebase.presentation.home.AddScreen
import com.natxo.pruebafirebase.presentation.model.Relic
import com.natxo.pruebafirebase.presentation.profile.ProfileScreen
import com.natxo.pruebafirebase.presentation.relic.RelicScreen

@Composable
fun NavigationWrapper(
    navHostController: NavHostController,
    auth: FirebaseAuth,
) {

    val startDestination = "home"

    NavHost(navController = navHostController, startDestination = startDestination) {
        composable("initial") {
            InitialScreen(navigateToLogin = { navHostController.navigate("logIn") },
                navigateToSignUp = { navHostController.navigate("signUp") })
        }
        composable("logIn") {
            LoginScreen(auth,
                navigateToHome = { navHostController.navigate("home") },
                navigateToInitial = { navHostController.navigate("initial") })
        }
        composable("signUp") {
            SignUpScreen(auth,
                navigateToHome = { navHostController.navigate("home") },
                navigateToInitial = { navHostController.navigate("initial") }
            )
        }
        composable("home") {
            HomeScreen(
                navigateToRelic = { navHostController.navigate("relic") },
                navigateToProfile = { navHostController.navigate("profile") },
                navigateToAdd = { navHostController.navigate("add")}
                )
        }
        composable("relic") {
            RelicScreen(navigateToHome = { navHostController.navigate("home") })
        }
        composable("profile") {
            ProfileScreen(auth,
                navigateToInitial = { navHostController.navigate("initial") },
                navigateToHome = { navHostController.navigate("home") }
                )
        }
        composable("add") {
            AddScreen(navigateToHome = { navHostController.navigate("home") })
        }
    }
}
