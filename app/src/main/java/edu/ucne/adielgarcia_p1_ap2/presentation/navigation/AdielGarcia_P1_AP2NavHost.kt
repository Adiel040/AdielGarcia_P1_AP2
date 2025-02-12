package edu.ucne.adielgarcia_p1_ap2.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import edu.ucne.adielgarcia_p1_ap2.presentation.Sistemas.DeleteSistemaScreen
import edu.ucne.adielgarcia_p1_ap2.presentation.Sistemas.EditSistemaScreen
import edu.ucne.adielgarcia_p1_ap2.presentation.Sistemas.SistemaListScreen
import edu.ucne.adielgarcia_p1_ap2.presentation.Sistemas.SistemaScreen

@Composable
fun AdielGarcia_P1_AP2NavHost(navHostController: NavHostController){
    val scope = rememberCoroutineScope()

    NavHost(navController = navHostController, startDestination = Screen.SistemaList,)
    {
        composable<Screen.SistemaList> {
            SistemaListScreen(
                scope = scope,
                onCreateSistema = {
                    navHostController.navigate(Screen.SistemaScreen(0))
                },
                onEditSistema = { sistema ->
                    navHostController.navigate(Screen.EditScreen(sistema))
                },
                onDeleteSistema = { sistema ->
                    navHostController.navigate(Screen.DeleteScreen(sistema))
                }
            )
        }

        composable<Screen.SistemaScreen> {
            val args = it.toRoute<Screen.SistemaScreen>()
            SistemaScreen(
                goBack = {
                    navHostController.navigateUp()
                }
            )
        }

        composable<Screen.EditScreen> {
            val args = it.toRoute<Screen.EditScreen>()
            EditSistemaScreen(
                sistemaId = args.sistemaId,
                goBack = {
                    navHostController.navigateUp()
                }
            )
        }

        composable<Screen.DeleteScreen> {
            val args = it.toRoute<Screen.DeleteScreen>()
            DeleteSistemaScreen(
                sistemaId = args.sistemaId,
                goBack = {
                    navHostController.navigateUp()
                }
            )
        }


    }
}