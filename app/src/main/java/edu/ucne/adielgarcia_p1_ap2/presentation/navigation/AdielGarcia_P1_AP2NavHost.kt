package edu.ucne.adielgarcia_p1_ap2.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import edu.ucne.adielgarcia_p1_ap2.presentation.entities.SistemaListScreen
import edu.ucne.adielgarcia_p1_ap2.presentation.entities.SistemaScreen

@Composable
fun AdielGarcia_P1_AP2NavHost(navHostController: NavHostController){

    NavHost(
        startDestination = Screen.EntityList,
        navController = navHostController
    ) {
        composable<Screen.EntityList> {
            SistemaListScreen(
            onAddClick = {
                navHostController.navigate(Screen.EntityScreen(entityId = 0))
            },
            editarSistema = {
                navHostController.navigate(Screen.EntityScreen(entityId = it))
            },
            )
        }
        composable<Screen.EntityScreen> {
            val id = it.toRoute<Screen.EntityScreen>().entityId
            SistemaScreen(
                id = id,
                goBack = {
                    navHostController.popBackStack()
                }
            )
        }
    }
}