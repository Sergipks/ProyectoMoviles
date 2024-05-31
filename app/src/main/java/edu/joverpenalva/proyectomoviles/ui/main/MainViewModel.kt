package edu.joverpenalva.proyectomoviles.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import edu.joverpenalva.proyectomoviles.data.Repository
import edu.joverpenalva.proyectomoviles.model.trabajos.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository) : ViewModel() {

    private val _trabajosPendientes = MutableStateFlow<List<Result>>(emptyList())
    val trabajosPendientes: StateFlow<List<Result>> get() = _trabajosPendientes

    private val _trabajosFinalizados = MutableStateFlow<List<Result>>(emptyList())
    val trabajosFinalizados: StateFlow<List<Result>> get() = _trabajosFinalizados

    fun fetchTrabajos() {
        viewModelScope.launch {
            val trabajador = repository.fetchTrabajador().firstOrNull()
            trabajador?.let {
                fetchTrabajosPendientes(it.idTrabajador, it.contraseña)
                fetchTrabajosFinalizados(it.idTrabajador, it.contraseña)
            }
        }
    }

    private fun fetchTrabajosPendientes(idTrabajador: String, contrasenya: String) {
        viewModelScope.launch {
            repository.fetchAPITrabajosPendientes(idTrabajador, contrasenya).collect {
                _trabajosPendientes.value = it
            }
        }
    }

    private fun fetchTrabajosFinalizados(idTrabajador: String, contrasenya: String) {
        viewModelScope.launch {
            repository.fetchAPITrabajosFinalizados(idTrabajador, contrasenya).collect {
                _trabajosFinalizados.value = it
            }
        }
    }
}

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(private val repository: Repository)
    : ViewModelProvider.Factory {
        override fun <T: ViewModel> create(modelClass: Class<T>): T {
            return MainViewModel(repository) as T
        }
}
