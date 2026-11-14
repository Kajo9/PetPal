package hr.foi.rmai.mypetpal.helpers

import android.content.Context
import hr.foi.rmai.mypetpal.database.MyPetPalDatabase
import hr.foi.rmai.mypetpal.entities.Pet

object MockDataLoader {
    fun loadMockData(context: Context) {
        val petsDao = MyPetPalDatabase.getInstance(context).getPetsDao()

        if (petsDao.getAllPets().isEmpty()) {
            val pets = arrayOf(
                Pet(
                    1,
                    "Golden Retriever",
                    "Dog",
                    12,
                    30.0,
                    60.0,
                    "Golden, Cream",
                    "Friendly and intelligent dog breed.",
                    "Balanced commercial food with fresh water.",
                    "Brush regularly, bathe as needed.",
                    "Trim nails regularly.",
                    "Clean teeth and ears regularly."
                ),
                Pet(
                    2,
                    "Siamese",
                    "Cat",
                    15,
                    5.0,
                    30.0,
                    "White, Gray, Black",
                    "Siamese cats are social, intelligent, and have a strong desire to communicate with their human companions.",
                    "High-quality food rich in proteins.",
                    "Brush to prevent hairballs and matting.",
                    "Trim claws, use scratching posts.",
                    "Clean litter box daily, maintain dental care."
                ),
                Pet(
                    3,
                    "Budgerigar",
                    "Bird",
                    15,
                    0.05,
                    0.2,
                    "Green, Yellow, Blue",
                    "Budgerigars are popular pets around the world due to their small size, low cost, and ability to mimic human speech.",
                    "Seeds, fruits, vegetables, and commercial food.",
                    "Gently clean feathers, provide bathing opportunities.",
                    "Trim claws monthly.",
                    "Clean cage daily."
                ),
                Pet(
                    4,
                    "Goldfish",
                    "Fish",
                    10,
                    0.2,
                    0.1,
                    "Orange, White, Orange & White",
                    "Goldfish are a popular pet fish species due to their bright colors, ease of care, and ability to live in a wide range of water conditions.",
                    "High-quality flakes or pellets, supplement with live food.",
                    "Not applicable.",
                    "Not applicable.",
                    "Regular water changes, clean tank."
                ),
                Pet(
                    5,
                    "Bearded Dragon",
                    "Reptile",
                    10,
                    0.5,
                    0.5,
                    "Brown, Green",
                    "Bearded dragons are popular pet reptiles due to their friendly nature, ease of care, and ability to thrive in captivity.",
                    "Species-specific diet (insects, veggies, fruits).",
                    "Not applicable.",
                    "Trim nails as needed, provide natural surfaces.",
                    "Clean habitat regularly, maintain temperature and humidity."
                ),
                Pet(
                    6,
                    "Chinchilla",
                    "Rodent",
                    15,
                    0.5,
                    0.3,
                    "Gray, White",
                    "Chinchillas are popular pets due to their soft fur, playful nature, and ability to form strong bonds with their human companions.",
                    " Balanced food, fresh veggies, occasional fruits.",
                    "Brush regularly, especially long-haired types.",
                    "Provide materials to wear down claws.",
                    "Clean cage and dishes regularly."
                )
            )
            petsDao.insertPet(*pets)
        }
    }
}