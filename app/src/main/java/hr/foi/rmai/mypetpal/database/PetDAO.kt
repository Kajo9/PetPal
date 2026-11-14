package hr.foi.rmai.mypetpal.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import hr.foi.rmai.mypetpal.entities.Pet

@Dao
interface PetDAO {
    @Query("SELECT * FROM pet")
    fun getAllPets(): List<Pet>

    @Query("SELECT * FROM pet WHERE id = :id")
    fun getPet(id: Int): Pet

    @Query("SELECT * FROM pet WHERE type = :type")
    fun getPetsByType(type: String): List<Pet>

    @Insert
    fun insertPet(vararg pet: Pet): List<Long>
}