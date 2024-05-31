package edu.joverpenalva.proyectomoviles.ui.detail

import android.app.ActivityOptions
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import edu.joverpenalva.proyectomoviles.TrabajadorApplication
import edu.joverpenalva.proyectomoviles.data.LocalDataSource
import edu.joverpenalva.proyectomoviles.data.RemoteDataSource
import edu.joverpenalva.proyectomoviles.data.Repository
import edu.joverpenalva.proyectomoviles.databinding.ActivityDetailPendienteBinding
import edu.joverpenalva.proyectomoviles.model.trabajos.Result
import edu.joverpenalva.proyectomoviles.trabajosPendientesList
import kotlinx.coroutines.launch
import java.math.BigDecimal

class DetailTrabajoPendienteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailPendienteBinding
    private lateinit var trabajo: Result
    companion object {
        const val TRABAJO_COD = "TRABAJO_COD"

        fun navigate(activity: AppCompatActivity, cod: String = "") {
            val intent = Intent(activity, DetailTrabajoPendienteActivity::class.java).apply {
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
        val codTrabajo = intent.getStringExtra(TRABAJO_COD)
        DetailViewModelFactory(repository, codTrabajo!!)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailPendienteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getTrabajo()

        binding.tvCodTrabajo.text = trabajo.codTrabajo
        binding.tvFecIni.text = trabajo.fechaFin
        binding.tvCategoria.text = trabajo.categoria
        binding.tvDescripcion.text = trabajo.descripcion

        if (trabajo.prioridad == 1) {
            binding.tvPrioridad.setTextColor(Color.RED)
        } else if (trabajo.prioridad == 2) {
            binding.tvPrioridad.setTextColor(Color.parseColor("#FFA500"))
        } else if (trabajo.prioridad == 3) {
            binding.tvPrioridad.setTextColor(Color.YELLOW)
        } else {
            binding.tvPrioridad.setTextColor(Color.GREEN)
        }

        binding.tvPrioridad.text = trabajo.prioridad.toString()

        binding.btnFinalizar.isEnabled = binding.tiedTiempo.text == null

        binding.btnFinalizar.setOnClickListener {
            val tiempoStr = binding.tiedTiempo.text.toString()
            val tiempoBigDecimal: BigDecimal

            try {
                // Parsear el tiempo a BigDecimal
                tiempoBigDecimal = BigDecimal(tiempoStr)
                // Llamar al método finalizarTrabajo con el valor de BigDecimal
                lifecycleScope.launch {
                    vm.repository.finalizarTrabajo(trabajo.codTrabajo, tiempoBigDecimal)
                }
            } catch (e: NumberFormatException) {
                // Manejar el error si el valor ingresado no es un número válido
                Toast.makeText(this, "Por favor, ingrese un tiempo válido", Toast.LENGTH_SHORT).show()
            }
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