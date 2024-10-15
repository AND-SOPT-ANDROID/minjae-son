package org.sopt.and.presentation.ui.auth.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AuthTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    hint: String = "",
    isPassword: Boolean = false,
) {
    var passwordVisible by remember { mutableStateOf(!isPassword) }
    val visualTransformation = if (isPassword && !passwordVisible) PasswordVisualTransformation() else VisualTransformation.None

    Box(modifier = modifier) {
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.padding(6.dp),
            textStyle = TextStyle(
                color = Color.White,
                fontSize = 12.sp
            ),
            singleLine = true,
            visualTransformation = visualTransformation,
            decorationBox = { innerTextField ->
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .padding(10.dp),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        if (value.isEmpty()) {
                            Text(
                                text = hint,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color(0xFF848484)
                            )
                        }
                        innerTextField()
                    }
                    if (isPassword) {
                        if (visualTransformation == PasswordVisualTransformation()) {
                            Text(
                                text = "show",
                                modifier = Modifier
                                    .clickable { passwordVisible = true }
                                    .padding(end = 2.dp),
                                color = Color(0xFFCCCCCC)
                            )
                        }
                        if (visualTransformation == VisualTransformation.None) {
                            Text(
                                text = "hide",
                                modifier = Modifier
                                    .clickable { passwordVisible = false }
                                    .padding(end = 2.dp),
                                color = Color(0xFFCCCCCC)
                            )
                        }
                    }
                }
            }
        )
    }
}
