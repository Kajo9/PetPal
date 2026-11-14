package hr.foi.rmai.mypetpal

import android.os.Bundle
import android.text.TextUtils
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import hr.foi.rmai.mypetpal.database.MyPetPalDatabase
import hr.foi.rmai.mypetpal.database.PetDAO
import hr.foi.rmai.mypetpal.database.UserPetDAO
import hr.foi.rmai.mypetpal.entities.UserPet
import hr.foi.rmai.mypetpal.helpers.HintAdapter
import hr.foi.rmai.mypetpal.ui.theme.MyPetPalTheme

class AddPetActivity : ComponentActivity() {
    private lateinit var spinner: Spinner
    private lateinit var petName: EditText
    private lateinit var btnConfirm: Button
    private lateinit var petDAO: PetDAO
    private lateinit var userPetDAO: UserPetDAO
    private lateinit var userPetEntity: UserPet
    private var userId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        petDAO = MyPetPalDatabase.getInstance(this).getPetsDao()
        userPetDAO = MyPetPalDatabase.getInstance(this).getUsersPetsDao()
        userId = intent.getIntExtra("USER_ID", -1)
        setContent {
            MyPetPalTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    setContentView(R.layout.activity_pet_select)
                    spinner = findViewById(R.id.pet_spinner)
                    petName = findViewById(R.id.pet_name_input)
                    btnConfirm = findViewById(R.id.confirm_button)

                    val petList = petDAO.getAllPets().map { it.breed }
                    val items = listOf("Select Breed") + petList

                    val adapter = HintAdapter(
                        this@AddPetActivity,
                        android.R.layout.simple_spinner_item,
                        items
                    )
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spinner.adapter = adapter

                    var selectedBreedId: Int? = null

                    spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(
                            parent: AdapterView<*>,
                            view: android.view.View?,
                            position: Int,
                            id: Long
                        ) {
                            selectedBreedId = if (position == 0) null else position
                        }

                        override fun onNothingSelected(parent: AdapterView<*>) {
                            // Handle case where nothing is selected
                        }
                    }

                    btnConfirm.setOnClickListener {
                        val insertedPetName = petName.text.toString()
                        val errorMessage = validateInput(insertedPetName, selectedBreedId)

                        if (errorMessage == null) {
                            val breedId = selectedBreedId ?: 0
                            userPetEntity = UserPet(0, userId, breedId, insertedPetName)
                            insertUserPet()
                        } else {
                            Toast.makeText(this@AddPetActivity, errorMessage, Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }
            }
        }
    }

    private fun validateInput(petName: String, breedId: Int?): String? {
        return when {
            TextUtils.isEmpty(petName) -> "Please insert pet name"
            breedId == null -> "Please select a breed"
            else -> null
        }
    }

    private fun insertUserPet() {
        userPetDAO.insertUserPet(userPetEntity)
        Toast.makeText(this@AddPetActivity, "Pet successfully added", Toast.LENGTH_LONG).show()
        setResult(RESULT_OK)
        finish()
    }
}