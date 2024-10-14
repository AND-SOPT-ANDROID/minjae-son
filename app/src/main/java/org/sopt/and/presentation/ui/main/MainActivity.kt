package org.sopt.and.presentation.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
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
import org.sopt.and.R
import org.sopt.and.presentation.ui.main.screen.HomeScreen
import org.sopt.and.presentation.ui.main.screen.MyPageScreen
import org.sopt.and.presentation.ui.main.screen.SearchScreen
import org.sopt.and.presentation.utils.KeyStorage
import org.sopt.and.ui.theme.ANDANDROIDTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val userEmail = intent.getStringExtra(KeyStorage.USER_EMAIL).orEmpty()
        val userPassword = intent.getStringExtra(KeyStorage.USER_PASSWORD).orEmpty()
        enableEdgeToEdge()
        setContent {
            ANDANDROIDTheme {
                MainScreen(email = userEmail)
            }
        }
    }
}

@Composable
fun MainScreen(
    email: String
) {
    var isHomeSelected by remember { mutableStateOf(true) }
    var isSearchSelected by remember { mutableStateOf(false) }
    var isMyPageSelected by remember { mutableStateOf(false) }

    val onChangedHomeSelectedState = {
        isHomeSelected = true
        isSearchSelected = false
        isMyPageSelected = false
    }
    val onChangedSearchSelectedState = {
        isHomeSelected = false
        isSearchSelected = true
        isMyPageSelected = false
    }
    val onChangedMyPageSelectedState = {
        isHomeSelected = false
        isSearchSelected = false
        isMyPageSelected = true
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        bottomBar = {
            NavigationBar(
                containerColor = Color.Black,
                content = {
                    NavigationBarItem(
                        selected = isHomeSelected,
                        onClick = onChangedHomeSelectedState,
                        icon = {
                            Image(
                                imageVector = ImageVector.vectorResource(id = R.drawable.ic_outline_home_28),
                                contentDescription = "",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(28.dp)
                                    .clip(CircleShape)
                            )
                        },
                        label = {
                            Text(
                                text = "홈"
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color.White,
                            selectedTextColor = Color.White,
                            indicatorColor = Color.Transparent,
                            unselectedIconColor = Color(0xFF848484),
                            unselectedTextColor = Color(0xFF848484)
                        )
                    )
                    NavigationBarItem(
                        selected = isSearchSelected,
                        onClick = onChangedSearchSelectedState,
                        icon = {
                            Image(
                                imageVector = ImageVector.vectorResource(id = R.drawable.ic_baseline_search_28),
                                contentDescription = "",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(28.dp)
                                    .clip(CircleShape)
                            )
                        },
                        label = {
                            Text(
                                text = "검색",
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color.White,
                            selectedTextColor = Color.White,
                            indicatorColor = Color.Transparent,
                            unselectedIconColor = Color(0xFF848484),
                            unselectedTextColor = Color(0xFF848484)
                        )
                    )
                    NavigationBarItem(
                        selected = isMyPageSelected,
                        onClick = onChangedMyPageSelectedState,
                        icon = {
                            Image(
                                painter = painterResource(id = R.drawable.img_mypage_dummy_profile),
                                contentDescription = "",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(28.dp)
                                    .clip(CircleShape)
                            )
                        },
                        label = {
                            Text(
                                text = "MY",
                            )
                        },
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
        content = { innerPadding ->
            when {
                isHomeSelected -> HomeScreen(innerPadding)
                isSearchSelected -> SearchScreen(innerPadding)
                isMyPageSelected -> MyPageScreen(
                    paddingValues = innerPadding,
                    email = email
                )
            }
        }
    )
}


@Preview(showBackground = true)
@Composable
fun MyPagePreview() {
    ANDANDROIDTheme {
        MainScreen(email = "")
    }
}