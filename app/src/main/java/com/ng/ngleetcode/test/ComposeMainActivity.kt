package com.ng.ngleetcode.test

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import com.ng.ngleetcode.app.AppScope
import com.ng.ngleetcode.test.协程启动框架.TaskManager


class ComposeMainActivity : ComponentActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			Text("Hello World in Compose!")
		}
		TaskManager.onActivityCreate(AppScope)

	}


}