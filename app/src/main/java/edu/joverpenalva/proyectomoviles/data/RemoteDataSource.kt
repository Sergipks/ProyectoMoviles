package edu.joverpenalva.proyectomoviles.data

import edu.joverpenalva.proyectomoviles.model.trabajos.Result as Trabajo
import edu.joverpenalva.proyectomoviles.model.trabajadores.Result as Trabajador
import java.math.BigDecimal

class RemoteDataSource {
    private val api = Retrofit2Api.getRetrofit2Api()

    suspend fun getTrabajadores(): List<Trabajador> {
        return api.getTrabajadores().result
    }

    suspend fun getTrabajosPendientes(idTrabajador: String, password: String): List<Trabajo> {
        return api.getTrabajosPendientes(idTrabajador, password).result
    }

    suspend fun getTrabajosFinalizados(idTrabajador: String, password: String): List<Trabajo> {
        return api.getTrabajosFinalizados(idTrabajador, password).result
    }


    suspend fun finalizarTrabajo(codTrabajo: String, tiempo: BigDecimal): Trabajo {
        return api.finalizarTrabajo(codTrabajo, tiempo).result
    }
}
