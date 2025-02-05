package edu.ucne.adielgarcia_p1_ap2.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Entities")
data class Entity(
    //Cambiar en el examen
    @PrimaryKey
    val entityId: Int?
)
