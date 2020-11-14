package com.luis.mallmarket.services.singleton

import com.google.firebase.auth.FirebaseUser

class userSingleton{

    companion object{

        /*
        *STATIC VARIABLE THAT IT WILL CONTAIN A USER IN MEMORY
        * */
        private lateinit var _currentUser: FirebaseUser


        /*
        * ALLOWS TO CREATE AN USER IN MEMORY WHILE THE APP IS RUNNING
        * */
        fun setCurrentUser(currentUser: FirebaseUser)  {
            _currentUser = currentUser
        }


        /*
        * ALLOWS TO GET THE CURRENT AUTHENTICATED USER
        * */
        fun getCurrentUser() : FirebaseUser{
            return _currentUser
        }
    }
}