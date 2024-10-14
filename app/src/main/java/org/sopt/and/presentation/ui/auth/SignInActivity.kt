package org.sopt.and.presentation.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
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
import kotlinx.coroutines.launch
import org.sopt.and.R
import org.sopt.and.presentation.ui.auth.component.AuthTextField
import org.sopt.and.presentation.ui.main.MainActivity
import org.sopt.and.presentation.utils.AuthValidation
import org.sopt.and.presentation.utils.KeyStorage
import org.sopt.and.presentation.utils.showToast
import org.sopt.and.ui.theme.ANDANDROIDTheme

class SignInActivity : ComponentActivity() {
    private lateinit var userEmail: String
    private lateinit var userPassword: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getUserInfo()
        enableEdgeToEdge()
        setContent {
            ANDANDROIDTheme {
                SignInScreen(
                    userEmail = userEmail,
                    userPassword = userPassword,
                    navigateToSignUp = { navigateToSignUp() },
                    navigateToMain = { userEmail, userPassword ->
                        navigateToMain(
                            userEmail,
                            userPassword
                        )
                    }
                )
            }
        }
    }

    private fun getUserInfo() {
        userEmail = intent.getStringExtra(KeyStorage.USER_EMAIL).orEmpty()
        userPassword = intent.getStringExtra(KeyStorage.USER_PASSWORD).orEmpty()
    }

    private fun navigateToSignUp() {
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToMain(userEmail: String, userPassword: String) {
        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra(KeyStorage.USER_EMAIL, userEmail)
            putExtra(KeyStorage.USER_PASSWORD, userPassword)
        }
        startActivity(intent)
    }
}

@Composable
fun SignInScreen(
    userEmail: String,
    userPassword: String,
    navigateToSignUp: () -> Unit,
    navigateToMain: (String, String) -> Unit
) {
    val context = LocalContext.current
    var inputEmail by remember { mutableStateOf("") }
    var inputPassword by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()
    val snackBarHostState = remember { SnackbarHostState() }
    lateinit var signInState: SignInState

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF161616))
            .padding(horizontal = 20.dp)
            .padding(WindowInsets.statusBars.asPaddingValues())
            .padding(WindowInsets.navigationBars.asPaddingValues()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_signin_arrow_back),
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
                signInState = AuthValidation.isSignInValid(
                    userEmail = userEmail,
                    userPassword = userPassword,
                    inputEmail = inputEmail,
                    inputPassword = inputPassword
                )
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
                        navigateToMain(userEmail, userPassword)
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
        SignInScreen(
            userEmail = "",
            userPassword = "",
            navigateToSignUp = {},
            navigateToMain = { _, _ -> }
        )
    }
}