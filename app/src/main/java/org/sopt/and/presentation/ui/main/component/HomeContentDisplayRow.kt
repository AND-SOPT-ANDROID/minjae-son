package org.sopt.and.presentation.ui.main.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import org.sopt.and.R

@Composable
fun HomeContentDisplayRow(
    title: String,
    onClick: () -> Unit,
    items: List<Int>,
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                color = Color.White
            )
            Spacer(modifier = Modifier.weight(1f))
            IconButton(
                onClick = onClick
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_arrow_forward),
                    contentDescription = null
                )
            }
        }

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(items) { item ->
                Image(
                    painter = painterResource(id = item),
                    contentDescription = null,
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(12.dp))
                )
            }
        }
    }
}

val bannerImages = listOf(
    R.drawable.img_home_banner_1,
    R.drawable.img_home_banner_2,
    R.drawable.img_home_banner_3
)

val editorRecommendationImages = listOf(
    R.drawable.img_editor_recommendation_1,
    R.drawable.img_editor_recommendation_2,
    R.drawable.img_editor_recommendation_3,
    R.drawable.img_editor_recommendation_4
)

val todayTop20Images = listOf(
    R.drawable.img_today_top20_1,
    R.drawable.img_today_top20_2,
    R.drawable.img_today_top20_3,
    R.drawable.img_today_top20_4
)