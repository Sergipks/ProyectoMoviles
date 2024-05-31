package edu.joverpenalva.proyectomoviles.ui.main

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import edu.joverpenalva.proyectomoviles.R
import edu.joverpenalva.proyectomoviles.TrabajadorApplication
import edu.joverpenalva.proyectomoviles.checkConnection
import edu.joverpenalva.proyectomoviles.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as TrabajadorApplication).repository)
    }

    private val adapter = TrabajosAdapter(
        onClickTrabajo = {
            //DetailTrabajoActivity.navigate(this@MainActivity, it)
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Bloqueo de la rotación
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_NOSENSOR

        // Se infla el menú en la Toolbar
        binding.mToolbar.inflateMenu(R.menu.toolbar_menu)

        // Inflado del menú en la BottomNavigationView
        binding.bottomNav.inflateMenu(R.menu.toolbar_menu)

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

        // Fetch initial data
        fetchPendingJobs()

    }

    private fun fetchPendingJobs() {
        adapter.submitList(emptyList())
        if (checkConnection(this)) {
            binding.swipeRefresh.isRefreshing = true
            viewModel.fetchTrabajos()
            lifecycleScope.launch {
                viewModel.trabajosPendientes.collect { trabajos ->
                    adapter.submitList(trabajos)
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
            viewModel.fetchTrabajos()
            lifecycleScope.launch {
                viewModel.trabajosFinalizados.collect { trabajos ->
                    adapter.submitList(trabajos)
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
