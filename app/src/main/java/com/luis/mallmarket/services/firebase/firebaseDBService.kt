package com.luis.mallmarket.services.firebase

import com.google.firebase.database.*
import com.luis.mallmarket.services.interfaces.CallbackFirebase
import java.lang.Exception

class firebaseDBService<T> {

    private val database: DatabaseReference = FirebaseDatabase.getInstance().reference

    /*
    * ALLOWS TO ADD O CREATE DATA TO FIREBASE DB SERVICE
    * */
    fun saveRealtimeData(data: T, documentName: String,callback: CallbackFirebase<Void>){
        database.child(documentName).child(documentName).setValue(data)
            .addOnCompleteListener {
                callback.onSuccess(null,true,"")
            }
            .addOnFailureListener {
                callback.onFailed(it)
            }
    }

    /*
    * ALLOWS TO GET A SNAPSHOT FROM FIREBASE
    * */
    fun getDataById(id: String,documentName: String,entity : Class<T>, callback: CallbackFirebase<Class<T>>){
        val refBD = FirebaseDatabase.getInstance().getReference(documentName).child(id)

        val listener = object : ValueEventListener{

            override fun onCancelled(error: DatabaseError) {
                callback.onFailed(Exception(error.message))
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                val data = snapshot.getValue(entity::class.java)
                callback.onSuccess(data,true,"")
            }

        }
    }

}