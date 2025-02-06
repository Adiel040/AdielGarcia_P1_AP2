package edu.ucne.adielgarcia_p1_ap2.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import edu.ucne.adielgarcia_p1_ap2.data.local.dao.Dao
import edu.ucne.adielgarcia_p1_ap2.data.local.entities.Entity

@Database(
    entities = [Entity::class],
    version = 1,
    exportSchema = false
)
abstract  class EntityDatabase: RoomDatabase() {
    abstract fun entityDao(): Dao
}