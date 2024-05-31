package edu.joverpenalva.proyectomoviles.ui.main

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.*
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import edu.joverpenalva.proyectomoviles.R
import edu.joverpenalva.proyectomoviles.TrabajadorApplication
import edu.joverpenalva.proyectomoviles.checkConnection
import edu.joverpenalva.proyectomoviles.databinding.ActivityMainBinding
import edu.joverpenalva.proyectomoviles.trabajosFinalizadosList
import edu.joverpenalva.proyectomoviles.trabajosPendientesList
import edu.joverpenalva.proyectomoviles.ui.detail.DetailTrabajoFinalizadoActivity
import edu.joverpenalva.proyectomoviles.ui.detail.DetailTrabajoPendienteActivity
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as TrabajadorApplication).repository)
    }

    private val adapter = TrabajosAdapter(
        onClickTrabajo = {
            if (it.fechaFin == null)
                DetailTrabajoPendienteActivity.navigate(this@MainActivity, it.codTrabajo)
            else
                DetailTrabajoFinalizadoActivity.navigate(this@MainActivity, it.codTrabajo)
        }
    )

    // Variable para alternar el estado de ordenación
    private var isSortedAscending = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Bloqueo de la rotación
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_NOSENSOR

        binding.recycler.adapter = adapter
        // Se infla el menú en la Toolbar
        binding.mToolbar.inflateMenu(R.menu.toolbar_menu)

        // Inflado del menú en la BottomNavigationView
        binding.bottomNav.inflateMenu(R.menu.bottom_menu)

        // Fetch initial data
        fetchPendingJobs()
    }

    override fun onStart() {
        super.onStart()

        binding.swipeRefresh.setOnRefreshListener {
            // Verificar qué ítem de navegación está seleccionado actualmente
            when (binding.bottomNav.selectedItemId) {
                R.id.action_pending -> {
                    fetchPendingJobs()
                }
                R.id.action_finished -> {
                    fetchFinishedJobs()
                }
            }
        }

        binding.mToolbar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.opt_ordenar_prioridad -> {
                    toggleSortOrder()
                    true
                }
                else -> false
            }
        }

        // Configurar el BottomNavigationView
        binding.bottomNav.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.action_pending -> {
                    fetchPendingJobs()
                    true
                }
                R.id.action_finished -> {
                    fetchFinishedJobs()
                    true
                }
                else -> false
            }
        }
    }

    override fun onResume() {
        super.onResume()

        // Verificar qué ítem de navegación está seleccionado actualmente
        when (binding.bottomNav.selectedItemId) {
            R.id.action_pending -> {
                fetchPendingJobs()
            }
            R.id.action_finished -> {
                fetchFinishedJobs()
            }
        }
    }

    private fun toggleSortOrder() {
        val sortedList = if (isSortedAscending) {
            adapter.currentList.sortedByDescending { it.prioridad }
        } else {
            adapter.currentList.sortedBy { it.prioridad }
        }
        adapter.submitList(sortedList)
        isSortedAscending = !isSortedAscending
    }

    private fun fetchPendingJobs() {
        Log.d("MainActivity", "fetchPendingJobs() called")
        adapter.submitList(emptyList())
        if (checkConnection(this)) {
            binding.swipeRefresh.isRefreshing = true
            lifecycleScope.launch {
                viewModel.trabajosPendientes.collect { trabajos ->
                    Log.d("MainActivity", "Número de trabajos recuperados: ${trabajos.size}")
                    adapter.submitList(trabajos)
                    trabajosPendientesList.addAll(trabajos)
                }
            }
            binding.swipeRefresh.isRefreshing = false
        } else {
            binding.swipeRefresh.isRefreshing = false
            Toast.makeText(
                this@MainActivity,
                getString(R.string.txt_noConnection),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun fetchFinishedJobs() {
        adapter.submitList(emptyList())
        if (checkConnection(this)) {
            binding.swipeRefresh.isRefreshing = true
            lifecycleScope.launch {
                viewModel.trabajosFinalizados.collect { trabajos ->
                    adapter.submitList(trabajos)
                    trabajosFinalizadosList.addAll(trabajos)
                }
            }
            binding.swipeRefresh.isRefreshing = false
        } else {
            binding.swipeRefresh.isRefreshing = false
            Toast.makeText(
                this@MainActivity,
                getString(R.string.txt_noConnection),
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}
