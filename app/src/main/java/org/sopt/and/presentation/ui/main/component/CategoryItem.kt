package org.sopt.and.presentation.ui.main.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CategoryItem(
    category: HomeCategory,
    onClick: () -> Unit
) {
    Text(
        text = category.categoryName,
        color = Color(0xFFA1A1A1),
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(horizontal = 4.dp, vertical = 8.dp)
            .wrapContentWidth(),
    )
}

enum class HomeCategory(val categoryName: String) {
    NEW_CLASSIC("뉴클래식"),
    DRAMA("드라마"),
    ENTERTAINMENT("예능"),
    MOVIE("영화"),
    ANIMATION("애니"),
    INTERNATIONAL_SERIES("해외시리즈"),
    CURRENT_AFFAIRS("시사교양"),
    KIDS("키즈"),
    MOVIE_PLUS("무비플러스")
}