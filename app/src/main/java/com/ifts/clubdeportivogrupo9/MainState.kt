package com.ifts.clubdeportivogrupo9

data class MainState(
    val numSocio: Int? = null,
    val nombreSocio: String = "",
    val dniSocio: String ="",
    val correoSocio: String ="",
    val fechaInscripcion: String ="",
    val aptoFisico: Boolean = false,
    val listaSocios: List<String> = emptyList(),
    val listaCuotas: List<CuotaMensual> = emptyList(),
    val listaCuotasByNumSocio: List<CuotaMensual> = emptyList(),
    val listaCuotasByNumDni: List<CuotaMensual> = emptyList()
)
