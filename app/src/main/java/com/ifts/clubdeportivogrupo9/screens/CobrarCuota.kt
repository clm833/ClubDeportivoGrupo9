package com.ifts.clubdeportivogrupo9.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.navigation.NavController
import com.ifts.clubdeportivogrupo9.MainViewModel
import com.ifts.clubdeportivogrupo9.navigation.AppScreens
import kotlinx.coroutines.*

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CobrarCuota(
    navController: NavController,
    viewModel: MainViewModel
) {

    Scaffold( topBar = {
        TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary,
            ),
            title = {
            Text(text = "Cobrar cuota")
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
        CobrarCuotaBodyContent(navController, viewModel)
    }
}

@Composable
fun CobrarCuotaBodyContent(
    navController: NavController,
    viewModel: MainViewModel,
) {


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var valorDNI by remember { mutableStateOf(TextFieldValue("")) }
        Text(text = "Ingrese el DNI del socio")
        OutlinedTextField(
            value = valorDNI,
            keyboardOptions = KeyboardOptions(
                autoCorrect = true,
                keyboardType = KeyboardType.NumberPassword,
                imeAction = ImeAction.Next
            ),
            onValueChange = {
                valorDNI = it
            },
            label = { Text(text = "DNI") }
        )
        Button(onClick = {
            buscarCuotas(valorDNI.text, viewModel, navController)
//            navController.popBackStack()
        }) {
            Text(text = "Buscar")
        }
    }
}

fun buscarCuotas(dni: String, viewModel: MainViewModel, navController: NavController ) {
    val state = viewModel.state
    val numSocio = state.numSocio
    val listaCuotasByNumSocio = state.listaCuotasByNumSocio

    runBlocking {
        println("buscarCuotas($dni)")

        viewModel.buscarSocio(dni)
        println("buscarSocio($dni) -> numSocio: " + numSocio)

        navController.navigate(route = AppScreens.VerCuotasSocio.route)
    }
    }

/*
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RegistrarSocio()
}*/