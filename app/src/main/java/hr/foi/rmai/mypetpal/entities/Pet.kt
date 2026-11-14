package hr.foi.rmai.mypetpal.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pet")

data class Pet(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val breed: String,
    val type: String,
    val lifeExpectancy: Int,
    val weight: Double,
    val height: Double,
    val colors: String,
    val description: String,
    val diet: String,
    val furCare: String,
    val clawMaintenance: String,
    val hygieneAndCleanliness: String
) {
    override fun toString(): String {
        return breed
    }
}