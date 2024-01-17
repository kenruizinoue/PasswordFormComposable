package com.kenruizinoue.passwordformcomposable.password_form

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun PasswordStrengthIndicator(
    totalIndicators: Int = 3,
    currentStrength: Int,
    baseColor: Color = Color(0xFFFD973C),
    inactiveAlpha: Float = 0.3f,
    barHeight: Dp = 8.dp,
    spacing: Dp = 4.dp,
    cornerRadius: Dp = 2.dp,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        // 1. Spacing Control
        horizontalArrangement = Arrangement.spacedBy(spacing)
    ) {
        // 2. Drawing Strength Bars
        for (level in 1..totalIndicators) {
            // 3. Dynamic Opacity Feedback
            val alpha = if (level <= currentStrength) 1f else inactiveAlpha
            val color = baseColor.copy(alpha = alpha)
            Box(
                modifier = Modifier
                    // 4. Weight Distribution
                    .weight(1f)
                    .height(barHeight)
                    .background(color, RoundedCornerShape(cornerRadius))
            )
        }
    }
}

@Preview
@Composable
fun PasswordStrengthIndicatorPreview() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        PasswordStrengthIndicator(currentStrength = 2)
    }
}