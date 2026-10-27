package com.example.myapplication.ui.signUpUser

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
@Composable
fun SignUpUserScreen(
    navController: NavController,
    viewModel: SignUpViewModel
) {
    val context = LocalContext.current
    var password by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    Column(
        Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = "SignUp",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        HorizontalDivider(
            Modifier
                .padding(8.dp)
                .fillMaxWidth()
        )
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            label = { Text("Email") }
        )
        Spacer(Modifier.height(2.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            label = { Text("Password") }
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp), horizontalArrangement = Arrangement.Center
        ) {
            Button(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                onClick = {
                    viewModel.signUp(
                        context = context,
                        userEmail = email,
                        userPassword = password
                    )
                }
            ) {
                Text("SignUp")
            }
            Button(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                onClick = {
                    navController.popBackStack()
                }
            ) {
                Text("Назад")
            }
        }
        Button(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            onClick = {
                navController.navigate("SignInScreen") {
                    popUpTo("SignUpScreen") { inclusive = true }
                }
            }
        ) {
            Text("SignIn")
        }
    }
}
