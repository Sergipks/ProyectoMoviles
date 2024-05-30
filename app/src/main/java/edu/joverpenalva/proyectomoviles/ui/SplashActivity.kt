package edu.joverpenalva.proyectomoviles.ui

import android.content.Intent
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import edu.joverpenalva.proyectomoviles.*
import edu.joverpenalva.proyectomoviles.ui.main.*

class SplashActivity : AppCompatActivity() {
    private val SPLASH_DISPLAY_LENGTH = 2000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, SPLASH_DISPLAY_LENGTH.toLong())
    }
}
