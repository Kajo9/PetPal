package hr.foi.rmai.mypetpal

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import hr.foi.rmai.mypetpal.database.MyPetPalDatabase
import hr.foi.rmai.mypetpal.database.PetDAO
import hr.foi.rmai.mypetpal.entities.Pet
import hr.foi.rmai.mypetpal.ui.theme.MyPetPalTheme

class PetProfileActivity : ComponentActivity() {
    private lateinit var petDAO: PetDAO
    private lateinit var pet: Pet
    private lateinit var petCareButton: Button
    private var petId = -1
    private var petName = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        petDAO = MyPetPalDatabase.getInstance(this).getPetsDao()
        petId = intent.getIntExtra("PET_ID", -1)
        petName = intent.getStringExtra("PET_NAME").toString()
        pet = petDAO.getPet(petId)

        setContent {
            MyPetPalTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    setContentView(R.layout.activity_pet_profile)
                    findViewById<TextView>(R.id.title).apply {
                        text = petName
                    }
                    findViewById<TextView>(R.id.breed).apply {
                        text = pet.breed
                    }
                    findViewById<TextView>(R.id.type).apply {
                        text = pet.type
                    }
                    findViewById<TextView>(R.id.life_expectancy).apply {
                        text = buildString {
                            append(pet.lifeExpectancy)
                            append(" years")
                        }
                    }
                    findViewById<TextView>(R.id.weight).apply {
                        text = buildString {
                            append(pet.weight)
                            append(" kg")
                        }
                    }
                    findViewById<TextView>(R.id.height).apply {
                        text = buildString {
                            append(pet.height)
                            append(" cm")
                        }
                    }
                    findViewById<TextView>(R.id.colors).apply {
                        text = pet.colors
                    }
                    findViewById<TextView>(R.id.description).apply {
                        text = pet.description
                    }
                    petCareButton = findViewById(R.id.pet_care_button)
                    petCareButton.setOnClickListener {
                        val intent: Intent = Intent(this, PetCareActivity::class.java)
                        intent.putExtra("PET_DIET", pet.diet)
                        intent.putExtra("PET_FUR", pet.furCare)
                        intent.putExtra("PET_CLAW", pet.clawMaintenance)
                        intent.putExtra("PET_HYGIENE", pet.hygieneAndCleanliness)
                        startActivity(intent)
                    }
                }
            }
        }
    }
}