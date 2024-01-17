package com.kenruizinoue.passwordformcomposable.password_form

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kenruizinoue.passwordformcomposable.R
import com.kenruizinoue.passwordformcomposable.data.PasswordRule
import com.kenruizinoue.passwordformcomposable.data.PasswordRule.Companion.defaultPasswordRules

@Composable
fun PasswordInputField(
    // 1. Hardcoded Values Free Composable
    isPasswordValid: MutableState<Boolean> = remember { mutableStateOf(false) },
    passwordValue: MutableState<String> = remember { mutableStateOf("") },
    placeholderText: String = "Enter your password",
    strengthLevel: MutableState<Int> = remember { mutableIntStateOf(0) },
    validationRules: MutableState<List<PasswordRule>> = defaultPasswordRules(),
    typingStarted: MutableState<Boolean> = remember { mutableStateOf(false) },
    showPasswordIconId: Int = R.drawable.ic_eye_pass_show,
    hidePasswordIconId: Int = R.drawable.ic_eye_pass_hide,
    iconDescription: String = "Toggle Password Visibility",
    iconColor: Color = Color(0xFF525252),
    inputTextStyle: TextStyle = TextStyle(fontSize = 16.sp),
    inputFieldShape: Shape = RoundedCornerShape(size = 24.dp),
    inputTextColor: Color = Color(0xFF525252),
    textFieldElevation: CardElevation = CardDefaults.cardElevation(defaultElevation = 32.dp)
) {
    // 2. State Management Values
    val input = remember { mutableStateOf("") }
    val visibleState = remember { mutableStateOf(false) }

    Card(
        shape = inputFieldShape,
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth(),
        elevation = textFieldElevation,
    ) {
        Row(
            modifier = Modifier
                .wrapContentHeight(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                modifier = Modifier.weight(1f),
                value = input.value,
                // 3. The onValueChange Magic
                onValueChange = { newText ->
                    input.value = newText
                    passwordValue.value = newText
                    // 4. Tracking User Interaction
                    typingStarted.value = newText.isNotEmpty()
                    // 5. Real-Time Rule Validation
                    validationRules.value = validationRules.value.map { rule ->
                        rule.copy(isValid = rule.regex.containsMatchIn(newText))
                    }
                    // 6. Strength Level Calculation
                    strengthLevel.value = validationRules.value.count { it.isValid }
                    // 7. Password Validity Check
                    isPasswordValid.value = validationRules.value.all { it.isValid }
                },
                placeholder = {
                    Text(text = placeholderText, style = inputTextStyle)
                },
                textStyle = inputTextStyle.copy(color = inputTextColor),
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    cursorColor = inputTextColor,
                    disabledTextColor = inputTextColor,
                    focusedTextColor = inputTextColor,
                    unfocusedTextColor = inputTextColor,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                // 8. Password Input Transformation
                visualTransformation =
                if (visibleState.value) VisualTransformation.None
                else PasswordVisualTransformation(),
            )
            // 9. Toggle Button
            IconButton(onClick = { visibleState.value = !visibleState.value }) {
                Icon(
                    painter =
                    painterResource(
                        id =
                        if (visibleState.value) hidePasswordIconId
                        else showPasswordIconId
                    ),
                    contentDescription = iconDescription,
                    tint = iconColor
                )
            }
        }
    }
}

@Preview
@Composable
fun PasswordInputFieldPreview() {
    Box(modifier = Modifier.padding(16.dp)) {
        PasswordInputField()
    }
}