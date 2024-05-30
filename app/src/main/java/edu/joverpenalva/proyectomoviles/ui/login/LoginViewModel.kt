package edu.joverpenalva.proyectomoviles.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import edu.joverpenalva.proyectomoviles.data.Repository
import edu.joverpenalva.proyectomoviles.model.trabajadores.Result
import kotlinx.coroutines.launch

class LoginViewModel(val repository: Repository) : ViewModel() {
    private var _APITrabajadores = repository.fetchAPITrabajadores()
    val APITrabajadores
        get() = _APITrabajadores

    private var _DBTrabajador = repository.fetchTrabajador()
    val DBTrabajador
        get() = _DBTrabajador

    fun saveUser(trabajador: Result){
        viewModelScope.launch {
            repository.saveTrabajador(trabajador)
        }
    }
}

@Suppress("UNCHECKED_CAST")
class LoginViewModelFactory(
    private val repository: Repository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoginViewModel(repository) as T
    }
}