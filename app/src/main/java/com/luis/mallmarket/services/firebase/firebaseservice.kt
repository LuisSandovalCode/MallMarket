package com.luis.mallmarket.services.firebase

import android.content.Context
import android.util.Log
import com.google.android.gms.dynamic.IFragmentWrapper
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.luis.mallmarket.services.interfaces.CallbackFirebase

/*
 * SERVICE THAT PROVIDES CONNECTION WITH FIREBASE RELATED TO SIGN IN AND SING UP
 * */
class firebaseservice {

    private var authFirebase: FirebaseAuth = FirebaseAuth.getInstance()


    /*
    * ALLOWS TO SIGN IN WITH GOOGLE AUTHENTICATION
    * */
    fun signInWithGoogle(email: String, password: String, callback: CallbackFirebase<FirebaseUser>){
        authFirebase.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener {
                if (!it.isSuccessful){
                    callback.onSuccess(null,false,it.exception!!.message.toString())
                }
                else{
                    callback.onSuccess(it.result!!.user,true,"")
                }

            }
            .addOnFailureListener {
                callback.onFailed(it)
            }
    }

    /*
    * ALLOWS TO CREATE A NEW USER IN FIREBASE TO LOG IN IN THE NEXT LOG IN FROM USER
    * */
    fun signUpWithEmailAndPassword(email: String,password: String,callback: CallbackFirebase<FirebaseUser>){
        authFirebase.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener {
                callback.onSuccess(it.result!!.user ?: null,it.isSuccessful,"")
            }
            .addOnFailureListener {
                callback.onFailed(it)
            }
    }


}