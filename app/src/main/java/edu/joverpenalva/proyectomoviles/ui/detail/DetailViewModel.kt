package edu.joverpenalva.proyectomoviles.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import edu.joverpenalva.proyectomoviles.data.Repository
import edu.joverpenalva.proyectomoviles.model.trabajos.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class DetailViewModel(val repository: Repository, val codTrabajo: String) : ViewModel() {
    val trabajo: Flow<Result> = repository.fetchTrabajoById(codTrabajo)

}

@Suppress("UNCHECKED_CAST")
class DetailViewModelFactory(private val repository: Repository, private
val codTrabajo: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailViewModel(repository, codTrabajo) as T
    }
}