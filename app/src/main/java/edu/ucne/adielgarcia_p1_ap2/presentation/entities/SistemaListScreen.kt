package edu.ucne.adielgarcia_p1_ap2.presentation.entities

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import edu.ucne.adielgarcia_p1_ap2.data.local.entities.SistemaEntity
import edu.ucne.adielgarcia_p1_ap2.presentation.Sistema.SistemaViewModel
import kotlinx.coroutines.launch
import kotlinx. coroutines. CoroutineScope


data class Sistema(val sistemaId: Int, val nombre: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SistemaListScreen(
    onAddClick: () -> Unit,
    editarSistema: (Int) -> Unit,
    viewModel: SistemaViewModel = hiltViewModel()
) {
    val scope = rememberCoroutineScope()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    SistemaListBodyScreen(
        uiState = uiState,
        onAddClick = onAddClick,
        editarSistema = editarSistema,
        eliminarSistema = { sistemaId ->
            viewModel.select(sistemaId)
            viewModel.delete { }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SistemaListBodyScreen(
    uiState: SistemaViewModel.UiState,
    onAddClick: () -> Unit,
    editarSistema: (Int) -> Unit,
    eliminarSistema: (Int) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Lista de Sistemas") },
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddClick) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Agregar Sistema")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Listado de Sistemas",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(8.dp)
            )
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(uiState.sistemas) { sistema ->
                    SistemaRow(sistema, editarSistema, eliminarSistema)
                }
            }
        }
    }
}

@Composable
private fun SistemaRow(
    sistema: SistemaEntity,
    editarSistema: (Int) -> Unit,
    eliminarSistema: (Int) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { editarSistema(sistema.SistemaId ?: 0) }
            .padding(8.dp)
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = sistema.SistemaId.toString(),
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            modifier = Modifier.weight(3f),
            text = sistema.Nombre,
            style = MaterialTheme.typography.bodyLarge
        )
        IconButton(
            onClick = {   },
            modifier = Modifier.padding(start = 8.dp)
        ) {
            Icon(imageVector = Icons.Default.Delete, contentDescription = "Eliminar Sistema")
        }
    }
    Divider(modifier = Modifier.padding(vertical = 4.dp))
}
