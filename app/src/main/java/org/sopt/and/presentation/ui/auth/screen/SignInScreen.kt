package org.sopt.and.presentation.ui.auth.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import org.sopt.and.R
import org.sopt.and.presentation.ui.auth.component.SocialPlatformIconRow
import org.sopt.and.presentation.ui.auth.component.SocialPlatformList
import org.sopt.and.presentation.ui.common.WavveTextField
import org.sopt.and.presentation.util.showToast
import org.sopt.and.ui.theme.ANDANDROIDTheme

@Composable
fun SignInRoute(
    authViewModel: AuthViewModel = hiltViewModel(),
    navigateToSignUp: () -> Unit,
    navigateToMain: () -> Unit,
) {
    val authUiState by authViewModel.uiState.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current

    LaunchedEffect(authViewModel.sideEffect, lifecycleOwner) {
        authViewModel.sideEffect.flowWithLifecycle(lifecycle = lifecycleOwner.lifecycle)
            .collect { authSideEffect ->
                when (authSideEffect) {
                    is AuthContract.AuthSideEffect.NavigateToSignUp -> navigateToSignUp()
                    is AuthContract.AuthSideEffect.NavigateToMain -> navigateToMain()
                    is AuthContract.AuthSideEffect.ShowToast -> showToast(
                        context = context,
                        message = authSideEffect.message
                    )

                    else -> {}
                }
            }
    }

    LaunchedEffect(authUiState.signInState) {
        when (authUiState.signInState) {
            is SignInState.Success -> {
                authViewModel.setSideEffect(AuthContract.AuthSideEffect.ShowToast(message = "로그인에 성공하였습니다."))
                authViewModel.setEvent(AuthContract.AuthEvent.ResetSignInState)
                authViewModel.setSideEffect(AuthContract.AuthSideEffect.NavigateToMain)
            }

            is SignInState.Failure -> {
                authViewModel.setSideEffect(AuthContract.AuthSideEffect.ShowToast(message = "아이디와 비밀번호를 다시 확인해주세요."))
                authViewModel.setEvent(AuthContract.AuthEvent.ResetSignInState)
            }

            else -> {}
        }
    }

    SignInScreen(
        authUiState = authUiState,
        updateSignInUsername = { input -> authViewModel.updateSignInUsername(input) },
        updateSignInPassword = { input -> authViewModel.updateSignInPassword(input) },
        onSignInClick = { authViewModel.validateSignIn() },
        onSignUpClick = { authViewModel.setSideEffect(AuthContract.AuthSideEffect.NavigateToSignUp) },
    )
}

@Composable
fun SignInScreen(
    authUiState: AuthContract.AuthUiState,
    updateSignInUsername: (String) -> Unit,
    updateSignInPassword: (String) -> Unit,
    onSignInClick: () -> Unit,
    onSignUpClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF161616))
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_arrow_back),
                contentDescription = "",
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .size(40.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.img_signin_wavve_logo),
                contentDescription = "",
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(
                        width = (LocalConfiguration.current.screenWidthDp * 0.3).dp,
                        height = (LocalConfiguration.current.screenHeightDp * 0.06).dp
                    ),
                contentScale = ContentScale.Fit
            )
        }

        Spacer(Modifier.height(40.dp))

        WavveTextField(
            value = authUiState.signInUsername,
            onValueChange = updateSignInUsername,
            modifier = Modifier
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(6.dp))
                .background(color = Color(0xFF262626)),
            hint = "이메일 주소 또는 아이디"
        )
        Spacer(Modifier.height(4.dp))
        WavveTextField(
            value = authUiState.signInPassword,
            onValueChange = updateSignInPassword,
            modifier = Modifier
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(6.dp))
                .background(color = Color(0xFF262626)),
            hint = "비밀번호",
            isPassword = true
        )

        Spacer(Modifier.height(30.dp))

        Button(
            onClick = onSignInClick,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(Color(0xFF0F42C7))
        ) {
            Text(
                text = "로그인",
                modifier = Modifier.padding(6.dp)
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            SignInOption(text = "아이디 찾기")
            VerticalDivider(
                modifier = Modifier.height(12.dp),
                thickness = 1.dp,
                color = Color(0xFF848484)
            )
            SignInOption(text = "비밀번호 재설정")
            VerticalDivider(
                modifier = Modifier.height(12.dp),
                thickness = 1.dp,
                color = Color(0xFF848484)
            )
            SignInOption(
                text = "회원가입",
                onClick = onSignUpClick
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(1.dp)
                    .background(color = Color(0xFF2F2F2F))
            )
            Text(
                text = "또는 다른 서비스 계정으로 로그인",
                modifier = Modifier
                    .weight(2.5f)
                    .padding(horizontal = 4.dp),
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                color = Color(0xFF848484)
            )
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(1.dp)
                    .background(color = Color(0xFF2F2F2F))
            )
        }

        SocialPlatformIconRow(
            images = SocialPlatformList,
            onClick = {}
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "·",
                modifier = Modifier
                    .align(Alignment.Top)
                    .padding(end = 2.dp),
                fontSize = 10.sp,
                lineHeight = 14.sp,
                color = Color(0xFF5D5D5D)
            )
            Text(
                text = "SNS계정으로 간편하게 가입하여 서비스를 이용하실 수 있습니다.\n" +
                        "기존 POOQ 계정 또는 Wavve 계정과는 연동되지 않으니 이용에 참고하세요",
                modifier = Modifier.fillMaxWidth(),
                fontSize = 10.sp,
                lineHeight = 14.sp,
                color = Color(0xFF5D5D5D)
            )
        }

        Spacer(modifier = Modifier.weight(1f))
    }
}

@Composable
fun SignInOption(
    text: String = "",
    onClick: () -> Unit = {}
) {
    Text(
        text = text,
        modifier = Modifier
            .clickable { onClick() }
            .padding(vertical = 14.dp, horizontal = 10.dp),
        fontSize = 12.sp,
        color = Color(0xFF848484)
    )
}

@Preview(showBackground = true)
@Composable
fun SignInPreview() {
    ANDANDROIDTheme {

    }
}