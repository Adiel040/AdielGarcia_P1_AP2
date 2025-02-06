package edu.ucne.adielgarcia_p1_ap2.data.repository

import edu.ucne.adielgarcia_p1_ap2.data.local.dao.SistemaDao
import edu.ucne.adielgarcia_p1_ap2.data.local.entities.SistemaEntity
import javax.inject.Inject

class SistemaRepository @Inject constructor(
    private val sistemadao: SistemaDao
) {
    suspend fun save(sistema: SistemaEntity) = sistemadao.save(sistema)
    suspend fun find(id: Int) = sistemadao.find(id)
    suspend fun delete(sistema: SistemaEntity) = sistemadao.delete(sistema)
    fun getAll() = sistemadao.getAll()
}