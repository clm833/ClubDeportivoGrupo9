package com.ifts.clubdeportivogrupo9.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.ifts.clubdeportivogrupo9.MainViewModel
import com.ifts.clubdeportivogrupo9.navigation.AppScreens

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RegistrarSocio(
    navController: NavController,
    viewModel: MainViewModel
    ) {
    val state = viewModel.state

    Scaffold( topBar = {
        TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary,
            ),
            title = {
            Text(text = "Registrar nuevo socio")
            },
            navigationIcon = {
                IconButton(onClick = { /* do something */ }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Atras",
                        modifier = Modifier.clickable {
                            navController.popBackStack()
                        }
                    )
                }
            }
        )
    }

    ) {
        RegistrarSocioBodyContent(navController, viewModel)
    }
}

@Composable
fun RegistrarSocioBodyContent(navController: NavController, viewModel: MainViewModel) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var valorNombre by remember { mutableStateOf(TextFieldValue("")) }
        var valorApellido by remember { mutableStateOf(TextFieldValue("")) }
        var valorDNI by remember { mutableStateOf(TextFieldValue("")) }
        var valorEmail by remember { mutableStateOf(TextFieldValue("")) }
        var checkAptoFisico by remember { mutableStateOf(false) }
        OutlinedTextField(
            value = valorNombre,
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                autoCorrect = true,
                keyboardType = KeyboardType.Text,
                capitalization = KeyboardCapitalization.Sentences,
                imeAction = ImeAction.Next
            ),
            onValueChange = {
                valorNombre = it
            },
            label = { Text(text = "Nombre") }
        )
        OutlinedTextField(
            value = valorApellido,
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                autoCorrect = true,
                keyboardType = KeyboardType.Text,
                capitalization = KeyboardCapitalization.Sentences,
                imeAction = ImeAction.Next
            ),
            onValueChange = {
                valorApellido = it
            },
            label = { Text(text = "Apellido") }
        )
        OutlinedTextField(
            value = valorDNI,
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                autoCorrect = true,
                keyboardType = KeyboardType.NumberPassword,
                imeAction = ImeAction.Next
            ),
            onValueChange = {
                valorDNI = it
            },
            label = { Text(text = "Número de DNI") }
        )
        OutlinedTextField(
            value = valorEmail,
            keyboardOptions = KeyboardOptions(
                autoCorrect = true,
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            singleLine = true,
            onValueChange = {
                valorEmail = it
            },
            label = { Text(text = "Email") }
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Checkbox(
                checked = checkAptoFisico,
                onCheckedChange = { checkAptoFisico = it }
            )
            Text(
                "Apto Físico"
            )
        }
        Spacer(modifier = Modifier.size(40.dp))
        Button(onClick = {
            viewModel.saveSocio(
                valorNombre.text,
                valorApellido.text,
                valorDNI.text,
                valorEmail.text,
                checkAptoFisico,
            )
            navController.popBackStack()
        }) {
            Text(
                text = "Registrar",
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(10.dp)
                    .width(200.dp)
            )
        }
    }
}

/*
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RegistrarSocio()
}*/