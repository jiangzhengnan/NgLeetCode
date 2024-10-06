package com.ng.ngleetcode.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cj.myapplication.ui.theme.white
import com.ng.ngleetcode.R
import com.ng.ngleetcode.theme.AppTheme
import kotlinx.coroutines.delay

@Composable
fun SplashPage(onNextPage: () -> Unit) {
  Box(
    modifier = Modifier
      .fillMaxSize()
      .background(AppTheme.colors.themeUi),
    contentAlignment = Alignment.Center
  ) {
    LaunchedEffect(Unit) {
      delay(500)
      onNextPage.invoke()
    }
    Column(
      horizontalAlignment = Alignment.CenterHorizontally,
    ) {
      Image(
        painter = painterResource(id = R.mipmap.app_logo),
        contentDescription = null,
        modifier = Modifier
          .size(50.dp)
          .clip(CircleShape)
      )
      Text(
        text = "NgLeetCode",
        fontSize = 15.sp,
        color = white,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(0.dp, 50.dp, 0.dp, 0.dp)
      )
    }
  }
}