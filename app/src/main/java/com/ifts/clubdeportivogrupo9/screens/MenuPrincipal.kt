package com.ifts.clubdeportivogrupo9.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ifts.clubdeportivogrupo9.navigation.AppScreens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuPrincipal(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Menú Principal")
                            }
                        )
                    },
                ) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .padding(innerPadding),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                    ) {
                        MainBodyContent(navController)
        }
    }
}
@Composable
fun MainBodyContent(navController: NavController) {
    MaterialTheme() {
        Column(
            modifier = Modifier.padding(20.dp).fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Button(onClick = {
                navController.navigate(route = AppScreens.RegistrarSocio.route)
            }) {
                Text(
                    text = "Registrar Usuario",
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(10.dp).width(200.dp)
                )
            }
            Button(onClick = {
                navController.navigate(route = AppScreens.BuscarCredencial.route)
            }) {
                Text(
                    text = "Ver credencial",
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(10.dp).width(200.dp)
                )
            }
            Button(onClick = {
                navController.navigate(route = AppScreens.CobrarCuota.route)
            }) {
                Text(
                    text = "Cobrar cuota",
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(10.dp).width(200.dp)
                )
            }
            Button(onClick = {

                navController.navigate(route = AppScreens.VerCuotasVencidas.route)
            }) {
                Text(
                    text = "Ver cuotas vencidas",
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(10.dp).width(200.dp)
                )
            }
            Button(onClick = {
                navController.navigate(route = AppScreens.Login.route)
            }) {
                Text(
                    text = "Cerrar sesión",
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(1.dp)
                )
            }
        }
    }
}