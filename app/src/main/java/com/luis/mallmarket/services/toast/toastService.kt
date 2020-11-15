package com.luis.mallmarket.services.toast
import android.content.Context
import android.widget.Toast
import com.luis.mallmarket.MainActivity

class toastService {

    companion object{
        fun showMessage(message : String,context: Context){
            Toast.makeText(context,message,Toast.LENGTH_LONG).show()
        }
    }

}