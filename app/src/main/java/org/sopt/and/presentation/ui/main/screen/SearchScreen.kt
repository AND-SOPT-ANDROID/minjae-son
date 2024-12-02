package org.sopt.and.presentation.ui.main.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import org.sopt.and.presentation.ui.common.WavveTextField
import org.sopt.and.ui.theme.ANDANDROIDTheme

@Composable
fun SearchRoute(
    mainViewModel: MainViewModel = hiltViewModel()
) {
    val hobbySearchState by mainViewModel.hobbySearchState.collectAsState()

    SearchScreen(
        hobbySearchState = hobbySearchState,
        onSearchClick = { userNo -> mainViewModel.getOthersHobby(userNo) }
    )
}

@Composable
fun SearchScreen(
    hobbySearchState: HobbySearchState,
    onSearchClick: (String) -> Unit
) {
    var inputSearch by remember { mutableStateOf("") }
    val isInputAvailable = inputSearch.toIntOrNull() != null

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF121212))
            .padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "검색화면",
            modifier = Modifier.padding(bottom = 80.dp),
            fontSize = 20.sp,
            color = Color(0xFFCCCCCC)
        )
        Row(
            modifier = Modifier.padding(bottom = 100.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            WavveTextField(
                value = inputSearch,
                onValueChange = { newValue -> inputSearch = newValue },
                modifier = Modifier
                    .weight(1f)
                    .clip(shape = RoundedCornerShape(8.dp))
                    .background(color = Color(0XFF3B5999)),
                hint = "검색할 userNo을 입력하세요"
            )
            Spacer(modifier = Modifier.width(8.dp))
            Box(
                modifier = Modifier
                    .wrapContentWidth()
                    .clip(shape = RoundedCornerShape(8.dp))
                    .background(color = if (isInputAvailable) Color.Green else Color.Red)
                    .clickable(
                        enabled = isInputAvailable,
                        onClick = { onSearchClick(inputSearch) }
                    ),
                contentAlignment = Alignment.Center,
                content = {
                    Text(
                        text = "검색하기",
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 16.dp),
                        fontSize = 14.sp
                    )
                }
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .clip(shape = RoundedCornerShape(12.dp))
                .background(color = Color.White),
            contentAlignment = Alignment.Center,
            content = {
                when (hobbySearchState) {
                    is HobbySearchState.Success -> {
                        Text(
                            text = "해당 유저의 취미는\n" +
                                    "${hobbySearchState.result.hobby}입니다.",
                            modifier = Modifier.padding(12.dp)
                        )
                    }

                    is HobbySearchState.Failure -> {
                        Text(
                            text = "해당 유저의 취미를\n" +
                                    "불러 오는데 실패했습니다.",
                            modifier = Modifier.padding(12.dp)
                        )
                    }

                    else -> {
                        Text(
                            text = "검색어를 입력하세요.",
                            modifier = Modifier.padding(12.dp)
                        )
                    }
                }

            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ShowSearchScreen() {
    ANDANDROIDTheme {
        SearchScreen(
            hobbySearchState = HobbySearchState.Idle,
            onSearchClick = {}
        )
    }
}
