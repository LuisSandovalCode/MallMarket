package com.luis.mallmarket

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.luis.mallmarket.interfaces.ResponseView
import com.luis.mallmarket.services.toast.toastService
import com.luis.mallmarket.view.activity.NewUser
import com.luis.mallmarket.viewmodel.LoginActivityViewModel
import java.lang.Exception

class LoginActivity : AppCompatActivity() {

    private lateinit var userEmail: TextInputEditText
    private lateinit var userPassword: TextInputEditText
    private  lateinit var btnLogIn: MaterialButton
    private lateinit var btnNewUser: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar!!.hide()
        userEmail = findViewById(R.id.userEmail)
        userPassword = findViewById(R.id.userPassword)
        btnLogIn = findViewById(R.id.btnLogin)
        btnNewUser = findViewById(R.id.btnNewUser)

        var model = ViewModelProviders.of(this).get(LoginActivityViewModel::class.java)


        btnLogIn.setOnClickListener {
            var context = this
            model.logInUser(userEmail.text.toString(),userPassword.text.toString(),object : ResponseView<String>{

                override fun getResponse(result: String) {

                    if (!result.isNullOrEmpty()){
                        toastService.showMessage(result,applicationContext)
                    }
                    else{
                        toastService.showMessage("WELCOME USER",applicationContext)
                    }
                }

                override fun throwException(ex: Exception) {
                    toastService.showMessage(ex.message.toString(),applicationContext)
                }

            })
        }

        btnNewUser.setOnClickListener{
            intent = Intent(this, NewUser::class.java)
            startActivity(intent!!)
        }
    }
}