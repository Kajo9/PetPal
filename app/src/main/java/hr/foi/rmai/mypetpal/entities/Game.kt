package hr.foi.rmai.mypetpal.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "game")

data class Game(
    @PrimaryKey val id: Int = 1,
    val highScore: Int?
)