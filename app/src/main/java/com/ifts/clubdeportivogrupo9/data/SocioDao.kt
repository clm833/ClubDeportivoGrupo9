package com.ifts.clubdeportivogrupo9.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface SocioDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSocio(socio:SocioEntity)

    @Query("SELECT * FROM socios")
    suspend fun getSocios(): List<SocioEntity>

    @Query("SELECT * FROM socios WHERE dniSocio = :dniSocio")
    suspend fun getSocio(dniSocio: String): SocioEntity


    // *************** Cuotas *********

    @Query("UPDATE cuotasocio SET metodoPago = :metodoPago, fechaPago = :fechaPago WHERE idCuota = :idCuota")
    suspend fun pagarCuotaMensual(idCuota: Int, metodoPago: Int, fechaPago: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCuotaMensual(cuota: CuotaMensualEntity)

    @Query("SELECT * FROM cuotasocio")
    suspend fun getCuotaMensual(): List<CuotaMensualEntity>

    @Query("SELECT * FROM cuotasocio WHERE idCuota = :idCuota")
    suspend fun getCuotaMensual(idCuota: Int): CuotaMensualEntity

    @Query("SELECT * FROM cuotasocio WHERE numSocio = :numSocio")
    suspend fun getCuotaMensualByNumSocio(numSocio: Int?): List<CuotaMensualEntity>


}