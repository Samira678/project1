package com.example.myapplication.ui.signUpUser

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.network.SupabaseClient.client
import com.example.myapplication.utils.SharedPreferenceHelper
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import kotlinx.coroutines.launch
class SignUpViewModel : ViewModel() {
    private val userToken = "SupabaseUserToken"
    fun saveToken(context: Context) {
        viewModelScope.launch {
            val accessToken = client.auth.currentAccessTokenOrNull() ?: ""
            val sharedPreference = SharedPreferenceHelper(context)
            sharedPreference.saveStringData(userToken, accessToken)
        }
    }
    fun getToken(context: Context):String?{
        val sharedPreference = SharedPreferenceHelper(context)
        return sharedPreference.getStringData(userToken)
    }
    fun isUserLoggedIn(
        context: Context
    ){
        viewModelScope.launch {
            try {
                val token = getToken(context)
                if(!token.isNullOrEmpty())
                {
                    client.auth.retrieveUser(token)
                    client.auth.refreshCurrentSession()
                    saveToken(context)
                    Log.d("SigUpCurrentUser", "Current user ${client.auth.currentUserOrNull()}")
                }
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
                val id = client.auth.signUpWith(Email) {
                    email = userEmail
                    password = userPassword
                }
                saveToken(context)
                Log.d("SigUpCurrentUser", "Current user ${id?.email}")
            } catch (e: Exception) {
                Log.e("SignUpUser", e.message.toString())
            }
        }
    }
}
