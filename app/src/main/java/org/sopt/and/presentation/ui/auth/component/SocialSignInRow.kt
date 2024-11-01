package org.sopt.and.presentation.ui.auth.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import org.sopt.and.R

@Composable
fun SocialSignInRow(
    images: List<Int>,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        images.forEach { image ->
            Image(
                painter = painterResource(id = image),
                contentDescription = "",
                modifier = Modifier
                    .clickable { onClick() }
                    .padding(vertical = 10.dp, horizontal = 4.dp)
                    .size(50.dp),
                contentScale = ContentScale.Crop
            )
        }
    }
}

val SocialSignInList = listOf(
    R.drawable.img_auth_kakao,
    R.drawable.img_auth_skt,
    R.drawable.img_auth_naver,
    R.drawable.img_auth_facebook,
    R.drawable.img_auth_apple,
)