package edu.ucne.adielgarcia_p1_ap2.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Sistema")
data class SistemaEntity(

    @PrimaryKey
    val SistemaId: Int?,
    val Nombre: String
)
