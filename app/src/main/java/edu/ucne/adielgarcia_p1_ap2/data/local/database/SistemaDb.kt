package edu.ucne.adielgarcia_p1_ap2.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import edu.ucne.adielgarcia_p1_ap2.data.local.dao.SistemaDao
import edu.ucne.adielgarcia_p1_ap2.data.local.entities.SistemaEntity


@Database(
    entities = [SistemaEntity::class],
    version = 1,
    exportSchema = false
)
abstract  class SistemaDb: RoomDatabase() {
    abstract fun sistemaDao(): SistemaDao
}