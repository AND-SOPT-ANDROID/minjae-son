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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.R
import org.sopt.and.presentation.ui.auth.component.AuthTextField
import org.sopt.and.presentation.utils.AuthValidation
import org.sopt.and.presentation.utils.KeyStorage
import org.sopt.and.presentation.utils.showToast
import org.sopt.and.ui.theme.ANDANDROIDTheme

class SignUpActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ANDANDROIDTheme {
                SignUpScreen(
                    navigateToSignIn = { navigateToSignIn() },
                    navigateToSignInWithInfo = { email, password -> navigateToSignInWithInfo(email, password) }
                )
            }
        }
    }

    private fun navigateToSignIn() {
        val intent = Intent(this@SignUpActivity, SignInActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToSignInWithInfo(email: String, password: String) {
        val intent = Intent(this@SignUpActivity, SignInActivity::class.java).apply {
            putExtra(KeyStorage.USER_EMAIL, email)
            putExtra(KeyStorage.USER_PASSWORD, password)
        }
        startActivity(intent)
    }
}

@Composable
fun SignUpScreen(
    navigateToSignIn: () -> Unit,
    navigateToSignInWithInfo: (String, String) -> Unit
) {
    val context = LocalContext.current
    var inputEmail by remember { mutableStateOf("") }
    var inputPassword by remember { mutableStateOf("") }
    var isSignUpValid by remember { mutableStateOf(false) }

    LaunchedEffect(inputEmail, inputPassword) {
        isSignUpValid = AuthValidation.isSignUpValid(inputEmail, inputPassword)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF1B1B1B))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(WindowInsets.statusBars.asPaddingValues())
                .padding(WindowInsets.navigationBars.asPaddingValues()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "회원가입",
                    modifier = Modifier.align(Alignment.Center),
                    color = Color.White
                )
                Image(
                    painter = painterResource(R.drawable.img_signup_cancel),
                    contentDescription = "",
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .size(40.dp)
                        .clickable(onClick = navigateToSignIn)
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = buildAnnotatedString {
                    append("이메일과 전화번호만으로\nWavve를 즐길 수 있어요!")
                    addStyle(
                        style = SpanStyle(color = Color.White),
                        start = 0,
                        end = 9
                    )
                    addStyle(
                        style = SpanStyle(color = Color(0xFFA5A5A5)),
                        start = 9,
                        end = 12
                    )
                    addStyle(
                        style = SpanStyle(color = Color.White),
                        start = 13,
                        end = 24
                    )
                    addStyle(
                        style = SpanStyle(color = Color(0xFFA5A5A5)),
                        start = 25,
                        end = 29
                    )
                },
                modifier = Modifier.align(Alignment.Start),
                fontSize = 20.sp,
                lineHeight = 30.sp,
            )

            Spacer(modifier = Modifier.height(18.dp))

            AuthTextField(
                value = inputEmail,
                onValueChange = { newValue -> inputEmail = newValue },
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(4.dp))
                    .background(color = Color(0xFF262626)),
                hint = "wavve@example.com"
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(R.drawable.img_signup_caution),
                    contentDescription = "",
                    modifier = Modifier
                        .align(Alignment.Top)
                        .padding(2.dp)
                        .size(14.dp)
                )
                Text(
                    text = "로그인, 비밀번호 찾기, 알림에 사용되니 정확한 이메을 입력해주세요.",
                    fontSize = 12.sp,
                    lineHeight = 14.sp,
                    color = Color(0xFF848484)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            AuthTextField(
                value = inputPassword,
                onValueChange = { newValue -> inputPassword = newValue },
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(4.dp))
                    .background(color = Color(0xFF262626)),
                hint = "Wavve 비밀번호 설정",
                isPassword = true
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(R.drawable.img_signup_caution),
                    contentDescription = "",
                    modifier = Modifier
                        .align(Alignment.Top)
                        .padding(2.dp)
                        .size(14.dp)
                )
                Text(
                    text = "비밀번호는 8~20자 이내로 영문 대소문자, 숫자, 특수문자 중 3가지 이상 혼용하여 입력해주세요.",
                    fontSize = 12.sp,
                    lineHeight = 14.sp,
                    color = Color(0xFF848484)
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

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
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(WindowInsets.navigationBars.asPaddingValues())
                .background(color = if (isSignUpValid) Color(0xFF0F42C7) else Color.Gray)
                .clickable(
                    enabled = isSignUpValid,
                    onClick = {
                        showToast(context = context, message = "회원가입에 성공했습니다")
                        navigateToSignInWithInfo(inputEmail, inputPassword)
                    }
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Wavve 회원가입",
                modifier = Modifier.padding(10.dp),
                color = Color.White
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpPreview() {
    ANDANDROIDTheme {
        SignUpScreen(
            navigateToSignIn = {},
            navigateToSignInWithInfo = { _, _ ->  }
        )
    }
}