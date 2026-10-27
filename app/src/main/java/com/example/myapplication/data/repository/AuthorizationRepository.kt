package com.example.myapplication.data.repository

import android.content.Context
import android.util.Log
import com.example.myapplication.data.network.SupabaseClient.client
import com.example.myapplication.utils.SharedPreferenceHelper

import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email

class AuthorizationRepository {
    private val userToken = "SupabaseUserToken"
    fun saveToken(context: Context) {
        val accessToken = client.auth.currentAccessTokenOrNull() ?: ""
        val sharedPreference = SharedPreferenceHelper(context)
        sharedPreference.saveStringData(userToken, accessToken)
    }

    fun getToken(context: Context): String? {
        val sharedPreference = SharedPreferenceHelper(context)
        return sharedPreference.getStringData(userToken)
    }

    suspend fun signInWithEmail(
        context: Context,
        userEmail: String,
        userPassword: String
    ) {
        try {
            client.auth.signInWith(Email) {
                email = userEmail
                password = userPassword
            }

            val currentSession = client.auth.currentSessionOrNull()

            if (currentSession != null && client.auth.currentUserOrNull() != null) {
                saveToken(context)
                Log.d("SigInCurrentUser", "Успешный вход! Пользователь: ${client.auth.currentUserOrNull()?.email}")
            } else {
                Log.e("SignInUser", "Ошибка: Неверные данные входа или аккаунт не подтвержден")
            }

        } catch (e: Exception) {
            Log.e("SignInUser", "Критическая ошибка: ${e.message}")
        }
    }
    suspend fun signUpWithEmail(
        context: Context,
        userEmail: String,
        userPassword: String
    ) {
        try {
            val userResponse = client.auth.signUpWith(Email) {
                email = userEmail
                password = userPassword
            }
            val currentSession = client.auth.currentSessionOrNull()
            if (currentSession != null) {
                saveToken(context)
                Log.d("SigUpCurrentUser", "Регистрация успешна! Пользователь сразу вошел: ${userResponse?.email}")
            } else if (userResponse != null) {
                Log.d("SigUpCurrentUser", "Пользователь создан: ${userResponse.email}. Ожидается подтверждение email.")

            } else {
                Log.e("SignUpUser", "Ошибка: Сервер вернул пустой ответ при регистрации.")
            }
        } catch (e: Exception) {
            Log.e("SignUpUser", "Ошибка регистрации: ${e.message}")
        }
    }

    suspend fun isUserLoggedIn(
        context: Context
    ) {
        try {
            val token = getToken(context)
            if (!token.isNullOrEmpty()) {
                client.auth.retrieveUser(token)
                client.auth.refreshCurrentSession()
                saveToken(context)
                Log.d("SigUpCurrentUser", "Current user ${client.auth.currentUserOrNull()}")
            }
        } catch (e: Exception) {
            Log.e("UserlLoggedIn", e.message.toString())
        }
    }
}