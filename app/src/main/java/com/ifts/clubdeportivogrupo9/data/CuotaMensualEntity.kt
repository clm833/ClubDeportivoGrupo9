package com.ifts.clubdeportivogrupo9.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "cuotasocio",
//    foreignKeys = [ForeignKey(
//        entity = SocioEntity::class,
//        parentColumns = ["numSocio"],
//        childColumns = ["numSocio"],
//        onDelete = ForeignKey.CASCADE
//    )]
)
data class CuotaMensualEntity(
    @PrimaryKey(autoGenerate = true)
    val idCuota: Int? = null,

    val numSocio: Int? = null,
    val montoCuota: Float,
    val fechaPago: String,
    val metodoPago: Int,
    val fechaInicio: String,
    val fechaVencimiento: String
)
