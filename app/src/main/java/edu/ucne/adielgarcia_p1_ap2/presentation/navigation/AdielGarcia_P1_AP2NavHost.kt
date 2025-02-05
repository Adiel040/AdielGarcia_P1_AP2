package edu.ucne.adielgarcia_p1_ap2.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AdielGarcia_P1_AP2NavHost(navHostController: NavHostController){
    NavHost(
        startDestination = Screen.EntityList,
        navController = navHostController
    ) {
        composable<Screen.EntityList> {  }
        composable<Screen.EntityScreen> {  }
    }
}