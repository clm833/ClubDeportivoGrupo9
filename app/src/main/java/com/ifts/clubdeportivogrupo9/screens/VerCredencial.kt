package com.ifts.clubdeportivogrupo9.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.navigation.NavController
import com.ifts.clubdeportivogrupo9.MainState
import com.ifts.clubdeportivogrupo9.MainViewModel
import com.ifts.clubdeportivogrupo9.R

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun VerCredencial(navController: NavController, viewModel: MainViewModel) {
    val state = viewModel.state
    Scaffold( topBar = {
        TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary,
            ),
            title = {
            Text(text = "Ver Credencial")
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
        VerCredencialBodyContent(navController, state)
    }
}

@Composable
fun VerCredencialBodyContent(navController: NavController, state: MainState) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var valorNombre by remember { mutableStateOf(TextFieldValue("")) }
        var valorApellido by remember { mutableStateOf(TextFieldValue("")) }
        var valorDNI by remember { mutableStateOf(TextFieldValue("")) }
        var valorEmail by remember { mutableStateOf(TextFieldValue("")) }
        Image(
            painter = painterResource(id = R.drawable.default_profile),
            contentDescription = stringResource(id = R.string.default_description)
        )
        var socio = state.numSocio.toString()
        OutlinedTextField(
            value = socio,
            onValueChange = {
//                valorNombre = it
            },
//            enabled = false,
            readOnly=true,
            label = { Text(text = "Numero de Socio") }
        )
        OutlinedTextField(
            value = state.nombreSocio,
//            value = valorNombre,
            onValueChange = {
//                valorNombre = it
            },
            readOnly=true,
            label = { Text(text = "Nombre") }
        )
        OutlinedTextField(
            value = state.dniSocio,
            onValueChange = {
//                valorDNI = it
            },
            readOnly=true,
            label = { Text(text = "Número de DNI") }
        )
        OutlinedTextField(
            value = state.correoSocio,
            onValueChange = {
//                valorEmail = it
            },
            readOnly=true,
            label = { Text(text = "Email") }
        )
    }
}
/*
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RegistrarSocio()
}*/