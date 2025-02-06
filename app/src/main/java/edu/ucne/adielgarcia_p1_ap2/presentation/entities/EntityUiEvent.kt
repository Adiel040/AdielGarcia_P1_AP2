package edu.ucne.adielgarcia_p1_ap2.presentation.entities

sealed interface EntityUiEvent {
    data class EntityIdChange(val entityId: Int): EntityUiEvent
    data object Save: EntityUiEvent
    data object  Delete: EntityUiEvent
}