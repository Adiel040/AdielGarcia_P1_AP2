package edu.ucne.adielgarcia_p1_ap2.presentation.Sistema

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.adielgarcia_p1_ap2.data.local.entities.SistemaEntity
import edu.ucne.adielgarcia_p1_ap2.data.repository.SistemaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SistemaViewModel @Inject constructor(
    private val sistemaRepository: SistemaRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState get() = _uiState.asStateFlow()

    init {
        getMetas()
    }
    fun save(goBack: () -> Unit) {
        viewModelScope.launch {
            val state = _uiState.value



            when {
                state.nombre.isBlank() -> {
                    _uiState.update {
                        it.copy(errorMessage = "La descripción no puede estar vacía")
                    }
                }


                else -> {
                    sistemaRepository.save(state.toEntity())
                    _uiState.update {
                        it.copy(
                            errorMessage = null
                        )
                    }
                    goBack()
                }
            }
        }
    }

    fun nuevo(){
        _uiState.update {
            it.copy(
                sistemaId = null,
                nombre = "",

            )
        }
    }
    fun select(sistemaId: Int){
        viewModelScope.launch {
            if(sistemaId > 0){
                val sistema = sistemaRepository.find(sistemaId)
                _uiState.update {
                    it.copy(
                        sistemaId = sistema?.SistemaId,
                        nombre = sistema?.Nombre ?: "",
                        errorMessage = null
                    )
                }
            }
        }
    }
    fun delete(goBack: () -> Unit){
        viewModelScope.launch {
            try{
                sistemaRepository.delete(_uiState.value.toEntity())
                _uiState.update {
                    it.copy(
                        errorMessage = null
                    )
                }
                goBack()
            }catch (e: Exception){
                _uiState.update {
                    it.copy(
                        errorMessage = "Error al eliminar la meta"
                    )
                }
            }
        }
    }
    fun getMetas() {
        viewModelScope.launch {
            sistemaRepository.getAll().collect() { sistemas ->
                _uiState.update {
                    it.copy(sistemas = sistemas)
                }
            }
        }
    }

    fun onNombreChange(nombre: String){
        _uiState.update {
            it.copy(
                nombre = nombre
            )
        }
    }

    data class UiState(
        val sistemaId: Int? = null,
        val nombre: String = "",
        val errorMessage: String? = null,
        val sistemas: List<SistemaEntity> = emptyList()
    )

    fun UiState.toEntity() = SistemaEntity(
        SistemaId=sistemaId,
        Nombre = nombre
    )
}
