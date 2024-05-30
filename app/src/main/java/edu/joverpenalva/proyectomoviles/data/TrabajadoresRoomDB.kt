package edu.joverpenalva.proyectomoviles.data

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RoomDatabase
import edu.joverpenalva.proyectomoviles.model.trabajadores.Result

@Database(entities = [Result::class], version = 1)
abstract class TrabajadoresRoomDB : RoomDatabase() {
    abstract fun trabajadoresDao(): TrabajadoresDao
}

@Dao
interface TrabajadoresDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTrabajador(trabajador: Result)

    @Query("SELECT * FROM result LIMIT 1")
    suspend fun getTrabajador(): Result?

    @Delete
    suspend fun deleteTrabajador(trabajador: Result)
}