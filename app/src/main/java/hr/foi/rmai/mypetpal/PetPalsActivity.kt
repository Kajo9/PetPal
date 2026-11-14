package hr.foi.rmai.mypetpal

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import hr.foi.rmai.mypetpal.database.MyPetPalDatabase
import hr.foi.rmai.mypetpal.database.UserPetDAO
import hr.foi.rmai.mypetpal.entities.UserPet
import hr.foi.rmai.mypetpal.helpers.PetListAdapter
import hr.foi.rmai.mypetpal.ui.theme.MyPetPalTheme
import kotlinx.coroutines.*

class PetPalsActivity : ComponentActivity() {
    private lateinit var petList: RecyclerView
    private lateinit var addPetButton: Button
    private lateinit var userPetDAO: UserPetDAO
    private var userId: Int = -1
    private lateinit var userPets: List<UserPet>
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userPetDAO = MyPetPalDatabase.getInstance(this).getUsersPetsDao()
        userId = intent.getIntExtra("USER_ID", -1)

        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    coroutineScope.launch {
                        userPets = userPetDAO.getAllUserPets(userId)
                        withContext(Dispatchers.Main) {
                            petList.adapter = PetListAdapter(userPets)
                        }
                    }
                }
            }

        coroutineScope.launch {
            userPets = userPetDAO.getAllUserPets(userId)
            withContext(Dispatchers.Main) {
                setContent {
                    MyPetPalTheme {
                        Surface(
                            modifier = Modifier.fillMaxSize(),
                            color = MaterialTheme.colorScheme.background
                        ) {
                            setContentView(R.layout.activity_pet_list)

                            if (userPets.isEmpty()) {
                                val intent =
                                    Intent(this@PetPalsActivity, AddPetActivity::class.java)
                                intent.putExtra("USER_ID", userId)
                                resultLauncher.launch(intent)
                            }

                            petList = findViewById(R.id.pet_list)
                            addPetButton = findViewById(R.id.add_pet_button)

                            petList.adapter = PetListAdapter(userPets)
                            petList.layoutManager = LinearLayoutManager(this@PetPalsActivity)
                            petList.addItemDecoration(
                                DividerItemDecoration(
                                    this@PetPalsActivity,
                                    LinearLayoutManager.VERTICAL
                                )
                            )

                            addPetButton.setOnClickListener {
                                val intent =
                                    Intent(this@PetPalsActivity, AddPetActivity::class.java)
                                intent.putExtra("USER_ID", userId)
                                resultLauncher.launch(intent)
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineScope.cancel()
    }
}