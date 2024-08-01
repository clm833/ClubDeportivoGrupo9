package com.ifts.clubdeportivogrupo9.data
import com.ifts.clubdeportivogrupo9.CuotaMensual

class CuotaMensualRepository(
    private val socioDao: SocioDao
) {
    suspend fun getCuotasMensuales(): List<CuotaMensual> {
        val entities = socioDao.getCuotaMensual()
        return entities.map {
            CuotaMensual(
                idCuota = it.idCuota!!,
                numSocio = it.numSocio!!,
                montoCuota = it.montoCuota,
                fechaPago = it.fechaPago,
                metodoPago = it.metodoPago,
                fechaInicio = it.fechaInicio,
                fechaVencimiento = it.fechaVencimiento
            )
        }
    }

    suspend fun getCuotasMensualesByNumSocio(numSocio: Int?): List<CuotaMensual> {
        val entities = socioDao.getCuotaMensualByNumSocio(numSocio)
        return entities.map {
            CuotaMensual(
                idCuota = it.idCuota!!,
                numSocio = it.numSocio!!,
                montoCuota = it.montoCuota,
                fechaPago = it.fechaPago,
                metodoPago = it.metodoPago,
                fechaInicio = it.fechaInicio,
                fechaVencimiento = it.fechaVencimiento
            )
        }
    }


    suspend fun getCuotaMensual(idCuota: Int): CuotaMensual {
        val entity = socioDao.getCuotaMensual(idCuota = idCuota)
        return CuotaMensual(
            idCuota = entity.idCuota!!,

            numSocio = entity.numSocio!!,
            montoCuota = entity.montoCuota,
            fechaPago = entity.fechaPago,
            metodoPago = entity.metodoPago,
            fechaInicio = entity.fechaInicio,
            fechaVencimiento = entity.fechaVencimiento
        )
    }

    suspend fun insertCuotaMensual(cuotaMensual: CuotaMensual) {
        val entity = CuotaMensualEntity(
            numSocio = cuotaMensual.numSocio,
            montoCuota = cuotaMensual.montoCuota,
            fechaPago = cuotaMensual.fechaPago,
            metodoPago = cuotaMensual.metodoPago,
            fechaInicio = cuotaMensual.fechaInicio,
            fechaVencimiento = cuotaMensual.fechaVencimiento
        )
        socioDao.insertCuotaMensual(entity)
    }

    suspend fun pagarCuotaMensual(idCuota:Int, metodoPago: Int, fechaHoy: String) {
        socioDao.pagarCuotaMensual(idCuota, metodoPago, fechaHoy)
    }
}