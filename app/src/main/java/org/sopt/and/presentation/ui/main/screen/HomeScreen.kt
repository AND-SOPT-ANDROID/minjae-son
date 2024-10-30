package org.sopt.and.presentation.ui.main.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import org.sopt.and.R
import org.sopt.and.presentation.ui.main.component.CategoryItem
import org.sopt.and.presentation.ui.main.component.HomeCategory
import org.sopt.and.presentation.ui.main.component.HomeContentDisplayRow
import org.sopt.and.ui.theme.ANDANDROIDTheme

@Composable
fun HomeScreen(
    paddingValues: PaddingValues
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF161616))
            .padding(paddingValues)
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            HomeTopBar()
        }
        item {
            HomeCategoryTopBar(onCategorySelected = { category -> })
        }
        item {
            HomeHorizontalBanner(images = bannerImages)
        }
        item {
            HomeContentDisplayRow(
                title = "믿고 보는 웨이브 에디터 추천",
                onClick = {},
                items = editorRecommendationImages,
            )
        }
        item {
            HomeContentDisplayRow(
                title = "오늘의 TOP 20",
                onClick = {},
                items = todayTop20Images,
            )
        }
    }
}


@Composable
fun HomeTopBar() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_home_logo),
            contentDescription = null,
            modifier = Modifier.size(width = 100.dp, height = 48.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
        IconButton(
            onClick = {}
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_home_mirroring),
                contentDescription = null,
            )
        }
        IconButton(
            onClick = {}
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_home_live),
                contentDescription = null
            )
        }
    }
}

@Composable
fun HomeCategoryTopBar(
    onCategorySelected: (HomeCategory) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(HomeCategory.values()) { category ->
                CategoryItem(
                    category = category,
                    onClick = { onCategorySelected(category) }
                )
            }
        }
    }
}

@Composable
fun HomeHorizontalBanner(
    images: List<Int>
) {
    val pagerState = rememberPagerState(
        initialPage = Int.MAX_VALUE / 2,
        pageCount = {Int.MAX_VALUE}
    )

    LaunchedEffect(pagerState) {
        while (true) {
            delay(3000)
            val nextPage = pagerState.currentPage + 1
            pagerState.animateScrollToPage(nextPage)
        }
    }

    HorizontalPager(
        state = pagerState,
        modifier = Modifier
            .width((LocalConfiguration.current.screenWidthDp * 0.85).dp)
            .height((LocalConfiguration.current.screenHeightDp * 0.6).dp)
            .clip(RoundedCornerShape(12.dp)),
    ) { page ->
        val imageIndex = page % images.size

        Box(
            modifier = Modifier.padding(horizontal = 12.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = images[imageIndex]),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(shape = RoundedCornerShape(12.dp))
            )
            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(14.dp)
                    .clip(shape = RoundedCornerShape(20.dp))
                    .background(color = Color(0xFF121212)),
            ) {
                Text(
                    text = "${imageIndex + 1} | ${images.size}",
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                    color = Color.White
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


@Preview(showBackground = true)
@Composable
fun ShowHomeScreen() {
    ANDANDROIDTheme {
        HomeScreen(
            paddingValues = PaddingValues(0.dp)
        )
    }
}