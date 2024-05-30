package edu.joverpenalva.proyectomoviles.data

import android.util.Log
import edu.joverpenalva.proyectomoviles.model.trabajadores.Result as Trabajador
import edu.joverpenalva.proyectomoviles.model.trabajos.Result as Trabajo
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

class Repository(db: TrabajadoresRoomDB, val ds: RemoteDataSource)  {
    val TAG = Repository::class.java.simpleName
    private val localDataSource = LocalDataSource(db.trabajadoresDao())

    fun fetchTrabajador(): Flow<Trabajador?> {
        return flow {
            var resultDB: Trabajador? = null
            try {
                // Se intenta recuperar la información de la base de datos.
                resultDB = localDataSource.getTrabajador()
            } catch (e: Exception) {
                // Se emite el error.
                Log.e(TAG, "fetchTrabajadores: ${e.message}")
            } finally {
                // Se emite el resultado de la base de datos.
                // Una lista con datos o vacía.
                emit(resultDB)
            }
        }
    }

    fun fetchAPITrabajadores(): Flow<List<Trabajador>> {
        return flow {
            var resultAPI = emptyList<Trabajador>()
            try {
                // Se intenta recuperar la información de la api.
                resultAPI = ds.getTrabajadores()
            } catch (e: Exception) {
                // Se emite el error.
                Log.e(TAG, "fetchTrabajadores: ${e.message}")
            } finally {
                // Se emite el resultado de la api.
                // Una lista con datos o vacía.
                emit(resultAPI)
            }
        }
    }

    fun fetchAPITrabajosPendientes(idTrabajador: String, contrasenya: String): Flow<List<Trabajo>> {
        return flow {
            var resultAPI = emptyList<Trabajo>()

            try {
                // Se intenta recuperar la información de la API.
                resultAPI = ds.getTrabajosPendientes(idTrabajador, contrasenya)
            } catch (e: Exception) {
                // Se emite el error.
                Log.e(TAG, "fetchTrabajosPendientes: ${e.message}")
            } finally {
                // Se emite el resultado de la API.
                // Una lista con datos o vacía.
                emit(resultAPI)
            }
        }
    }

    fun fetchAPITrabajosFinalizados(idTrabajador: String, contrasenya: String): Flow<List<Trabajo>> {
        return flow {
            var resultAPI = emptyList<Trabajo>()
            try {
                // Se intenta recuperar la información de la API.
                resultAPI = ds.getTrabajosFinalizados(idTrabajador, contrasenya)
            } catch (e: Exception) {
                // Se emite el error.
                Log.e(TAG, "fetchTrabajosPendientes: ${e.message}")
            } finally {
                // Se emite el resultado de la API.
                // Una lista con datos o vacía.
                emit(resultAPI)
            }
        }
    }

    suspend fun saveTrabajador(trabajador: Trabajador) = runBlocking {
        localDataSource.insertTrabajador(trabajador)
        delay(10)
    }

    suspend fun deleteTrabajador(trabajador: Trabajador) = runBlocking {
        localDataSource.deleteTrabajador(trabajador)
        delay(10)
    }
}
