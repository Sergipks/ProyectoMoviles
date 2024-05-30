package edu.joverpenalva.proyectomoviles.data

import edu.joverpenalva.proyectomoviles.model.trabajos.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

class Retrofit2Api {
    companion object {
        const val BASE_URL = "http://localhost:8080/api/"
        fun getRetrofit2Api(): Retrofit2ApiInterface {
            return Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build()
                .create(Retrofit2ApiInterface::class.java)
        }
    }
}

interface Retrofit2ApiInterface {
    @GET("trabajos/pendientes/trabajador") // http:localhost:8080/api/trabajos/pendientes/trabajador?idTrabajador=11111&contraseña=1234
    suspend fun getTrabajosPendientes(@Query("trabajador") idTrabajador: Int, @Query("contraseña") password: String): Trabajos

    @GET("trabajos/finalizados/trabajador") // http:localhost:8080/api/trabajos/finalizados/trabajador?idTrabajador=11111&contraseña=1234
    suspend fun getTrabajosFinalizados(@Query("trabajador") idTrabajador: Int, @Query("contraseña") password: String): Trabajos
}
