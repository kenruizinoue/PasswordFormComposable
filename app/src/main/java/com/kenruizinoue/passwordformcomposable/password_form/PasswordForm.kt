package com.kenruizinoue.passwordformcomposable.password_form

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.kenruizinoue.passwordformcomposable.data.PasswordRule

@Composable
fun PasswordForm(
    passwordCriteria: List<Pair<Regex, String>> = listOf(
        Regex("[A-Z]") to "Password must contain an uppercase letter",
        Regex("[a-z]") to "Password must contain a lowercase letter",
        Regex("[0-9]") to "Password must contain a number"
    ),
    // 1. Accessible State Parameters
    passwordValue: MutableState<String> = remember { mutableStateOf("") },
    isPasswordValid: MutableState<Boolean> = remember { mutableStateOf(false) },
    formPadding: Dp = 16.dp,
    strengthIndicatorColor: Color = Color(0xFFFD973C),
) {
    // 2. Simplified Data Handling
    val passwordRules = remember {
        mutableStateOf(passwordCriteria.map { PasswordRule(it.first, it.second) })
    }
    val passwordStrengthLevel = remember { mutableIntStateOf(0) }
    val hasStartedTyping = remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.padding(start = formPadding, top = formPadding, end = formPadding)
    ) {
        Column {
            PasswordInputField(
                isPasswordValid = isPasswordValid,
                passwordValue = passwordValue,
                strengthLevel = passwordStrengthLevel,
                validationRules = passwordRules,
                typingStarted = hasStartedTyping,
                // Other arguments for PasswordInputField()...
            )
            Spacer(modifier = Modifier.height(formPadding))
            PasswordStrengthIndicator(
                totalIndicators = passwordRules.value.size,
                currentStrength = passwordStrengthLevel.intValue,
                baseColor = strengthIndicatorColor,
                // Other arguments for PasswordStrengthIndicator()...
            )
            Spacer(modifier = Modifier.height(formPadding))
            passwordRules.value.forEach { rule ->
                if (hasStartedTyping.value) {
                    ValidationFeedbackItemUi(
                        isRuleMet = rule.isValid,
                        feedbackMessage = rule.errorMessage,
                        // Other arguments for ValidationFeedbackItemUi()...
                    )
                    Spacer(modifier = Modifier.height(formPadding))
                }
            }
        }
    }
}

@Preview
@Composable
fun PasswordFormPreview() {
    PasswordForm()
}