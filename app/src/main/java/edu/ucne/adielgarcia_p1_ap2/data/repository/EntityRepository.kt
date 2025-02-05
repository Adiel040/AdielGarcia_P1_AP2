package edu.ucne.adielgarcia_p1_ap2.data.repository

import edu.ucne.adielgarcia_p1_ap2.data.local.dao.Dao
import edu.ucne.adielgarcia_p1_ap2.data.local.entities.Entity
import javax.inject.Inject

class EntityRepository @Inject constructor(
    private val dao: Dao
) {
    suspend fun save(entity: Entity) = dao.save(entity)
    suspend fun find(id: Int) = dao.find(id)
    suspend fun delete(entity: Entity) = dao.delete(entity)
    fun getAll() = dao.getAll()
}