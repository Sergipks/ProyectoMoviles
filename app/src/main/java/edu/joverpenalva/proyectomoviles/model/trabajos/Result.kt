package edu.joverpenalva.proyectomoviles.model.trabajos


import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

data class Result(
    @SerializedName("categoria")
    val categoria: String,
    @SerializedName("codTrabajo")
    val codTrabajo: String,
    @SerializedName("descripcion")
    val descripcion: String,
    @SerializedName("fechaFin")
    val fechaFin: String?,
    @SerializedName("fechaInicio")
    val fechaInicio: String,
    @SerializedName("idTrabajador")
    val idTrabajador: String?,
    @SerializedName("prioridad")
    val prioridad: Int,
    @SerializedName("tiempo")
    val tiempo: BigDecimal?
)
