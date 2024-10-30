package org.sopt.and.presentation.ui.main.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import org.sopt.and.R
import org.sopt.and.ui.theme.ANDANDROIDTheme

@Composable
fun MainRoute(
    navController: NavHostController,
    mainViewModel: MainViewModel,
    userEmail: String,
) {
    MainScreen(
        userEmail = userEmail
    )
}

@Composable
fun MainScreen(
    userEmail: String
) {
    var selectedTab by remember { mutableStateOf<MainTabList>(MainTabList.HOME) }
    val onTabSelected: (MainTabList) -> Unit = { tab ->
        selectedTab = tab
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar(
                containerColor = Color.Black,
                windowInsets = WindowInsets(0.dp, 0.dp, 0.dp, 0.dp),
                content = {
                    NavigationBarItem(
                        selected = selectedTab == MainTabList.HOME,
                        onClick = { onTabSelected(MainTabList.HOME) },
                        icon = {
                            Image(
                                imageVector = ImageVector.vectorResource(id = R.drawable.ic_outline_home_28),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(28.dp)
                                    .clip(CircleShape)
                            )
                        },
                        label = { Text(text = "홈") },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color.White,
                            selectedTextColor = Color.White,
                            indicatorColor = Color.Transparent,
                            unselectedIconColor = Color(0xFF848484),
                            unselectedTextColor = Color(0xFF848484)
                        )
                    )
                    NavigationBarItem(
                        selected = selectedTab == MainTabList.SEARCH,
                        onClick = { onTabSelected(MainTabList.SEARCH) },
                        icon = {
                            Image(
                                imageVector = ImageVector.vectorResource(id = R.drawable.ic_baseline_search_28),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(28.dp)
                                    .clip(CircleShape)
                            )
                        },
                        label = { Text(text = "검색") },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color.White,
                            selectedTextColor = Color.White,
                            indicatorColor = Color.Transparent,
                            unselectedIconColor = Color(0xFF848484),
                            unselectedTextColor = Color(0xFF848484)
                        )
                    )
                    NavigationBarItem(
                        selected = selectedTab == MainTabList.MY,
                        onClick = { onTabSelected(MainTabList.MY) },
                        icon = {
                            Image(
                                painter = painterResource(id = R.drawable.img_mypage_dummy_profile),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(28.dp)
                                    .clip(CircleShape)
                            )
                        },
                        label = { Text(text = "MY") },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color.White,
                            selectedTextColor = Color.White,
                            indicatorColor = Color.Transparent,
                            unselectedIconColor = Color(0xFF848484),
                            unselectedTextColor = Color(0xFF848484)
                        )
                    )
                }
            )
        },
        containerColor = Color.Black,
        contentWindowInsets = WindowInsets(0.dp, 0.dp, 0.dp, 0.dp),
        content = { innerPadding ->
            // selectedTab 값에 따라 표시되는 화면을 변경
            when (selectedTab) {
                MainTabList.HOME -> HomeScreen(innerPadding)
                MainTabList.SEARCH -> SearchScreen(innerPadding)
                MainTabList.MY -> MyPageScreen(
                    paddingValues = innerPadding,
                    userEmail = userEmail
                )
            }
        }
    )
}

sealed class MainTabList {
    data object HOME: MainTabList()
    data object SEARCH: MainTabList()
    data object MY: MainTabList()
}


@Preview(showBackground = true)
@Composable
fun MyPagePreview() {
    ANDANDROIDTheme {

    }
}