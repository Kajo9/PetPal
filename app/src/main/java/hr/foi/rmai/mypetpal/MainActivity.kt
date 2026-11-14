package hr.foi.rmai.mypetpal

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import hr.foi.rmai.mypetpal.database.MyPetPalDatabase
import hr.foi.rmai.mypetpal.helpers.MockDataLoader
import hr.foi.rmai.mypetpal.ui.theme.MyPetPalTheme

class MainActivity : ComponentActivity() {
    private lateinit var btnSignIn: Button
    private lateinit var btnSignUp: Button
    private lateinit var btnGame: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MyPetPalDatabase.buildInstance(applicationContext)
        MockDataLoader.loadMockData(this)
        setContent {
            MyPetPalTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    setContentView(R.layout.activity_welcome)
                    userLogin()
                    userRegistration()
                    game()
                }
            }
        }
    }

    private fun userLogin() {
        btnSignIn = findViewById(R.id.signInButton)
        btnSignIn.setOnClickListener {
            val intent: Intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun userRegistration() {
        btnSignUp = findViewById(R.id.signUpButton)
        btnSignUp.setOnClickListener {
            val intent: Intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
        }
    }

    private fun game() {
        btnGame = findViewById(R.id.gameButton)
        btnGame.setOnClickListener {
            val intent: Intent = Intent(this, GameActivity::class.java)
            startActivity(intent)
        }
    }
}