package com.example.lab6

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.room.Room
import com.example.lab6.data.AppDatabase
import com.example.lab6.data.User
import java.util.regex.Matcher
import java.util.regex.Pattern

class SignupActivity : AppCompatActivity() {

    private lateinit var signUpUsername: EditText
    private lateinit var signUpPassword: EditText
    private lateinit var signUpBtn: Button
    private lateinit var signUpToLogIn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        signUpUsername = findViewById(R.id.signup_username)
        signUpPassword = findViewById(R.id.signup_password)
        signUpToLogIn = findViewById(R.id.signUpToLogIn)
        signUpBtn = findViewById(R.id.signup_button)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "app-database"
        ).allowMainThreadQueries().build()

        val userDao = db.userDao()

        signUpBtn.setOnClickListener {

            if(signUpPassword.text.toString().trim().length<8 && !isValidPassword(signUpPassword.text.toString().trim())){
                Toast.makeText(baseContext,"Invalid Password", Toast.LENGTH_SHORT).show()
            }else{
                val tempUser = User(signUpUsername.text.toString().trim(), signUpPassword.text.toString().trim())
                userDao.insert(tempUser)
                Toast.makeText(baseContext,"Sign Up Successful", Toast.LENGTH_SHORT).show()
            }
        }

        signUpToLogIn.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun isValidPassword(password: String?): Boolean {
        val pattern: Pattern
        val passwordPattern = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!]).{8,}$"
        pattern = Pattern.compile(passwordPattern)
        val matcher: Matcher = pattern.matcher(password)
        return matcher.matches()
    }
}