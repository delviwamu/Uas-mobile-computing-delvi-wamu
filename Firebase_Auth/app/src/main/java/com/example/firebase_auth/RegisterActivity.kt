package com.example.firebase_auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.example.firebase_auth.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding : ActivityRegisterBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.btnRegister.setOnClickListener {
            val email : String = binding.editTextTextEmailAddress.text.toString().trim()
            val password : String = binding.editTextTextPassword.text.toString().trim()
            val confirmPassword : String = binding.editTextTextPassword2.text.toString().trim()

            if (email.isEmpty()){
                binding.editTextTextEmailAddress.error = "Input Email"
                binding.editTextTextEmailAddress.requestFocus()
                return@setOnClickListener
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                binding.editTextTextEmailAddress.error = "Invalid email"
                binding.editTextTextEmailAddress.requestFocus()
                return@setOnClickListener
            }
            if (password.isEmpty() || password.length < 6){
                binding.editTextTextPassword.error = "password must be more than 6 characters"
                binding.editTextTextPassword.requestFocus()
                return@setOnClickListener
            }
            if (password != confirmPassword){
                binding.editTextTextPassword2.error = "password must be match"
                binding.editTextTextPassword2.requestFocus()
                return@setOnClickListener
            }
            registerUser(email, password)


        }
        binding.btnRegister.setOnClickListener {
            Intent(this, ContentActivity::class.java).also {
                startActivity(it)
            }
        }

        binding.txtLogin.setOnClickListener {
            Intent(this, MainActivity::class.java).also {
                startActivity(it)
            }
        }



    }

    private fun registerUser(email: String, password: String) {
        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener {
            if (it.isSuccessful) {
                Intent(this, ContentActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            }
            else {
                Toast.makeText(this, it.exception?.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        if (firebaseAuth.currentUser != null) {
            Intent(this, ContentActivity::class.java).also {
                it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(it)
            }
        }
    }
}