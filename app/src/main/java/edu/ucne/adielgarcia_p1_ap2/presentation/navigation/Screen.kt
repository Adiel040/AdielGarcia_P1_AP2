package edu.ucne.adielgarcia_p1_ap2.presentation.navigation

import kotlinx.serialization.Serializable

sealed class Screen {
    @Serializable
    data object SistemaList : Screen()

    @Serializable
    data class SistemaScreen(val sistemaId: Int): Screen()

    @Serializable
    data class DeleteScreen(val sistemaId: Int): Screen()

    @Serializable
    data class EditScreen(val sistemaId: Int): Screen()

}
