package hr.foi.rmai.mypetpal.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "user_pet",
    foreignKeys = [
        ForeignKey(entity = User::class,
            parentColumns = ["id"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE),
        ForeignKey(entity = Pet::class,
            parentColumns = ["id"],
            childColumns = ["petId"],
            onDelete = ForeignKey.CASCADE)
    ]
)
data class UserPet(
    @PrimaryKey(autoGenerate = true)
    val userPetId: Int,
    val userId: Int,
    val petId: Int,
    val petName: String
) {
    override fun toString(): String {
        return petName
    }
}