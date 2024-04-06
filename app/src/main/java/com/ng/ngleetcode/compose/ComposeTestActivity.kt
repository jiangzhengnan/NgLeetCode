package com.ng.ngleetcode.compose

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.cj.myapplication.ui.theme.MyApplicationTheme
import com.ng.ngleetcode.R
import com.ng.ngleetcode.compose.view.CircularAnimProgressView

class ComposeTestActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      PreviewMessageCard()
//      MyApplicationTheme() {
//        MessageCard(msg = Message("博物馆", "我们开始更新啦"))
//      }
    }
  }


}

data class Message(val author: String, val body: String)

@Composable
private fun MessageCard(msg: Message) {

  var isExpanded by remember { mutableStateOf(false) } // 创建一个能够检测卡片是否被展开的变量

//  var isExpanded by mutableStateOf(false) // 创建一个能够检测卡片是否被展开的变量

  Surface(
    shape = MaterialTheme.shapes.medium, // 使用 MaterialTheme 自带的形状
    elevation = 5.dp,
    modifier = Modifier
      .padding(all = 8.dp)
      .clickable { // 添加一个新的 Modifier 扩展方法，可以让元素具有点击的效果
        isExpanded = !isExpanded // 编写点击的事件内容
      }
  ) {
    Row(
      modifier = Modifier.padding(all = 8.dp)
    ) {
      Image(
        painter = painterResource(id = R.mipmap.app_logo),
        contentDescription = null,
        modifier = Modifier
          .size(30.dp)
          .clip(CircleShape)
      )
      Spacer(Modifier.padding(horizontal = 8.dp))
      Column() {
        Text(
          text = msg.author + "a",
          color = Color.Red, // 添加颜色
          style = MaterialTheme.typography.subtitle2 // 添加 style
        )
        Spacer(Modifier.padding(vertical = 8.dp))
        Text(
          text = msg.body,
          style = MaterialTheme.typography.body2, // 添加 style
          // 修改 maxLines 参数，在默认情况下，只显示一行文本内容
          maxLines = if (isExpanded) Int.MAX_VALUE else 1,
          // Composable 大小的动画效果
          modifier = Modifier.animateContentSize()
        )

      }
    }
  }
}

@Preview(name = "Light Mode")
@Preview(
  uiMode = Configuration.UI_MODE_NIGHT_YES,
  showBackground = true,
  name = "Dark Mode"
)
@Composable
fun PreviewMessageCard() {

  MyApplicationTheme {
    var isExpanded by mutableStateOf(false)  // 创建一个能够检测卡片是否被展开的变量
    var text by mutableStateOf("hello")
    if (isExpanded) {
      Text(text = text)
    }

//    Row() {
//      Text(text = text)
//      Button(onClick = { text = "world" }) {
//        Text("Update Text")
//      }
//    }

//    CustomView()
//    Conversation(messages = MsgData.messages)
//    MessageCard(msg = Message("博物馆", "我们开始更新啦"))
  }
}


//如何使用自定义View
@Composable
fun CustomView() {
  AndroidView(
    modifier = Modifier.fillMaxSize(), // Occupy the max size in the Compose UI tree
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



