package edu.joverpenalva.proyectomoviles.model.trabajos


import com.google.gson.annotations.SerializedName

data class Trabajo(
    @SerializedName("message")
    val message: String,
    @SerializedName("result")
    val result: Result,
    @SerializedName("status")
    val status: Int
)
