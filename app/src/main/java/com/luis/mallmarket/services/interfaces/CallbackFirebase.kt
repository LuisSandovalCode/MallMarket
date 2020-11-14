package com.luis.mallmarket.services.interfaces

import java.lang.Exception

interface CallbackFirebase<Entidad> {

    fun onSuccess(result: Entidad?,isCorrect: Boolean)

    fun onFailed(exception: Exception)

}