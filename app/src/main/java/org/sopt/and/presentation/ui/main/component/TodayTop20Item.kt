package org.sopt.and.presentation.ui.main.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TodayTop20Item(
    index: Int,
    imageId: Int
) {
    Box(
        modifier = Modifier.size(200.dp) // 기본 크기를 지정
    ) {
        Image(
            painter = painterResource(id = imageId),
            contentDescription = null,
            modifier = Modifier.align(Alignment.TopCenter)
        )
        Text(
            text = "${index + 1}",
            modifier = Modifier.align(Alignment.BottomStart),
            color = Color.White,
            fontSize = 50.sp,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.ExtraBold
        )
    }
}