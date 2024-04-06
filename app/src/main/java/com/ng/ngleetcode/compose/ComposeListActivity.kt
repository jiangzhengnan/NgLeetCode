package com.ng.ngleetcode.compose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ng.ngleetcode.R
import com.ng.ngleetcode.compose.ComposeListActivity.UserData.messages

//实现一个列表
class ComposeListActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      Conversation(userList = messages)
    }
  }

  @Composable
  fun Conversation(userList: List<User>) {
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

