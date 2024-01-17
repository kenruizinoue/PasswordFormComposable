package com.kenruizinoue.passwordformcomposable

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.kenruizinoue.passwordformcomposable.password_form.PasswordForm

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val myAppColor = Color(0xFF485ED6)
            // 1. Defining Validation Rules
            val passwordCriteria = listOf(
                Regex("[A-Z]") to "Password must contain an uppercase letter",
                Regex("[a-z]") to "Password must contain a lowercase letter",
                Regex("[0-9]") to "Password must contain a number",
                Regex(".{8,}") to "Password must be at least 8 characters long",
                Regex("[!@#$%^&*(),.?\":{}|<>]") to "Password must contain a special character"
            )
            val isPasswordValid = remember { mutableStateOf(false) }
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {
                PasswordForm(
                    passwordCriteria = passwordCriteria,
                    isPasswordValid = isPasswordValid,
                    strengthIndicatorColor = myAppColor,
                )
                // 2. Conditional Submit Button Activation
                Button(
                    onClick = { /*TODO*/ },
                    enabled = isPasswordValid.value,
                    colors = ButtonDefaults.buttonColors(containerColor = myAppColor)
                ) {
                    Text(text = "Submit Password")
                }
            }
        }
    }
}