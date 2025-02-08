package com.ng.ngleetcode.test.study

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import kotlinx.coroutines.delay

class CustomComposeActivity : ComponentActivity() {


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContent {
      val welComeText by remember { mutableStateOf("欢迎光临!") }

      val welComeText2 by rememberUpdatedState(newValue = {
        "$welComeText!"
      })
      LaunchedEffect(key1 = Unit) {
        delay(3000)
        println("@@= $welComeText")
      }

    }

  }


}

