package org.sopt.and.presentation.ui.main.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import org.sopt.and.presentation.ui.common.WavveTextField
import org.sopt.and.presentation.util.showToast
import org.sopt.and.ui.theme.ANDANDROIDTheme

@Composable
fun SettingRoute(
    mainViewModel: MainViewModel = hiltViewModel(),
    navigateToMyPage: () -> Unit
) {
    val userInfoUpdateState by mainViewModel.userInfoUpdateState.collectAsState()

    SettingScreen(
        userInfoUpdateState = userInfoUpdateState,
        resetUserInfoUpdateState = { mainViewModel.resetUserInfoUpdateState() },
        onUserInfoChangeClick = { password, hobby -> mainViewModel.updateUserInfo(password, hobby) },
        navigateToMyPage = navigateToMyPage
    )
}

@Composable
fun SettingScreen(
    userInfoUpdateState: UserInfoUpdateState,
    resetUserInfoUpdateState: () -> Unit,
    onUserInfoChangeClick: (String?, String?) -> Unit,
    navigateToMyPage: () -> Unit
) {
    val context = LocalContext.current
    var inputPassword by remember { mutableStateOf("") }
    var inputHobby by remember { mutableStateOf("") }
    var isButtonEnabled = inputPassword.isNotBlank() || inputHobby.isNotBlank()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF161616))
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "회원정보 변경",
            modifier = Modifier.padding(bottom = 80.dp),
            fontSize = 20.sp,
            color = Color(0xFFCCCCCC)
        )
        WavveTextField(
            value = inputPassword,
            onValueChange = { newValue -> inputPassword = newValue },
            modifier = Modifier
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(6.dp))
                .background(color = Color(0xFF262626)),
            hint = "변경할 비밀번호 (8자 이하)"
        )
        Spacer(Modifier.height(8.dp))
        WavveTextField(
            value = inputHobby,
            onValueChange = { newValue -> inputHobby = newValue },
            modifier = Modifier
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(6.dp))
                .background(color = Color(0xFF262626)),
            hint = "변경할 취미 (8자 이하)"
        )
        Spacer(Modifier.height(24.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .clip(shape = RoundedCornerShape(20.dp))
                .background(color = if(isButtonEnabled) Color(0xFF1352F9) else Color.Gray)
                .clickable(
                    enabled = isButtonEnabled,
                    onClick = {
                        if (inputPassword.isBlank()) onUserInfoChangeClick(null, inputHobby)
                        else if (inputHobby.isBlank()) onUserInfoChangeClick(inputPassword, null)
                        else onUserInfoChangeClick(inputPassword, inputHobby)
                    }
                ),
            contentAlignment = Alignment.Center,
            content = {
                Text(
                    text = "변경하기",
                    modifier = Modifier.padding(vertical = 12.dp),
                    color = Color.White
                )
            }
        )
        when (userInfoUpdateState) {
            is UserInfoUpdateState.Success -> {
                showToast(
                    context = context,
                    message = "회원정보 변경에 성공했습니다."
                )
                resetUserInfoUpdateState()
                navigateToMyPage()
            }

            is UserInfoUpdateState.Failure -> {
                showToast(
                    context = context,
                    message = "회원정보 변경에 실패했습니다."
                )
                resetUserInfoUpdateState()
            }

            else -> {}
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ShowSettingScreen() {
    ANDANDROIDTheme {
        SettingScreen(
            userInfoUpdateState = UserInfoUpdateState.Idle,
            resetUserInfoUpdateState = {},
            onUserInfoChangeClick = { _, _ -> },
            navigateToMyPage = {}
        )
    }
}