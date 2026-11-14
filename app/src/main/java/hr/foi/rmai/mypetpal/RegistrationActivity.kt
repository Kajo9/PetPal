package hr.foi.rmai.mypetpal

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
import hr.foi.rmai.mypetpal.entities.User
import hr.foi.rmai.mypetpal.ui.theme.MyPetPalTheme

class RegistrationActivity : ComponentActivity() {
    private lateinit var btnRegister: Button
    private lateinit var username: EditText
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var confirmPassword: EditText
    private lateinit var userEntity: User
    private lateinit var userDAO: UserDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userDAO = MyPetPalDatabase.getInstance(this).getUsersDao()
        setContent {
            MyPetPalTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    setContentView(R.layout.activity_register)
                    username = findViewById(R.id.usernameInput)
                    email = findViewById(R.id.emailInput)
                    password = findViewById(R.id.passwordInput)
                    confirmPassword = findViewById(R.id.confirm_password_input)
                    btnRegister = findViewById(R.id.register_button)
                    btnRegister.setOnClickListener {
                        registerUser(
                            username.text.toString(),
                            email.text.toString(),
                            password.text.toString(),
                            confirmPassword.text.toString()
                        )
                    }
                }
            }
        }
    }
    private fun registerUser(username: String, email: String, password: String, confirmPassword: String) {
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(confirmPassword)) {
            Toast.makeText(this, "Fill out all fields!", Toast.LENGTH_SHORT).show()
        }
        else if (password != confirmPassword) {
            Toast.makeText(this, "Passwords do not match!", Toast.LENGTH_SHORT).show()
        }
        else if (userDAO.getUserByUsername(username) != null) {
            Toast.makeText(this, "Username already exists!", Toast.LENGTH_SHORT).show()
        }
        else {
            userEntity = User(0, username, password, email)
            insertUser()
        }
    }
    private fun insertUser() {
        userDAO.insertUser(userEntity)
        Toast.makeText(this, "Registration successful!", Toast.LENGTH_LONG).show()
        finish()
    }
}