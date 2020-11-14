package com.luis.mallmarket.viewmodel

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import com.luis.mallmarket.interfaces.ResponseLogin
import com.luis.mallmarket.services.firebase.firebaseservice
import com.luis.mallmarket.services.interfaces.CallbackFirebase
import com.luis.mallmarket.services.singleton.userSingleton
import java.util.concurrent.CountDownLatch

class LoginActivityViewModel : ViewModel() {

    /*
    * ALLOWS TO LOG IN USER WITH GOOGLE AND RETURNS IF THE AUTHENTICATION
    * WAS SUCCESSFUL, TO NAVIGATE TO OTHER ACTIVITY
    * */
    fun logInUser(email: String,password: String,response: ResponseLogin<String>){

        val firebase = firebaseservice()

        firebase.signInWithGoogle(email,password,object : CallbackFirebase<FirebaseUser> {

            override fun onSuccess(result: FirebaseUser?, isCorrect: Boolean, message: String) {
                response.getResponse(message)

                if (isCorrect){
                    /*
                    * IF THE AUTHENTICATION WAS SUCCESSFUL, WE WILL SAVE THE CURRENT USER IN MEMORY
                    * */
                    if(result != null && !message.isNullOrEmpty()){
                        userSingleton.setCurrentUser(result!!)
                    }
                }

            }

            override fun onFailed(exception: Exception) {
                response.throwException(exception)
            }
        })
    }
}