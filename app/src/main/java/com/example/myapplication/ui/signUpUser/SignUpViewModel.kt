package com.example.myapplication.ui.signUpUser

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.repository.AuthorizationRepository
import kotlinx.coroutines.launch
class SignUpViewModel(
    val repository: AuthorizationRepository
) : ViewModel() {

    fun isUserLoggedIn(
        context: Context
    ){
        viewModelScope.launch {
            try {
                repository.isUserLoggedIn(context)
            }
            catch (e: Exception){
                Log.e("UserlLoggedIn",e.message.toString())
            }
        }
    }

    fun signUp(
        context: Context,
        userEmail: String,
        userPassword: String
    ) {
        viewModelScope.launch {
            try {
                repository.signUpWithEmail(context, userEmail, userPassword)
            } catch (e: Exception) {
                Log.e("SignUpUser", e.message.toString())
            }
        }
    }
}
