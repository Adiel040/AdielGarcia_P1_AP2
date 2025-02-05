package edu.ucne.adielgarcia_p1_ap2.presentation.entities

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.adielgarcia_p1_ap2.data.local.entities.Entity
import edu.ucne.adielgarcia_p1_ap2.data.repository.EntityRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class EntityViewModel @Inject constructor(
    private val entityRepository: EntityRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(EntityUiState())
    val uiState = _uiState.asStateFlow()

    init{

    }
}

fun EntityUiState.toEntity() = Entity(
    entityId = entityId
)