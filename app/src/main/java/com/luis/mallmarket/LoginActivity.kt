package com.luis.mallmarket

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.FirebaseApp
import com.luis.mallmarket.interfaces.ResponseLogin
import com.luis.mallmarket.viewmodel.LoginActivityViewModel
import java.lang.Exception

class LoginActivity : AppCompatActivity() {

    private lateinit var userEmail: TextInputEditText
    private lateinit var userPassword: TextInputEditText
    private  lateinit var btnLogIn: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar!!.hide()
        userEmail = findViewById(R.id.userEmail)
        userPassword = findViewById(R.id.userPassword)
        btnLogIn = findViewById(R.id.btnLogin)

        var model = ViewModelProviders.of(this).get(LoginActivityViewModel::class.java)


        btnLogIn.setOnClickListener {
            var context = this
            model.logInUser(userEmail.text.toString(),userPassword.text.toString(),object : ResponseLogin<String>{

                override fun getResponse(result: String) {

                    if (!result.isNullOrEmpty()){
                        Toast.makeText(context,result,Toast.LENGTH_LONG).show()
                    }
                    else{
                        Toast.makeText(context,"WELCOME USER",Toast.LENGTH_LONG).show()
                    }
                }

                override fun throwException(ex: Exception) {
                    Toast.makeText(context,ex.message,Toast.LENGTH_LONG).show()
                }

            })
        }
    }
}