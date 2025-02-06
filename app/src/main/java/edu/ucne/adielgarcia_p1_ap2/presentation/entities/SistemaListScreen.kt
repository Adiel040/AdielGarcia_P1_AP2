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
import kotlinx.coroutines.launch
import kotlinx. coroutines. CoroutineScope


data class Sistema(val sistemaId: Int, val nombre: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SistemaListScreen(
    sistemaList: List<Sistema>,
    onBackClick: () -> Unit,
    onAddClick: () -> Unit,
    editarSistema: (Int) -> Unit,
    eliminarSistema: (Sistema) -> Unit
) {
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Lista de Sistemas") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Volver")
                    }
                }
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
                items(sistemaList) { sistema ->
                    SistemaRow(sistema, editarSistema, eliminarSistema, scope)
                }
            }
        }
    }
}

@Composable
private fun SistemaRow(
    sistema: Sistema,
    editarSistema: (Int) -> Unit,
    eliminarSistema: (Sistema) -> Unit,
    scope: CoroutineScope
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { editarSistema(sistema.sistemaId) }
            .padding(8.dp)
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = sistema.sistemaId.toString(),
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            modifier = Modifier.weight(3f),
            text = sistema.nombre,
            style = MaterialTheme.typography.bodyLarge
        )
        IconButton(
            onClick = { scope.launch { eliminarSistema(sistema) } },
            modifier = Modifier.padding(start = 8.dp)
        ) {
            Icon(imageVector = Icons.Default.Delete, contentDescription = "Eliminar Sistema")
        }
    }
    Divider(modifier = Modifier.padding(vertical = 4.dp))
}
