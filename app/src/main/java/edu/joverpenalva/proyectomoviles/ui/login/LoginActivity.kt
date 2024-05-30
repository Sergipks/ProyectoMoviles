package edu.joverpenalva.proyectomoviles.ui.login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import edu.joverpenalva.proyectomoviles.R
import edu.joverpenalva.proyectomoviles.data.RemoteDataSource
import edu.joverpenalva.proyectomoviles.data.Repository
import edu.joverpenalva.proyectomoviles.databinding.ActivityLoginBinding
import edu.joverpenalva.proyectomoviles.TrabajadorApplication
import edu.joverpenalva.proyectomoviles.checkConnection
import edu.joverpenalva.proyectomoviles.model.trabajadores.Result
import edu.joverpenalva.proyectomoviles.ui.main.MainActivity
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var trabajadores: List<Result>
    private var trabajador: Result? = null

    // ViewModel obtenido a través de viewModels
    private val vm: LoginViewModel by viewModels {
        val db = (application as TrabajadorApplication).trabajadorDB
        val ds = RemoteDataSource()
        LoginViewModelFactory(Repository(db, ds))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getTrabajador()
        val intent = Intent(this, MainActivity::class.java)

        if(trabajador != null)
            startActivity(intent)

        // Deshabilitar el botón inicialmente
        binding.btnAcceder.isEnabled = false

        // TextWatcher para monitorear cambios en los campos de texto
        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Habilitar o deshabilitar el botón dependiendo del contenido de los campos
                binding.btnAcceder.isEnabled = binding.tiedLogin.text.toString().isNotEmpty() &&
                        binding.tiedPassword.text.toString().isNotEmpty()
            }

            override fun afterTextChanged(s: Editable?) {}
        }

        // Añadir el TextWatcher a ambos campos
        binding.tiedLogin.addTextChangedListener(textWatcher)
        binding.tiedPassword.addTextChangedListener(textWatcher)

        // Obtener la lista de trabajadores al iniciar la actividad
        getTrabajadores()

        // Configurar el listener del botón de acceder
        binding.btnAcceder.setOnClickListener {
            val login = binding.tiedLogin.text.toString()
            val password = binding.tiedPassword.text.toString()

            val trabajador = findTrabajador(login, password)

            // Lógica de autenticación
            if (trabajador != null ) {

                if(binding.cbRecordar.isActivated)
                    vm.saveUser(trabajador)

                // Iniciar MainActivity
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Método para obtener los trabajadores
    private fun getTrabajadores() {
        if (checkConnection(this)) { // Comprobar conexión a internet
            lifecycleScope.launch {
                vm.APITrabajadores.collect {
                    trabajadores = it
                }
            }
        } else {
            // Mostrar un mensaje si no hay conexión a internet
            Toast.makeText(
                this@LoginActivity,
                getString(R.string.txt_noConnection),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    // Método para obtener el usuario guardado
    private fun getTrabajador() {
        if (checkConnection(this)) { // Comprobar conexión a internet
            lifecycleScope.launch {
                vm.DBTrabajador.collect {
                    trabajador = it
                }
            }
        } else {
            // Mostrar un mensaje si no hay conexión a internet
            Toast.makeText(
                this@LoginActivity,
                getString(R.string.txt_noConnection),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    // Método para validar usuario
    private fun findTrabajador(login: String, password: String): Result? {
        // Verificar si los trabajadores están inicializados y no están vacíos
        if (::trabajadores.isInitialized) {
            // Comprobar si hay algún trabajador con el idTrabajador y contraseña dados
            for (trabajador in trabajadores) {
                if (trabajador.idTrabajador == login && trabajador.contraseña == password) {
                    return trabajador
                }
            }
        }
        return null
    }
}
