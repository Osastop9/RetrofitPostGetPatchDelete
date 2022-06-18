package com.example.retrofitpostgetdeletepatch

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_create_new_user.*

class CreateNewUserActivity : AppCompatActivity() {

    lateinit var viewModel: CreateNewUserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_new_user)

        initViewModel()
        createUserObservable()

        createButton.setOnClickListener {
            createUser()
        }
    }

    private fun createUser(){
         val user = User("", editTextName.text.toString(),editTextEmail.text.toString(),  "Active", "Male")
        viewModel.createUser(user)
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(CreateNewUserViewModel ::class.java)
    }

    private fun createUserObservable() {
        viewModel.getcreateNewUserObservable().observe(
            this,
            Observer<UserResponse?> {
                if (it == null) {
                    Toast.makeText(this, "Failed to create new user...", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "User successfully created", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        )
    }
}
