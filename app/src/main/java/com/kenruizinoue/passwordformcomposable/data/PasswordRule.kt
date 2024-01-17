package com.kenruizinoue.passwordformcomposable.data

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

// 1. PasswordRule Model
data class PasswordRule(
    // 2. Streamlining Performance
    val regex: Regex,
    val errorMessage: String,
    // 3. Data State Management
    var isValid: Boolean = false
) {
    companion object {
        // 4. Establishing Reliability
        @Composable
        fun defaultPasswordRules(): MutableState<List<PasswordRule>> {
            return remember {
                mutableStateOf(
                    listOf(
                        PasswordRule(Regex("[A-Z]"), "Password must contain an uppercase letter"),
                        PasswordRule(Regex("[0-9]"), "Password must contain a lowercase letter"),
                        PasswordRule(Regex("[!@#$%^&*(),.?\":{}|<>]"), "Password must contain a number")
                    )
                )
            }
        }
    }
}