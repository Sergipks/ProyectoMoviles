package edu.joverpenalva.proyectomoviles.ui.detail

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import edu.joverpenalva.proyectomoviles.databinding.ActivityDetailFinalizadaBinding
import edu.joverpenalva.proyectomoviles.databinding.ActivityDetailPendienteBinding

class DetailTrabajoFinalizadoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailFinalizadaBinding
    companion object {
        const val TRABAJO_COD = "TRABAJO_COD"

        fun navigate(activity: AppCompatActivity, cod: String = "") {
            val intent = Intent(activity, DetailTrabajoFinalizadoActivity::class.java).apply {
                putExtra(TRABAJO_COD, cod)
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            }
            activity.startActivity(
                intent,
                ActivityOptions.makeSceneTransitionAnimation(activity).toBundle()
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailFinalizadaBinding.inflate(layoutInflater)

    }
}