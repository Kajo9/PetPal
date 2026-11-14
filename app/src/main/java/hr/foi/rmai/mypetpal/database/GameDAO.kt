package hr.foi.rmai.mypetpal.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import hr.foi.rmai.mypetpal.entities.Game

@Dao
interface GameDAO {
    @Query("SELECT highScore FROM game WHERE id = 1")
    fun getHighScore(): Int?
    @Insert
    fun insertHighScore(vararg highScore: Game)
    @Update
    fun updateHighScore(highScore: Game)
}