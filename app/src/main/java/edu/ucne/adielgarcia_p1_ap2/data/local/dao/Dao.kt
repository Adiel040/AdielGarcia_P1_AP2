package edu.ucne.adielgarcia_p1_ap2.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import edu.ucne.adielgarcia_p1_ap2.data.local.entities.Entity
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {
    @Upsert
    suspend fun save(entity: Entity)

    @Query("""
        SELECT * FROM Entities
        WHERE entityId = :entityId
    """)
    suspend fun find(entityId: Int): Entity?

    @Delete
    suspend fun delete(entity: Entity)

    @Query("SELECT * FROM Entities")
    fun getAll(): Flow<List<Entity>>
}