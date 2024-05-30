package edu.joverpenalva.proyectomoviles.model.trabajos


import com.google.gson.annotations.SerializedName

data class Trabajos(
    @SerializedName("message")
    val message: String,
    @SerializedName("result")
    val result: List<Result>,
    @SerializedName("status")
    val status: Int
)
