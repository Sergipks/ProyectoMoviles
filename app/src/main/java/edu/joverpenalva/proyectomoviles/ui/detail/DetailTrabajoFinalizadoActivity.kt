package edu.joverpenalva.proyectomoviles.ui.detail

import android.app.ActivityOptions
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import edu.joverpenalva.proyectomoviles.TrabajadorApplication
import edu.joverpenalva.proyectomoviles.data.RemoteDataSource
import edu.joverpenalva.proyectomoviles.data.Repository
import edu.joverpenalva.proyectomoviles.databinding.ActivityDetailFinalizadaBinding
import edu.joverpenalva.proyectomoviles.databinding.ActivityDetailPendienteBinding
import edu.joverpenalva.proyectomoviles.model.trabajos.Result
import kotlinx.coroutines.launch

class DetailTrabajoFinalizadoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailFinalizadaBinding
    private lateinit var trabajo: Result

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

    private val vm: DetailViewModel by viewModels {
        val db = (application as TrabajadorApplication).trabajadorDB
        val dataSource = RemoteDataSource()
        val repository = Repository(db, dataSource)
        val codTrabajo = intent.getStringExtra(DetailTrabajoPendienteActivity.TRABAJO_COD)
        DetailViewModelFactory(repository, codTrabajo!!)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailFinalizadaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getTrabajo()

        binding.tvCodTrabajo.text = trabajo.codTrabajo
        binding.tvFecIni.text = trabajo.fechaInicio
        binding.tvCategoria.text = trabajo.categoria
        binding.tvDescripcion.text = trabajo.descripcion
        binding.tvFecFin.text = trabajo.fechaFin
        binding.tvTiempo.text = trabajo.tiempo.toString()

        if (trabajo.prioridad == 1) {
            binding.tvPrioridad.setTextColor(Color.RED)
        } else if (trabajo.prioridad == 2) {
            binding.tvPrioridad.setTextColor(Color.parseColor("#FFA500"))
        } else if (trabajo.prioridad == 3) {
            binding.tvPrioridad.setTextColor(Color.YELLOW)
        } else {
            binding.tvPrioridad.setTextColor(Color.GREEN)
        }
    }

    private fun getTrabajo() {
        lifecycleScope.launch {
            vm.trabajo.collect { t ->
                trabajo = t
            }
        }
    }
}