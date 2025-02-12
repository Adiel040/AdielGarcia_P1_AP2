package edu.ucne.adielgarcia_p1_ap2.presentation.Sistemas

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import edu.ucne.adielgarcia_p1_ap2.data.local.entities.SistemaEntity
import edu.ucne.adielgarcia_p1_ap2.presentation.Sistema.SistemaViewModel
import kotlinx.coroutines.CoroutineScope



@Composable
fun SistemaListScreen(
    scope: CoroutineScope,
    viewModel: SistemaViewModel = hiltViewModel(),
    onCreateSistema: () -> Unit,
    onEditSistema: (Int) -> Unit,
    onDeleteSistema: (Int) -> Unit

) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    SistemaListBodyScreen(
        scope = scope,
        uiState = uiState,
        createSistemas = onCreateSistema,
        onDeleteSistema = onDeleteSistema,
        onEditSistema = onEditSistema
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SistemaListBodyScreen(
    uiState: SistemaViewModel.UiState,
    scope: CoroutineScope,
    createSistemas: () -> Unit,
    onDeleteSistema: (Int) -> Unit,
    onEditSistema: (Int) -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Lista de Sistemas",
                        style = TextStyle(
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFF6200EE)
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = createSistemas,
                modifier = Modifier.padding(16.dp),
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = Color.White
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Añadir Sistema")
            }
        }

    ) { paddingValues ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(uiState.sistemas)
                {sistema ->
                    SistemaRow(
                        sistema,
                        onEditSistema,
                        onDeleteSistema
                    )

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
    var expanded by remember { mutableStateOf(false) }
    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(MaterialTheme.colorScheme.surfaceVariant),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Column(
                modifier = Modifier.weight(5f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Nombre: ${sistema.Nombre}",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface

                    )
                )
                Text(
                    text = "Precio: ${sistema.Precio}",
                    style = TextStyle(
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.primary
                    )
                )
            }
            IconButton(
                onClick = { expanded = !expanded },
                modifier = Modifier.weight(1f)
            ){
                Icon(Icons.Filled.MoreVert, contentDescription = "Más opciones")

            }

            DropdownMenu(
                expanded = expanded, onDismissRequest = { expanded = false},
                modifier = Modifier.background(MaterialTheme.colorScheme.surface),
                offset = DpOffset(x= (220).dp, y = (0).dp)
            ) {
                DropdownMenuItem(
                    text = { Text("Editar") },
                    leadingIcon = { Icon(Icons.Filled.Edit, contentDescription = "Editar")},
                    onClick = {
                        expanded = false
                        editarSistema(sistema.SistemaId!!)
                    }
                )
                DropdownMenuItem(
                    text = { Text("Eliminar") },
                    leadingIcon = { Icon(Icons.Filled.Delete, contentDescription = "Eliminar")},
                    onClick = {
                        expanded = false
                        eliminarSistema(sistema.SistemaId!!)

                    }
                )
            }
        }

    }
}
