package com.kenruizinoue.passwordformcomposable.password_form

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kenruizinoue.passwordformcomposable.R

@Composable
fun ValidationFeedbackItemUi(
    feedbackMessage: String = "Password must comply with the rule",
    // 1. State Control Parameter
    isRuleMet: Boolean = false,
    iconResIdOnSuccess: Int = R.drawable.ic_valid,
    iconResIdOnFailure: Int = R.drawable.ic_invalid,
    colorOnSuccess: Color = Color(0xFF72A374),
    colorOnFailure: Color = Color(0xFFCF5555),
    feedbackTextStyle: TextStyle = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.SemiBold
    ),
    iconDimension: Dp = 24.dp,
    spaceBetweenIconAndText: Dp = 8.dp
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = if (isRuleMet) iconResIdOnSuccess else iconResIdOnFailure),
            contentDescription = null,
            tint = if (isRuleMet) colorOnSuccess else colorOnFailure,
            modifier = Modifier.size(iconDimension)
        )
        // 2. Layout Simplicity
        Spacer(modifier = Modifier.width(spaceBetweenIconAndText))
        Text(
            text = feedbackMessage,
            color = if (isRuleMet) colorOnSuccess else colorOnFailure,
            style = feedbackTextStyle
        )
    }
}

@Preview
@Composable
fun ValidationFeedbackItemUiPreview() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ValidationFeedbackItemUi(feedbackMessage = "Password must contain an uppercase letter")
        Spacer(modifier = Modifier.height(8.dp))
        ValidationFeedbackItemUi(feedbackMessage = "Password must contain a number", isRuleMet = true)
    }
}