package com.ng.ngleetcode.compose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.ng.ngleetcode.R
import com.ng.ngleetcode.compose.view.CircularAnimProgressView

class ComposeHomeActivity : ComponentActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      //MyApp()
      CustomApp()
    }
  }

  @Composable
  fun CustomApp() {
    var currentScreen by remember { mutableStateOf<Screen>(Screen.Home) }

    Scaffold(
      bottomBar = {
        Row(
          Modifier
            .height(84.dp)
            .padding(16.dp),
          verticalAlignment = Alignment.CenterVertically
        ) {
          NavItem(iconRes = R.mipmap.home, desc = "主页", Color.Blue) {
            currentScreen = Screen.Home
          }
          NavItem(iconRes = R.mipmap.project, desc = "项目", Color.Gray) {
            currentScreen = Screen.Project
          }
          NavItem(iconRes = R.mipmap.mine, desc = "我的", Color.Gray) {
            currentScreen = Screen.User
          }
        }
      }
    ) { innerPadding ->
      when (currentScreen) {
        Screen.Home -> HomeScreen(UserData.messages)
        Screen.Project -> ProjectScreen(Modifier.padding(innerPadding))
        Screen.User -> UserScreen(Modifier.padding(innerPadding))
      }
    }
  }

  // 避免给每个Icon重复设置，将Icon抽成独立的自定义参数组件
  @Composable
  fun RowScope.NavItem(
    @DrawableRes iconRes: Int,
    desc: String,
    tint: Color,
    click: () -> Unit,
  ) {
    Icon(
      painter = painterResource(id = iconRes), contentDescription = desc,
      Modifier
        .size(24.dp)
        .weight(1f)
        .clickable {
          click.invoke()
        },  //宽度比重，必须要在RowScope下才能设置。
      tint = tint,

      )
  }

  @Composable
  fun MyApp() {
    var currentScreen by remember { mutableStateOf<Screen>(Screen.Home) }

    Scaffold(
      bottomBar = {
        BottomNavigation {
          BottomNavigationItem(
            icon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
            label = { Text("Home") },
            selected = currentScreen == Screen.Home,
            onClick = { currentScreen = Screen.Home }
          )
          BottomNavigationItem(
            icon = { Icon(Icons.Filled.Favorite, contentDescription = "Favorites") },
            label = { Text("Favorites") },
            selected = currentScreen == Screen.Project,
            onClick = { currentScreen = Screen.Project }
          )
          BottomNavigationItem(
            icon = { Icon(Icons.Filled.Settings, contentDescription = "Settings") },
            label = { Text("Settings") },
            selected = currentScreen == Screen.User,
            onClick = { currentScreen = Screen.User }
          )
        }
      }
    ) { innerPadding ->
      when (currentScreen) {
        Screen.Home -> HomeScreen(UserData.messages)
        Screen.Project -> ProjectScreen(Modifier.padding(innerPadding))
        Screen.User -> UserScreen(Modifier.padding(innerPadding))
      }
    }
  }

  sealed class Screen {
    object Home : Screen()
    object Project : Screen()
    object User : Screen()
  }


  @Composable
  fun ProjectScreen(modifier: Modifier = Modifier) {
    AndroidView(
      modifier = Modifier.size(150.dp), // Occupy the max size in the Compose UI tree
      factory = { context ->
        // Creates view
        CircularAnimProgressView(context)
      },
      update = { view ->
        // 视图已膨胀或此块中读取的状态已更新
        // 如有必要，在此处添加逻辑
        // 由于selectedItem在此处阅读，AndroidView将重新组合
        // 每当状态发生变化时
        // 撰写示例->查看通信
      }
    )
  }

  @Composable
  fun UserScreen(modifier: Modifier = Modifier) {
    Box(modifier.fillMaxSize()) {
      Text(text = "Settings Screen", modifier = Modifier.align(Alignment.Center))
    }
  }


  @Composable
  fun HomeScreen(userList: List<User>) {
    LazyColumn {
      items(userList) { it ->
        UserItem(user = it)
      }
    }
  }

  //ui item
  @Composable
  private fun UserItem(user: User) {
    Row(
      modifier = Modifier
        .padding(all = 8.dp)
        .clickable {
          Toast
            .makeText(this, "click:$user", Toast.LENGTH_SHORT)
            .show()

        },
      verticalAlignment = Alignment.CenterVertically,
    ) {
      //头像
      Image(
        painter = painterResource(id = R.mipmap.mine),
        contentDescription = null,
        modifier = Modifier
          .size(30.dp)
          .clip(CircleShape)
      )
      Spacer(Modifier.padding(horizontal = 8.dp))
      Column() {
        //名字
        Text(
          text = user.name
        )
        //间隔
        Spacer(Modifier.padding(vertical = 8.dp))
        //职业
        Text(
          text = user.job,
        )
      }
    }
  }

  //数据源
  data class User(val name: String, val job: String)

  object UserData {
    val messages = listOf(
      User("小王", "厨师"),
      User("大明", "司机"),
      User("小李 ", "外卖员"),
    )
  }


}
