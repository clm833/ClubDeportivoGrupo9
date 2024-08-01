package com.ifts.clubdeportivogrupo9.data

import com.ifts.clubdeportivogrupo9.Socio

class SocioRepository(
    private val socioDao: SocioDao
) {
    suspend fun getSocios(): List<Socio> {
        val entities = socioDao.getSocios()
        return entities.map {
            Socio(
                numSocio = it.numSocio!!,
                nombreSocio = it.nombreSocio,
                dniSocio = it.dniSocio,
                correoSocio = it.correoSocio,
                fechaInscripcion = it.fechaInscripcion,
                aptoFisico = it.aptoFisico
            )
        }
    }

    suspend fun getSocio(dni: String): Socio {
        val entity = socioDao.getSocio(dniSocio = dni)
        return Socio(
                numSocio = entity.numSocio!!,
                nombreSocio = entity.nombreSocio,
                dniSocio = entity.dniSocio,
                correoSocio = entity.correoSocio,
                fechaInscripcion = entity.fechaInscripcion,
                aptoFisico = entity.aptoFisico
        )
    }

    suspend fun insertSocio(socio: Socio) {
        val entity = SocioEntity(
            nombreSocio = socio.nombreSocio,
            dniSocio = socio.dniSocio,
            correoSocio = socio.correoSocio,
            fechaInscripcion = socio.fechaInscripcion,
            aptoFisico = socio.aptoFisico
        )
        socioDao.insertSocio(entity)
    }
}