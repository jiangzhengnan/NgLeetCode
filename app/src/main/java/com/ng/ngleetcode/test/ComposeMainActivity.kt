package com.ng.ngleetcode.test

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.ng.ngleetcode.test.compose嵌套频繁重组问题.MainPage

/**
 * Compose测试
 */
class ComposeMainActivity : ComponentActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			MainPage()

		}
	}



}