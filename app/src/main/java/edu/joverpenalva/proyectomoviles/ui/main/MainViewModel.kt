package edu.joverpenalva.proyectomoviles.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import edu.joverpenalva.proyectomoviles.data.Repository
import edu.joverpenalva.proyectomoviles.model.trabajos.Result
import edu.joverpenalva.proyectomoviles.usuario
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository) : ViewModel() {

    private val _trabajosPendientes: Flow<List<Result>> = repository.fetchAPITrabajosPendientes(
        usuario!!.idTrabajador, usuario!!.contraseña)
    val trabajosPendientes
        get() = _trabajosPendientes

    private val _trabajosFinalizados: Flow<List<Result>> = repository.fetchAPITrabajosFinalizados(
        usuario!!.idTrabajador, usuario!!.contraseña)
    val trabajosFinalizados
        get() = _trabajosFinalizados

}

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(private val repository: Repository)
    : ViewModelProvider.Factory {
        override fun <T: ViewModel> create(modelClass: Class<T>): T {
            return MainViewModel(repository) as T
        }
}
