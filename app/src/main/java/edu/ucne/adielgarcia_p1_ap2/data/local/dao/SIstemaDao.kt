package edu.ucne.adielgarcia_p1_ap2.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import edu. ucne. adielgarcia_p1_ap2.data. local. entities. SistemaEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SistemaDao {
    @Upsert
    suspend fun save(sistema: SistemaEntity)

    @Query("""
        SELECT * FROM Sistema
        WHERE SistemaId = :sistemaId
    """)
    suspend fun find(sistemaId: Int): SistemaEntity?

    @Delete
    suspend fun delete(sistema: SistemaEntity)

    @Query("SELECT * FROM Sistema")
    fun getAll(): Flow<List<SistemaEntity>>
}