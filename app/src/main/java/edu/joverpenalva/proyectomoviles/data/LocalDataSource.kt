package edu.joverpenalva.proyectomoviles.data

import edu.joverpenalva.proyectomoviles.model.trabajadores.Result

class LocalDataSource (val db: TrabajadoresDao) {
    suspend fun insertTrabajador(trabajador: Result) = db.insertTrabajador(trabajador)

    suspend fun getTrabajador(): Result?{
        return db.getTrabajador()
    }

    suspend fun deleteTrabajador(trabajador: Result) = db.deleteTrabajador(trabajador)
}