package com.ng.ngleetcode.ui.page.code

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.ScaffoldState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.ng.ngleetcode.R
import com.ng.ngleetcode.theme.AppTheme
import com.ng.ngleetcode.theme.black
import com.ng.ngleetcode.ui.page.code.mvi.CodeModel
import com.ng.ngleetcode.ui.page.code.mvi.CodeViewAction
import com.ng.ngleetcode.ui.page.code.mvi.CodeViewModel
import com.ng.ngleetcode.ui.page.code.mvi.CodeViewModelFactory
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CodePage(
  navCtrl: NavHostController,
  scaffoldState: ScaffoldState,
) {
  val codeModel = CodeModel()
  val codeViewModel: CodeViewModel = viewModel(factory = CodeViewModelFactory(codeModel))
  val codeViewStates = remember { codeViewModel.codeDrawerListState }

  val drawerState = rememberDrawerState(DrawerValue.Closed)
  val scope = rememberCoroutineScope()

  ModalNavigationDrawer(
    drawerState = drawerState,
    drawerContent = {
      ModalDrawerSheet {
        CodeDrawerList(navCtrl, scaffoldState, scope, drawerState, codeViewModel)
      }
    },
    content = {
      Column(
        modifier = Modifier
          .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
      ) {

        // 顶部栏
        TopAppBar(
          windowInsets = AppBarDefaults.topAppBarWindowInsets,
          title = {
            Box(
              modifier = Modifier
                .fillMaxSize()
                .offset(x = (-25).dp, y = (-1).dp),
              contentAlignment = Alignment.Center,
            ) {
              Text(
                "题库", fontSize = 20.sp,
                modifier = Modifier.align(Alignment.Center)
              )
            }
          },
          modifier = Modifier.height(40.dp),
          navigationIcon = {
            IconButton(onClick = { scope.launch { drawerState.open() } }) {
              Icon(
                painterResource(id = R.drawable.ic_menu),
                contentDescription = null,
                modifier = Modifier.size(24.dp)
              )
            }
          },
          colors = TopAppBarColors(
            containerColor = AppTheme.colors.themeUi,
            scrolledContainerColor = AppTheme.colors.mainColor,
            navigationIconContentColor = AppTheme.colors.mainColor,
            titleContentColor = AppTheme.colors.mainColor,
            actionIconContentColor = AppTheme.colors.mainColor,
          )
        )

        // 进度条+进度
        Box(
          modifier = Modifier
            .fillMaxWidth()
            .height(20.dp)
        ) {
          val animatedProgress by
          animateFloatAsState(
            targetValue = (codeViewStates.value.nowPro / codeViewStates.value.allPro).toFloat(),
            animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec
          )
          LinearProgressIndicator(
            modifier = Modifier.fillMaxSize(),
            progress = { animatedProgress })
          Text(
            "${codeViewStates.value.nowPro} - ${codeViewStates.value.allPro}",
            fontSize = 11.sp,
            color = black,
            modifier = Modifier.align(Alignment.Center)
          )
        }

        // 题目标题



        // 请求刷新数据
        codeViewModel.handIntent(CodeViewAction.Refresh)
      }
    }
  )


}