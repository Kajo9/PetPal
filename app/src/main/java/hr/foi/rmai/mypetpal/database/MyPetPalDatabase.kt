package hr.foi.rmai.mypetpal.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import hr.foi.rmai.mypetpal.entities.Game
import hr.foi.rmai.mypetpal.entities.User
import hr.foi.rmai.mypetpal.entities.Pet
import hr.foi.rmai.mypetpal.entities.UserPet

@Database(entities = [User::class, Pet::class, UserPet::class, Game::class], version = 3)
abstract class MyPetPalDatabase : RoomDatabase() {
    abstract fun getPetsDao(): PetDAO
    abstract fun getUsersDao(): UserDAO
    abstract fun getUsersPetsDao(): UserPetDAO
    abstract fun getGameDao(): GameDAO

    companion object {
        @Volatile
        private var INSTANCE: MyPetPalDatabase? = null

        fun getInstance(context: Context): MyPetPalDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MyPetPalDatabase::class.java,
                    "my_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }

        fun buildInstance(context: Context) {
            if (INSTANCE == null) {
                val instanceBuilder = Room.databaseBuilder(
                    context,
                    MyPetPalDatabase::class.java,
                    "petpal.db"
                )

                instanceBuilder.fallbackToDestructiveMigration()
                instanceBuilder.allowMainThreadQueries()

                INSTANCE = instanceBuilder.build()
            }
        }
    }
}