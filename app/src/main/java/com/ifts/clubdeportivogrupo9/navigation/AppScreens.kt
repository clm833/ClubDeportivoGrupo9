package com.ifts.clubdeportivogrupo9.navigation

import androidx.navigation.NamedNavArgument

sealed class AppScreens (
    val route: String,
    val arguments: List<NamedNavArgument>
) {
    object Login: AppScreens("login", emptyList())
    object MenuPrincipal: AppScreens("main", emptyList())
    object RegistrarSocio: AppScreens("registrar_socio", emptyList())
    object BuscarCredencial: AppScreens("buscar_credencial", emptyList())
    object VerCredencial: AppScreens("ver_credencial", emptyList())
    object CobrarCuota: AppScreens("cobrar_cuota", emptyList())
    object VerCuotasVencidas: AppScreens("ver_cuotas_vencidas", emptyList())
    object VerCuotasSocio: AppScreens("ver_cuotas_socio", emptyList())
}