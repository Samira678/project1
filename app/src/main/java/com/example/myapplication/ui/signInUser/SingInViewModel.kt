package com.example.myapplication.ui.signInUser

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.network.SupabaseClient.client
import com.example.myapplication.data.repository.AuthorizationRepository
import com.example.myapplication.utils.SharedPreferenceHelper
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import kotlinx.coroutines.launch
class SignInViewModel(
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

    fun signInWithEmail(
        context: Context,
        userEmail: String,
        userPassword: String
    ) {
        viewModelScope.launch {
            try {
                repository.signInWithEmail(context,userEmail,userPassword)
            } catch (e: Exception) {
                Log.e("SignInUser", e.message.toString())
            }
        }
    }
}