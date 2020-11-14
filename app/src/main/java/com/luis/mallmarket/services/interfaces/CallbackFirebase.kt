package com.luis.mallmarket.services.interfaces

import java.lang.Exception

interface CallbackFirebase<Entidad> {

    fun onSuccess(result: Entidad?,isCorrect: Boolean, message : String)

    fun onFailed(exception: Exception)

}