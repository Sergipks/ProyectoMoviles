package edu.joverpenalva.proyectomoviles

import android.app.Application
import androidx.room.Room
import edu.joverpenalva.proyectomoviles.data.TrabajadoresRoomDB

class TrabajadorApplication : Application() {
    lateinit var trabajadorDB: TrabajadoresRoomDB
        private set

    override fun onCreate() {
        super.onCreate()
        trabajadorDB = Room.databaseBuilder(
            this,
            TrabajadoresRoomDB::class.java, "words.db"
        ).build()
    }
}