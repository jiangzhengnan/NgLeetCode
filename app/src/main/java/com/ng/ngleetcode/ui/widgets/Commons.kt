package com.ng.ngleetcode.ui.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.ng.ngleetcode.theme.AppTheme
import com.ng.ngleetcode.theme.H5
import com.ng.ngleetcode.theme.ToolBarHeight
import com.ng.ngleetcode.theme.ToolBarTitleSize
import com.ng.ngleetcode.ui.page.main.BottomNavRoute


/**
 * 普通标题栏头部
 */
@Composable
fun AppToolsBar(
    title: String,
    rightText: String? = null,
    onBack: (() -> Unit)? = null,
    onRightClick: (() -> Unit)? = null,
    imageVector: ImageVector? = null,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(ToolBarHeight)
            .background(AppTheme.colors.themeUi)
    ) {
        Row(modifier = Modifier.fillMaxSize()) {
            if (onBack != null) {
                Icon(
                    Icons.Default.ArrowBack,
                    null,
                    Modifier
                        .clickable(onClick = onBack)
                        .align(Alignment.CenterVertically)
                        .padding(12.dp),
                    tint = AppTheme.colors.mainColor
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            if (!rightText.isNullOrEmpty() && imageVector == null) {
                TextContent(
                    text = rightText,
                    color = AppTheme.colors.mainColor,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(horizontal = 20.dp)
                        .clickable { onRightClick?.invoke() }
                )
            }

            if (imageVector != null) {
                Icon(
                    imageVector = imageVector,
                    contentDescription = null,
                    tint = AppTheme.colors.mainColor,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(end = 12.dp)
                        .clickable {
                            onRightClick?.invoke()
                        })
            }
        }
        Text(
            text = title,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = 40.dp),
            color = AppTheme.colors.mainColor,
            textAlign = TextAlign.Center,
            fontSize = if (title.length > 14) H5 else ToolBarTitleSize,
            fontWeight = FontWeight.W500,
            maxLines = 1
        )

    }
}

@Composable
fun BottomNavBarView(navCtrl: NavHostController) {
    val bottomNavList = listOf(
        BottomNavRoute.Code,
        BottomNavRoute.Statistics,
        BottomNavRoute.Read,
        BottomNavRoute.Profile
    )
    BottomNavigation {
        val navBackStackEntry by navCtrl.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        bottomNavList.forEach { screen ->
            BottomNavigationItem(
                modifier = Modifier.background(AppTheme.colors.themeUi),
                icon = {
                    Icon(
                        imageVector = screen.icon,
                        contentDescription = null
                    )
                },
                label = { Text(text = stringResource(screen.stringId)) },
                selected = currentDestination?.hierarchy?.any { it.route == screen.routeName } == true,
                onClick = {
                    println("BottomNavView当前路由 ===> ${currentDestination?.hierarchy?.toList()}")
                    println("当前路由栈 ===> ${navCtrl.graph.nodes}")
                    if (currentDestination?.route != screen.routeName) {
                        navCtrl.navigate(screen.routeName) {
                            popUpTo(navCtrl.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                })
        }
    }
}


