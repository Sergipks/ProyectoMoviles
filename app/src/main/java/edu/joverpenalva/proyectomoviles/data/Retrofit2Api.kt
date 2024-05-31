package edu.joverpenalva.proyectomoviles.data

import edu.joverpenalva.proyectomoviles.model.trabajadores.Trabajadores
import edu.joverpenalva.proyectomoviles.model.trabajos.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.math.BigDecimal

class Retrofit2Api {
    companion object {
        const val BASE_URL = "http://192.168.0.24:8080/api/"
        fun getRetrofit2Api(): Retrofit2ApiInterface {
            return Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build()
                .create(Retrofit2ApiInterface::class.java)
        }
    }
}

interface Retrofit2ApiInterface {
    @GET("trabajadores")
    suspend fun getTrabajadores(): Trabajadores

    @GET("trabajos/pendientes/trabajador") // http:localhost:8080/api/trabajos/pendientes/trabajador?idTrabajador=11111&contraseña=1234
    suspend fun getTrabajosPendientes(@Query("idTrabajador") idTrabajador: String, @Query("contraseña") password: String): Trabajos

    @GET("trabajos/finalizados/trabajador") // http:localhost:8080/api/trabajos/finalizados/trabajador?idTrabajador=11111&contraseña=1234
    suspend fun getTrabajosFinalizados(@Query("idTrabajador") idTrabajador: String, @Query("contraseña") password: String): Trabajos

    @PUT("trabajos/{id}/finalizar") // http:localhost:8080/api/trabajos/TR001/finalizar?tiempo=2.5
    suspend fun finalizarTrabajo(@Path("id") codTrabajo: String, @Query("tiempo") tiempo: BigDecimal): Trabajo
}
