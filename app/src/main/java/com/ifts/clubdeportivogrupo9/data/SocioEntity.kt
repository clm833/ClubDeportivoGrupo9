package com.ifts.clubdeportivogrupo9.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "socios")
data class SocioEntity(
    @PrimaryKey(autoGenerate = true)
    val numSocio: Int? = null,
    val nombreSocio: String,
    val dniSocio: String,
    val correoSocio: String,
    val fechaInscripcion: String,
    val aptoFisico: Boolean
)
