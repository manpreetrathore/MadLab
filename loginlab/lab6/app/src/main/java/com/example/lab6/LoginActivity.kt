package com.example.lab6

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.room.Room
import com.example.lab6.data.AppDatabase

class LoginActivity : AppCompatActivity() {

    private lateinit var logInUsername: EditText
    private lateinit var logInPassword: EditText
    private lateinit var logInBtn: Button
    private lateinit var logInToSignup: Button
    private var counter = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        logInUsername = findViewById(R.id.login_username)
        logInPassword = findViewById(R.id.login_password)
        logInToSignup = findViewById(R.id.login_to_signup)
        logInBtn = findViewById(R.id.logInButton)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "app-database"
        ).allowMainThreadQueries().build()

        val userDao = db.userDao()

        logInBtn.setOnClickListener {

            val tempUser = userDao.getUser(logInUsername.text.toString().trim())

            if(tempUser.password.equals(logInPassword.text.toString().trim())){
                val intent = Intent(this, HomePage::class.java)
                startActivity(intent)
            }
            else{
                Toast.makeText(baseContext,"Invalid Username Or Password", Toast.LENGTH_SHORT).show()
                counter++;
                if (counter == 2){
                    logInBtn.isEnabled = false;
                }
            }
        }

        logInToSignup.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}