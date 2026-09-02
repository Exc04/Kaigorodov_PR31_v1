package com.example.kaigorodov_pr_31_v1

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var sharedPrefs: SharedPreferences
    private lateinit var editTextLogin: EditText
    private lateinit var  editTextPasswor: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPrefs = getSharedPreferences("MyPrfs", Context.MODE_PRIVATE)
        editTextLogin = findViewById(R.id.Login)
        editTextPasswor = findViewById(R.id.Password)
        val buttonReg = findViewById<Button>(R.id.buttonReg)


        val savedLogin = sharedPrefs.getString("login", "")
        val savePassword = sharedPrefs.getString("password", "")

        buttonReg.setOnClickListener{
            val login = editTextLogin.text.toString().trim()
            val password = editTextPasswor.text.toString().trim()

            if (login.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Введите логин и пароль", Toast.LENGTH_SHORT).show()

                return@setOnClickListener
            }

            if (savedLogin.isNullOrEmpty() && savePassword.isNullOrEmpty()) {
                sharedPrefs.edit().apply {
                    putString("login", login)
                    putString("password", password)
                    apply()
                }

                val intent = Intent(this, SecondActivity::class.java)
                startActivity(intent)
                finish()
            } else {

                if (login == savedLogin && password == savePassword) {
                    val intent = Intent(this, SecondActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this,"Неверный логин или пароль", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}