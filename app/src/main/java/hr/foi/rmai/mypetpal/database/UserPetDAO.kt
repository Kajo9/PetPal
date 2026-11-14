package hr.foi.rmai.mypetpal.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import hr.foi.rmai.mypetpal.entities.UserPet

@Dao
interface UserPetDAO {
    @Query("SELECT * FROM user_pet WHERE userId = :id")
    fun getAllUserPets(id: Int): List<UserPet>

    @Query("SELECT * FROM user_pet WHERE userId = :userId AND petId = :petId")
    fun getUserPet(userId: Int, petId: Int): UserPet

    @Query("SELECT COUNT(*) FROM user_pet WHERE userId = :id")
    fun getUserPetCount(id: Int): Int

    @Insert
    fun insertUserPet(vararg userPet: UserPet): List<Long>

    @Delete
    fun removeUserPet(vararg userPet: UserPet)

    @Update
    fun updateUserPet(userPet: UserPet)
}