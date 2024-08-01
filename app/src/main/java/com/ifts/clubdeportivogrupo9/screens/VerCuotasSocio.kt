package com.ifts.clubdeportivogrupo9.screens

import android.annotation.SuppressLint
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ifts.clubdeportivogrupo9.CuotaMensual
import com.ifts.clubdeportivogrupo9.MainViewModel
import com.ifts.clubdeportivogrupo9.data.CuotaMensualEntity

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun VerCuotasSocio(
    navController: NavController,
    viewModel: MainViewModel
) {
    val state = viewModel.state
    val numSocio = state.numSocio
    viewModel.listarCuotasDeSocio(numSocio)
    println("listarCuotasDeSocio($numSocio) -> listaCuotasBynumSocio: " + state.listaCuotasByNumSocio)

    val listaCuotasByNumSocio = state.listaCuotasByNumSocio

    Scaffold( topBar = {
        TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary,
            ),
            title = {
            Text(text = "Cuotas que vencen hoy")
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
            },
            scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
        )
    }

    ) {
//        val arrayCuotas: List
//        VerCuotasVencidasBodyContent(navController)
            innerPadding ->
        // Apply the padding globally to the whole BottomNavScreensController
        Box(modifier = Modifier.padding(innerPadding)) {
            VerCuotasSocioBodyContent(
                modifier = Modifier,
                listaCuotasByNumSocio,
                viewModel,
                navController
            )
        }

    }
}

    @Composable
    private fun VerCuotasSocioBodyContent(
        modifier: Modifier = Modifier,
        listaCuotas: List<CuotaMensual>,
        viewModel: MainViewModel,
        navController: NavController
//        names: List<String> = List(11) { "$it" }
    ) {
        LazyColumn(
            modifier = modifier
                .padding(vertical = 4.dp)
                .fillMaxSize()
        ) {
//            items(items = names) { name ->
//                Greeting(name = name)
//            }
            items(items = listaCuotas) { listaCuotas ->
                CardCuota(cuota = listaCuotas, viewModel, navController)
            }
        }
    }

    @Composable
    fun Greeting(name: String, modifier: Modifier = Modifier) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primary
            ),
            modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
        ) {
//            CardContent(name)
        }
    }
    @Composable
    fun CardCuota(cuota: CuotaMensual, viewModel: MainViewModel, navController: NavController) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primary
            ),
            modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
        ) {
            CardContent(
                cuota.idCuota.toString(),
                cuota.fechaVencimiento,
                cuota.numSocio.toString(),
                cuota.montoCuota.toString(),
                cuota.metodoPago,
                viewModel,
                navController
            )
        }
    }


    @Composable
    private fun CardContent(
        id: String,
        vencimiento: String,
        socio: String,
        montoCuota: String,
        metodoPago: Int,
        viewModel: MainViewModel,
        navController: NavController
    ) {
//        val expanded by rememberSaveable { mutableStateOf(false) }

        Row(
            modifier = Modifier
                .padding(12.dp)
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(12.dp)
                    .fillMaxSize()
            ) {
                Text(
                    text = "ID de cuota: $id"
                )
                Text(
                    text = "Vencimiento: $vencimiento"
                )
                Text(
                    text = "Nro de socio: $socio",
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.ExtraBold
                    )

                )
                Text(
                    text = "Monto: $$montoCuota",
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.ExtraBold
                    )
                )
                if(metodoPago == 0) {
                    Text(
                        text = "Cobrar",
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontWeight = FontWeight.ExtraBold
                        ),
                        textAlign = TextAlign.Right,
                        textDecoration = TextDecoration.Underline,
                        color = Color.White,
                        modifier = Modifier
                            .clickable(
                                onClick = {
                                    viewModel.pagarCuotaMensual(id.toInt(), metodoPago = 1)
                                    navController.popBackStack()
                                })
                            .fillMaxWidth(),

                        )
                } else {
                    Text(
                        text = "Pagado",
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontWeight = FontWeight.ExtraBold
                        ),
                        textAlign = TextAlign.Right,
                        textDecoration = TextDecoration.Underline,
                        color = Color.Green,
                        modifier = Modifier.fillMaxWidth(),
                        )
                }

//                if (expanded) {
//                    Text(
//                        text = ("Composem ipsum color sit lazy, " +
//                                "padding theme elit, sed do bouncy. ").repeat(4)
//                    )
//                }
            }
        }
    }
