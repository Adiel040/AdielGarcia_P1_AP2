package edu.ucne.adielgarcia_p1_ap2.presentation.Sistemas

import  androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import edu.ucne.adielgarcia_p1_ap2.presentation.Sistema.SistemaViewModel

@Composable
fun SistemaScreen(
    viewModel: SistemaViewModel = hiltViewModel(), goBack: () -> Unit

) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    SistemaBodyScreen(
        uiState = uiState,
        onNombreChange = viewModel::onNombreChange,
        onPrecioChange = viewModel::onPrecioChange,
        save = { viewModel.save(goBack)},
        goBack = goBack,
        nuevo = viewModel::nuevo
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SistemaBodyScreen(
    uiState: SistemaViewModel.UiState,
    onNombreChange: (String) -> Unit,
    onPrecioChange: (Double) -> Unit,
    save: () -> Unit,
    nuevo: () -> Unit,
    goBack: () -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Registro de sistemas",
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    )
                },
                navigationIcon = {
                    IconButton(onClick = goBack) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Regresar")
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFF6200EE)
                )


            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "Nombre") },
                value = uiState.nombre,
                onValueChange = onNombreChange

            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "Precio") },
                value = if (uiState.precio == 0.0) "" else uiState.precio.toString(),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                ),
                onValueChange = {newValue ->
                    val precio = newValue.toDoubleOrNull() ?: 0.0
                    onPrecioChange(precio)
                }
            )
            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedButton(
                    modifier = Modifier.weight(1f),
                    onClick = { save() }
                ) {
                    Text(text = "Guardar")
                    Icon(Icons.Filled.Add, contentDescription = "Guardar")
                }

                OutlinedButton(
                    modifier = Modifier.weight(1f),
                    onClick = { nuevo() }
                ) {
                    Text(text = "Nuevo")
                    Icon(Icons.Filled.Refresh, contentDescription = "Nuevo")
                }
            }

            uiState.errorMessage?.let{ message ->
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = message,
                    color = Color.Red,
                    style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold)
                )
            }

        }
    }
}