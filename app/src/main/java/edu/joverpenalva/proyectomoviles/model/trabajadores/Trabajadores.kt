package edu.joverpenalva.proyectomoviles.model.trabajadores


import com.google.gson.annotations.SerializedName

data class Trabajadores(
    @SerializedName("message")
    val message: String,
    @SerializedName("result")
    val result: List<Result>,
    @SerializedName("status")
    val status: Int
)
