package hr.foi.rmai.mypetpal.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")

data class User(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val username: String,
    val password: String,
    val email: String
) {
    override fun toString(): String {
        return username
    }
}
