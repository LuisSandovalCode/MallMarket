package com.luis.mallmarket.interfaces

import java.lang.Exception

interface ResponseLogin<T> {

    fun getResponse(result : T)

    fun throwException(ex : Exception)
}