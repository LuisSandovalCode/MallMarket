package com.luis.mallmarket.viewmodel

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import com.luis.mallmarket.enums.EnumDocumentType
import com.luis.mallmarket.interfaces.ResponseView
import com.luis.mallmarket.model.User
import com.luis.mallmarket.services.firebase.firebaseDBService
import com.luis.mallmarket.services.firebase.firebaseservice
import com.luis.mallmarket.services.interfaces.CallbackFirebase
import java.lang.Exception

class NewUserViewModel : ViewModel() {


    fun validateFieldsUser(user: User) : String{
        if (user.userName.isNullOrEmpty()){
            return "The username field is required"
        }
        if (user.userEmail.isNullOrEmpty()){
            return  "The user email is required"
        }
        if (user.userPassword.isNullOrEmpty()){
            return  "The user password field is required"
        }
        return  ""
    }

    /*
    * ALLOWS TO CREATE A NEW USER IN FIREBASE DB
    * */
    fun singUpUser(user : User,callback: ResponseView<Boolean>){
        val database = firebaseDBService<User>()
        val firebaseService = firebaseservice()

        firebaseService.signUpWithEmailAndPassword(user.userEmail,user.userPassword, object : CallbackFirebase<FirebaseUser>{

            override fun onSuccess(result: FirebaseUser?, isCorrect: Boolean, message: String) {

                if (isCorrect){
                    user.userId = result!!.uid
                    database.saveRealtimeData(user, EnumDocumentType.USER.getValueEnum(),object : CallbackFirebase<Void>{

                        override fun onSuccess(result: Void?, isCorrect: Boolean, message: String) {
                            callback.getResponse(true)
                        }

                        override fun onFailed(exception: Exception) {
                            callback.throwException(exception)
                        }

                    })
                }else{
                    callback.getResponse(false)
                }
            }

            override fun onFailed(exception: Exception) {
                callback.throwException(exception)
            }

        })
    }


}