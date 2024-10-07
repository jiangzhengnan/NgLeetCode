package com.ng.ngleetcode.ui.page.code

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.ScaffoldState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.ng.ngleetcode.R
import com.ng.ngleetcode.theme.AppTheme
import com.ng.ngleetcode.theme.black
import com.ng.ngleetcode.theme.green3
import com.ng.ngleetcode.ui.page.code.mvi.CodeModel
import com.ng.ngleetcode.ui.page.code.mvi.CodeViewAction
import com.ng.ngleetcode.ui.page.code.mvi.CodeViewModel
import com.ng.ngleetcode.ui.page.code.mvi.CodeViewModelFactory
import com.ng.ngleetcode.ui.page.code.widgets.CodeShowLayout
import com.ng.ngleetcode.ui.page.code.widgets.FloatingActionMenu
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CodePage(
  navCtrl: NavHostController,
  scaffoldState: ScaffoldState,
) {
  val codeModel = CodeModel()
  val codeViewModel: CodeViewModel = viewModel(factory = CodeViewModelFactory(codeModel))

  // 左侧抽屉列表数据
  val codeViewStates = remember {
    codeViewModel.codeDrawerListState
  }
  // 当前正在展示的题目数据
  val codeShowStates = remember {
    codeViewModel.codeShowState
  }

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
            targetValue = (codeViewStates.value.nowPro.toFloat() / codeViewStates.value.allPro.toFloat()),
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

        // 目录+标题
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .height(45.dp)
            .padding(8.dp)
        ) {
          Box(
            modifier = Modifier
              .border(
                1.dp, Color.Black, shape = RoundedCornerShape(
                  topStart = 3.dp, topEnd = 3.dp, bottomEnd = 3.dp, bottomStart = 3.dp
                )
              ) // 设置边框
              .padding(start = 5.dp, end = 5.dp)
              .align(Alignment.CenterVertically)
          ) {
            Text(
              text = codeShowStates.value.getMenuStr().toString(),
              fontSize = 20.sp,
              color = black,
              fontWeight = FontWeight.Bold, // 设置字体为粗体
            )
          }
          Text(
            text = codeShowStates.value.title,
            fontSize = 16.sp,
            color = if (codeShowStates.value.state == 1) green3 else black,
            fontWeight = FontWeight.Bold, // 设置字体为粗体
            modifier = Modifier
              .align(Alignment.CenterVertically)
              .padding(start = 20.dp)
          )
        }

        // 内容
        Box(
          modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(1f)
            .padding(start = 8.dp, end = 8.dp, bottom = 66.dp) // 这里底部间距有问题。
        ) {
          CodeShowLayout(data = codeShowStates.value)
        }

      }
    }
  )

  // floating Btn
  Box(
    modifier = Modifier
      .fillMaxSize()
      .padding(bottom = 78.dp, end = 20.dp)  // 底部间距58
  ) {
    // 控制菜单展开和折叠的状态
    val (expanded, setExpanded) = remember { mutableStateOf(false) }
    FloatingActionMenu(
      modifier = Modifier
        .align(Alignment.BottomEnd),
      expanded = expanded,
      onExpandToggle = { setExpanded(!expanded) },
      codeViewModel
    )
  }

  // 数据初始化
  LaunchedEffect(true) {
    // 请求刷新数据
    codeViewModel.handIntent(CodeViewAction.Refresh)
    // 随机得到一道题用来显示
    codeViewModel.handIntent(CodeViewAction.GetRandomCode)
  }

}