package com.ng.ngleetcode.ui.page.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.ng.ngleetcode.R
import com.ng.ngleetcode.app.theme.AppTheme
import com.ng.ngleetcode.app.theme.BottomNavBarHeight
import com.ng.ngleetcode.app.theme.white
import com.ng.ngleetcode.ui.page.main.RouteName
import com.ng.ngleetcode.ui.page.web.WebData
import com.ng.ngleetcode.ui.widgets.ArrowRightListItem
import com.ng.ngleetcode.ui.widgets.MainTitle
import com.ng.ngleetcode.utils.RouteUtils
import kotlinx.coroutines.launch

@Composable
fun ProfilePage(
  navCtrl: NavHostController,
  scaffoldState: ScaffoldState,
  profileViewModel: ProfileViewModel = viewModel()
) {

  val scope = rememberCoroutineScope()

  Column(
    modifier = Modifier
      .padding(bottom = BottomNavBarHeight)
      .fillMaxSize()
      .background(color = AppTheme.colors.mainColor)
  ) {
//    AppToolsBar(title = "我的")
    // 头像 + 昵称
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .height(180.dp)
        .background(color = AppTheme.colors.themeUi)
    ) {
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .padding(top = 30.dp)
      ) {
        Image(
          painter = painterResource(id = R.mipmap.pumpkin_icon),
          contentDescription = null,
          modifier = Modifier
            .size(80.dp)
            .clip(CircleShape)
            .align(Alignment.CenterHorizontally)
        )
        MainTitle(title = "Pumpkin", Modifier.align(Alignment.CenterHorizontally), color = white)
      }
    }

    //Github主页
    ArrowRightListItem(
      iconRes = Icons.Default.Home,
      title = "Github 主页"
    ) {
      RouteUtils.navTo(
        navCtrl = navCtrl,
        destinationName = RouteName.WEB_VIEW,
        args = WebData(title = "Github", url = "https://github.com/jiangzhengnan")
      )
    }

    // 重置进度（清除缓存）
    ArrowRightListItem(
      iconRes = Icons.Default.Settings,
      title = "重置进度"
    ) {
      profileViewModel.handIntent(ProfileViewAction.ClearCache)
      scope.launch {
        scaffoldState.snackbarHostState.showSnackbar("重置进度成功", null, SnackbarDuration.Short)
      }
    }

  }



}