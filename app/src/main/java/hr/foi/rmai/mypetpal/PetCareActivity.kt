package hr.foi.rmai.mypetpal

import android.os.Bundle
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import hr.foi.rmai.mypetpal.ui.theme.MyPetPalTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier


class PetCareActivity : ComponentActivity() {
    private var diet = ""
    private var fur = ""
    private var claw = ""
    private var hygiene = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        diet = intent.getStringExtra("PET_DIET").toString()
        fur = intent.getStringExtra("PET_FUR").toString()
        claw = intent.getStringExtra("PET_CLAW").toString()
        hygiene = intent.getStringExtra("PET_HYGIENE").toString()

        setContent {
            MyPetPalTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    setContentView(R.layout.pet_care_tips)
                    findViewById<TextView>(R.id.diet).apply {
                       text = diet
                        }
                    findViewById<TextView>(R.id.fur_care).apply {
                        text = fur
                        }
                    findViewById<TextView>(R.id.claw_maintenance).apply {
                        text = claw
                    }
                    findViewById<TextView>(R.id.hygiene_and_cleanliness).apply {
                        text = hygiene
                    }
                }
            }
        }
    }
}