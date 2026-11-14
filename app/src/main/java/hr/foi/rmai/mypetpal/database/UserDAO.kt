package hr.foi.rmai.mypetpal.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import hr.foi.rmai.mypetpal.entities.User

@Dao
interface UserDAO {
    @Query("SELECT * FROM user WHERE id = :id")
    fun getUser(id: Int): User

    @Insert
    fun insertUser(vararg user: User): List<Long>

    @Delete
    fun removeUser(vararg user: User)

    @Update
    fun updateUser(user: User)

    @Query("SELECT * FROM user")
    fun getAllUsers(): List<User>

    @Query("SELECT * FROM user WHERE username = :username")
    fun getUserByUsername(username: String): User
}