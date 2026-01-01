package com.ng.ngleetcode.ui

import android.Manifest
import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.ng.base.utils.ToastUtils
import com.ng.ngleetcode.R
import com.ng.ngleetcode.app.theme.AppTheme
import com.ng.ngleetcode.app.theme.white

/**
 */
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun SplashPage(activity: ComponentActivity, onNextPage: () -> Unit) {
  Box(
    modifier = Modifier
      .fillMaxSize()
      .background(AppTheme.colors.themeUi),
    contentAlignment = Alignment.Center
  ) {
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


    //需要申请的权限列表
    val permissions = arrayListOf(
      Manifest.permission.WRITE_EXTERNAL_STORAGE,
      Manifest.permission.READ_EXTERNAL_STORAGE
    )

    val permissionState = rememberMultiplePermissionsState(permissions = permissions)

    if (permissionState.allPermissionsGranted) {
      LaunchedEffect(Unit) {
        onNextPage.invoke()
      }
    } else if (permissionState.shouldShowRationale) {
      ToastUtils.showShort(activity, "没有权限!")
    } else {
      ToastUtils.showShort(activity, "没有权限，需要申请")
      DisposableEffect(permissionState) {
        permissionState.launchMultiplePermissionRequest()
        onNextPage.invoke()
        onDispose {  }
      }
    }

  }

}