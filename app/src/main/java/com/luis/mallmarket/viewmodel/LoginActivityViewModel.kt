package com.luis.mallmarket.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import com.luis.mallmarket.services.firebase.firebaseservice
import com.luis.mallmarket.services.interfaces.CallbackFirebase
import com.luis.mallmarket.services.singleton.userSingleton
import java.lang.Exception

class LoginActivityViewModel : ViewModel() {

    /*
    * ALLOWS TO LOG IN USER WITH GOOGLE AND RETURNS IF THE AUTHENTICATION
    * WAS SUCCESSFUL, TO NAVIGATE TO OTHER ACTIVITY
    * */
    fun logInUser(email: String,password: String) : Boolean{
        val firebase = firebaseservice()
        var userAuthenticated = false

        firebase.signInWithGoogle(email,password,object : CallbackFirebase<FirebaseUser> {

            override fun onSuccess(result: FirebaseUser?, isCorrect: Boolean) {

                if (isCorrect){
                    userAuthenticated = result != null
                    /*
                    * IF THE AUTHENTICATION WAS SUCCESSFUL, WE WILL SAVE THE CURRENT USER IN MEMORY
                    * */
                    if(userAuthenticated){
                        userSingleton.setCurrentUser(result!!)
                    }
                }

            }

            override fun onFailed(exception: Exception) {
                Log.d("ERROR LOGIN",exception.message.toString())
            }
        })

        return  userAuthenticated
    }
}