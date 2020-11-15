package com.luis.mallmarket.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.luis.mallmarket.R
import com.luis.mallmarket.interfaces.ResponseView
import com.luis.mallmarket.model.User
import com.luis.mallmarket.services.toast.toastService
import com.luis.mallmarket.viewmodel.NewUserViewModel
import java.lang.Exception

class NewUser : AppCompatActivity() {


    private lateinit var userName:TextInputEditText
    private lateinit var userEmail:TextInputEditText
    private lateinit var userPassword:TextInputEditText
    private lateinit var btnSignUpUser: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_user)
        supportActionBar!!.hide()

        userName = findViewById(R.id.txtuserName)
        userEmail = findViewById(R.id.txtuserEmail)
        userPassword = findViewById(R.id.txtuserpassword)
        btnSignUpUser = findViewById(R.id.btnSignUp)

        var model = ViewModelProviders.of(this).get(NewUserViewModel::class.java)

        btnSignUpUser.setOnClickListener {
            val user = User(userName.text.toString(),userEmail.text.toString(),userPassword.text.toString())
            val validationMessage = model.validateFieldsUser(user)
            val context = this

            if (validationMessage.isNullOrEmpty()){

                model.singUpUser(user,object : ResponseView<Boolean>{

                    override fun getResponse(result: Boolean) {
                        if (result){
                            toastService.showMessage("USER REGISTERED",applicationContext)
                        }
                    }

                    override fun throwException(ex: Exception) {
                        toastService.showMessage(ex.message.toString(),applicationContext)
                    }

                })
            }
            else{
                toastService.showMessage(validationMessage,applicationContext)
            }
        }

    }
}