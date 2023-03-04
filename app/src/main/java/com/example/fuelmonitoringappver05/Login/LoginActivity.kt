package com.example.fuelmonitoringappver05.Login

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.fuelmonitoringappver05.MainActivity
import com.example.fuelmonitoringappver05.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        var registerText : TextView = binding.tvNoAccount

        registerText.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        val loginButton : Button = binding.btnLogin
        loginButton.setOnClickListener {
            performLogin()
        }
    }


    private fun performLogin() {
        val email = binding.etUsernameEmailLogin
        val password = binding.etPasswordLogin

        if(email.text.isEmpty() || password.text.isEmpty()){
            Toast.makeText(this, "Please fill up all fields", Toast.LENGTH_SHORT).show()
            return
        }

        val inputEmail = email.text.toString()
        val inputPassword = password.text.toString()

        auth.signInWithEmailAndPassword(inputEmail, inputPassword)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in Success, Navigate to the Main Activity
                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("auth",auth.uid)
                    intent.putExtra("email",inputEmail)
                    startActivity(intent)

                    Toast.makeText(baseContext, "Success!!",Toast.LENGTH_SHORT).show()

                } else {

                    Toast.makeText(baseContext, "Authentication failed.", Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener{
                Toast.makeText(baseContext, "Authentication failed ${it.localizedMessage}", Toast.LENGTH_SHORT).show()
            }
    }
}