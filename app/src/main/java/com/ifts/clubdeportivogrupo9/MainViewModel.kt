package com.ifts.clubdeportivogrupo9

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ifts.clubdeportivogrupo9.data.CuotaMensualRepository
import com.ifts.clubdeportivogrupo9.data.SocioRepository
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.random.Random

class MainViewModel(
    private val repository: SocioRepository,
    private val cuotaMensualRepository: CuotaMensualRepository
) : ViewModel() {
    var state by mutableStateOf(
        MainState(
        nombreSocio = "",
        dniSocio = "",
        correoSocio = "",
        fechaInscripcion = "",
        aptoFisico = false,
        listaSocios = emptyList(),
        listaCuotas = emptyList(),
        listaCuotasByNumSocio = emptyList(),
        listaCuotasByNumDni = emptyList()
        )
    )
        private set

    fun onChange (
        nombreSocio: String,
        dniSocio: String,
        correoSocio: String,
        fechaInscripcion: String,
        aptoFisico: Boolean
    ) {
        state = state.copy(
            nombreSocio = nombreSocio,
            dniSocio = dniSocio,
            correoSocio = correoSocio,
            fechaInscripcion = fechaInscripcion,
            aptoFisico = aptoFisico
        )

    }

    fun saveSocio(
        valorNombre : String,
        valorApellido: String,
        valorDNI: String,
        valorEmail: String,
        checkAptoFisico: Boolean,
    ) {
        val format =  DateTimeFormatter.ofPattern("dd-MM-yyyy")
        val fecha = LocalDateTime.now().format(format)
        val socio = Socio(
            numSocio = 0,
            nombreSocio = valorNombre + " " + valorApellido,
            dniSocio = valorDNI,
            correoSocio = valorEmail,
            fechaInscripcion = fecha,
            aptoFisico = checkAptoFisico
//            nombreSocio = state.nombreSocio,
//            dniSocio = state.dniSocio,
//            correoSocio = state.correoSocio,
//            fechaInscripcion = state.fechaInscripcion,
//            aptoFisico = state.aptoFisico
        )
        viewModelScope.launch {
            repository.insertSocio(socio)
        }
    }

    fun buscarSocio(
        valorDNI: String
    ){
        var socioBuscado: Socio

        viewModelScope.launch {
            socioBuscado = repository.getSocio(valorDNI)
            state = state.copy(
                numSocio = socioBuscado.numSocio,
                nombreSocio = socioBuscado.nombreSocio,
                dniSocio= socioBuscado.dniSocio,
                correoSocio = socioBuscado.correoSocio,
                fechaInscripcion = socioBuscado.fechaInscripcion,
                aptoFisico = socioBuscado.aptoFisico,
            )
        }
    }


    fun listarCuotasQueVencen () {
        viewModelScope.launch {
            val listaCuotas = cuotaMensualRepository.getCuotasMensuales() // Devuelve el listado total de cuotas mensuales
            val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
            val fechaHoy = LocalDateTime.now().format(formatter).toString()
            val listaCuotasQueVencen = listaCuotas
                .filter { it.fechaVencimiento == fechaHoy} // Compara la fecha actual
                .filter { it.metodoPago == 0 } // Filtra que la cuota no esté pagada

            state = state.copy(
                listaCuotas = listaCuotasQueVencen
            )
        }
    }

    fun listarCuotasDeSocio (numSocio:Int?) { // Devuelve el listado completo de cuotas correspondientes a un socio
        viewModelScope.launch {

            val listaCuotas = cuotaMensualRepository.getCuotasMensualesByNumSocio(numSocio) //

            state = state.copy(
                listaCuotasByNumSocio = listaCuotas
            )
        }
    }

    fun pagarCuotaMensual (idCuota:Int, metodoPago: Int) { //Actualiza el medio y la fecha de pago de una cuota
        viewModelScope.launch {

            val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
            val fechaHoy = LocalDateTime.now().format(formatter).toString()
            cuotaMensualRepository.pagarCuotaMensual(idCuota, metodoPago, fechaHoy)
//            state = state.copy(
//                listaCuotasByNumSocio = listaCuotas
//            )
        }
    }



    // Genera una cuota de manera aleatoria
    fun insertarCuotas() {
        val diaRandom = Random.nextInt(1, 30)
        val mesRandom = Random.nextInt(1, 12)
        var mesString = ""
        var diaString = ""
        if (mesRandom<10) {
            mesString = "0$mesRandom"
        } else {
            mesString = mesRandom.toString()
        }
        if (diaRandom<10) {
            diaString = "0$mesRandom"
        } else {
            diaString = diaRandom.toString()
        }
        val cuota = CuotaMensual(
            idCuota = 0,
            numSocio = Random.nextInt(1000, 9999),
            montoCuota = Random.nextInt(1000, 9999).toFloat(),
            fechaPago = "",
//            metodoPago = Random.nextInt(1, 3),
            metodoPago = 0,
            fechaInicio = "",
            fechaVencimiento = "$diaString-$mesString-2024"
        )
        viewModelScope.launch {
            cuotaMensualRepository.insertCuotaMensual(cuota)
        }
    }
}
