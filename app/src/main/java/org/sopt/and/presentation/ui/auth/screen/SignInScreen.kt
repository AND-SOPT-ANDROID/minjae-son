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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
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
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch
import org.sopt.and.R
import org.sopt.and.presentation.ui.auth.component.AuthTextField
import org.sopt.and.presentation.ui.auth.navigation.navigateToSignUp
import org.sopt.and.presentation.ui.main.navigation.navigateToMain
import org.sopt.and.presentation.utils.showToast
import org.sopt.and.ui.theme.ANDANDROIDTheme

@Composable
fun SignInRoute(
    navController: NavHostController,
    authViewModel: AuthViewModel,
) {
    val signInState by authViewModel.signInState.collectAsState()

    SignInScreen(
        authViewModel = authViewModel,
        signInState = signInState,
        navigateToSignUp = { navController.navigateToSignUp() },
        navigateToMain = { userEmail -> navController.navigateToMain(userEmail) },
    )
}

@Composable
fun SignInScreen(
    authViewModel: AuthViewModel,
    signInState: SignInState,
    navigateToSignUp: () -> Unit,
    navigateToMain: (String) -> Unit
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    var inputEmail by remember { mutableStateOf("") }
    var inputPassword by remember { mutableStateOf("") }
    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(inputEmail,inputPassword) {
        authViewModel.updateSignInState(authViewModel.isSignInValid(inputEmail,inputPassword))
    }

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

        AuthTextField(
            value = inputEmail,
            onValueChange = { newValue -> inputEmail = newValue },
            modifier = Modifier
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(6.dp))
                .background(color = Color(0xFF262626)),
            hint = "이메일 주소 또는 아이디"
        )
        Spacer(Modifier.height(4.dp))
        AuthTextField(
            value = inputPassword,
            onValueChange = { newValue -> inputPassword = newValue },
            modifier = Modifier
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(6.dp))
                .background(color = Color(0xFF262626)),
            hint = "비밀번호",
            isPassword = true
        )

        Spacer(Modifier.height(30.dp))

        Button(
            onClick = {
                when (signInState) {
                    is SignInState.EmailEmpty -> {
                        showToast(context = context, message = "이메일을 입력해주세요")
                    }

                    is SignInState.PasswordEmpty -> {
                        showToast(context = context, message = "비밀번호를 입력해주세요")
                    }

                    is SignInState.EmailInvalid -> {
                        coroutineScope.launch {
                            val snackBarResult = snackBarHostState.showSnackbar(
                                message = "이메일이 일치하지 않습니다",
                                actionLabel = "실행 취소",
                                duration = SnackbarDuration.Short
                            )
                            when (snackBarResult) {
                                SnackbarResult.ActionPerformed -> {
                                    inputEmail = ""
                                }

                                SnackbarResult.Dismissed -> {}
                            }
                        }
                    }

                    is SignInState.PasswordInvalid -> {
                        coroutineScope.launch {
                            val snackBarResult = snackBarHostState.showSnackbar(
                                message = "비밀번호가 일치하지 않습니다",
                                actionLabel = "실행 취소",
                                duration = SnackbarDuration.Short
                            )
                            when (snackBarResult) {
                                SnackbarResult.ActionPerformed -> {
                                    inputPassword = ""
                                }

                                SnackbarResult.Dismissed -> {}
                            }
                        }
                    }

                    is SignInState.Success -> {
                        showToast(context = context, message = "로그인 성공")
                        navigateToMain(authViewModel.authEmail.value.toString())
                    }

                    else -> {}
                }
            },
            modifier = Modifier
                .fillMaxWidth(),
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
            Box(
                modifier = Modifier
                    .height(12.dp)
                    .width(1.dp)
                    .background(color = Color(0xFF848484))
            )
            SignInOption(text = "비밀번호 재설정")
            Box(
                modifier = Modifier
                    .height(12.dp)
                    .width(1.dp)
                    .background(color = Color(0xFF848484))
            )
            SignInOption(
                text = "회원가입",
                onClick = { navigateToSignUp() }
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

        Row(
            modifier = Modifier.padding(vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            PlatformSignInButton(id = R.drawable.img_auth_kakao)
            PlatformSignInButton(id = R.drawable.img_auth_skt)
            PlatformSignInButton(id = R.drawable.img_auth_naver)
            PlatformSignInButton(id = R.drawable.img_auth_facebook)
            PlatformSignInButton(id = R.drawable.img_auth_apple)
        }

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
                text = "SNS계정으로 간편하게 가입하여 서비스를 이용하실 수 있습니다.\n기존 POOQ 계정 또는 Wavve 계정과는 연동되지 않으니 이용에 참고하세요",
                modifier = Modifier.fillMaxWidth(),
                fontSize = 10.sp,
                lineHeight = 14.sp,
                color = Color(0xFF5D5D5D)
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        SnackbarHost(hostState = snackBarHostState)
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

@Composable
fun PlatformSignInButton(
    id: Int,
    onClick: () -> Unit = {}
) {
    Image(
        painter = painterResource(id = id),
        contentDescription = "",
        modifier = Modifier
            .clickable { onClick() }
            .padding(vertical = 10.dp, horizontal = 4.dp)
            .size(50.dp),
        contentScale = ContentScale.Crop
    )
}

@Preview(showBackground = true)
@Composable
fun SignInPreview() {
    ANDANDROIDTheme {

    }
}