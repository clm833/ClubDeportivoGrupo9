package com.ifts.clubdeportivogrupo9

data class CuotaMensual(
    val idCuota: Int,

    val numSocio: Int,
    val montoCuota: Float,
    val fechaPago: String,
    val metodoPago: Int,
    val fechaInicio: String,
    val fechaVencimiento: String
)
