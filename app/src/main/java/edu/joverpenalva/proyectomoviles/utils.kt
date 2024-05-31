package edu.joverpenalva.proyectomoviles

import android.content.Context.CONNECTIVITY_SERVICE
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import edu.joverpenalva.proyectomoviles.model.trabajos.Result

val trabajosPendientesList: MutableList<Result> = mutableListOf()
val trabajosFinalizadosList: MutableList<Result> = mutableListOf()
var usuario: edu.joverpenalva.proyectomoviles.model.trabajadores.Result? = null

fun checkConnection(context: Context): Boolean {
    val cm = context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkInfo = cm.activeNetwork

    if (networkInfo != null) {
        val activeNetwork = cm.getNetworkCapabilities(networkInfo)
        if (activeNetwork != null) {
            return when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }
        }
    }
    return false
}