package org.sopt.and.presentation.ui.main.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.R

@Composable
fun MyPageScreen(
    paddingValues: PaddingValues,
    email: String = ""
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color(0xFF161616))
            .padding(paddingValues)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color(0xFF1E1E1E))
                .padding(vertical = 20.dp, horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_mypage_dummy_profile),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(70.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = email + "님",
                color = Color.White
            )
            Spacer(modifier = Modifier.weight(1f))
            Image(
                painter = painterResource(id = R.drawable.img_mypage_notification),
                contentDescription = "",
                modifier = Modifier
                    .padding(8.dp)
                    .size(28.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.img_mypage_setting),
                contentDescription = "",
                modifier = Modifier
                    .padding(8.dp)
                    .size(28.dp)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color(0xFF1E1E1E))
                .padding(horizontal = 12.dp)
                .padding(top = 6.dp, bottom = 18.dp)
        ) {
            Text(
                text = "첫 결제 시 첫 달 100원!",
                color = Color.Gray
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "구매하기",
                    color = Color(0xFFCCCCCC)
                )
                Image(
                    painter = painterResource(id = R.drawable.img_mypage_arrow_forward),
                    contentDescription = "",
                    modifier = Modifier.size(24.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(1.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color(0xFF1E1E1E))
                .padding(horizontal = 12.dp)
                .padding(top = 6.dp, bottom = 18.dp)
        ) {
            Text(
                text = "현재 보유하신 이용권이 없습니다.  ",
                color = Color.Gray
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "구매하기",
                    color = Color(0xFFCCCCCC)
                )
                Image(
                    painter = painterResource(id = R.drawable.img_mypage_arrow_forward),
                    contentDescription = "",
                    modifier = Modifier.size(24.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(12.dp)
        ) {
            Text(
                text = "전체 시청내역",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFCCCCCC)
            )
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.img_mypage_empty_content),
                        contentDescription = "",
                        modifier = Modifier.size(80.dp)
                    )
                    Text(
                        text = "시청내역이 없어요.",
                        color = Color.Gray
                    )
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(12.dp)
        ) {
            Text(
                text = "관심 프로그램",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFCCCCCC)
            )
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.img_mypage_empty_content),
                        contentDescription = "",
                        modifier = Modifier.size(80.dp)
                    )
                    Text(
                        text = "관심 프로그램이 없어요.",
                        color = Color.Gray
                    )
                }
            }
        }
    }
}