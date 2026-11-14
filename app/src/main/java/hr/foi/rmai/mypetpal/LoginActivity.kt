package hr.foi.rmai.mypetpal

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import hr.foi.rmai.mypetpal.database.MyPetPalDatabase
import hr.foi.rmai.mypetpal.database.UserDAO
import hr.foi.rmai.mypetpal.ui.theme.MyPetPalTheme

class LoginActivity : ComponentActivity() {
    private lateinit var btnLogin: Button
    private lateinit var username: EditText
    private lateinit var password: EditText
    private lateinit var userDAO: UserDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userDAO = MyPetPalDatabase.getInstance(this).getUsersDao()
        setContent {
            MyPetPalTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    setContentView(R.layout.activity_login)
                    username = findViewById(R.id.usernameInput)
                    password = findViewById(R.id.passwordInput)
                    btnLogin = findViewById(R.id.sign_in)

                    btnLogin.setOnClickListener {
                        loginUser(
                            username.text.toString(),
                            password.text.toString()
                        )
                    }
                }
            }
        }
    }

    private fun loginUser(username: String, password: String) {
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please fill in all fields!", Toast.LENGTH_SHORT).show()
        } else {
            val user = userDAO.getUserByUsername(username)
            if (user != null && user.password == password) {
                Toast.makeText(this, "Logged in Successfully!", Toast.LENGTH_SHORT).show()
                saveUserId(user.id)
                val intent = Intent(this, PetPalsActivity::class.java)
                intent.putExtra("USER_ID", user.id)
                startActivity(intent)
            } else {
                Toast.makeText(
                    this,
                    "Unsuccessful login. Please check your username and password.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun saveUserId(userId: Int) {
        val sharedPref = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putInt("user_id", userId)
            apply()
        }
    }
}