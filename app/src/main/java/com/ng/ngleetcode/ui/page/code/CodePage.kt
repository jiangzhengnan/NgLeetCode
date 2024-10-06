package com.ng.ngleetcode.ui.page.code

import androidx.compose.foundation.layout.*
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.ScaffoldState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.ng.ngleetcode.R
import com.ng.ngleetcode.theme.AppTheme
import com.ng.ngleetcode.ui.page.code.mvi.CodeModel
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
        Text(text = if (drawerState.isClosed) ">>> Swipe >>>" else "<<< Swipe <<<")
        Spacer(Modifier.height(20.dp))
      }
    }
  )


}