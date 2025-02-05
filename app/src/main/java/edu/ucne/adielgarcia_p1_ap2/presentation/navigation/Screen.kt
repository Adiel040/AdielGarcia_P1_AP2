package edu.ucne.adielgarcia_p1_ap2.presentation.navigation

import kotlinx.serialization.Serializable

sealed class Screen {
    @Serializable
    data object EntityList : Screen()

    @Serializable
    data class EntityScreen(val entityId: Int): Screen()
}