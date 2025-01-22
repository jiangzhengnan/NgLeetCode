package com.ng.ngleetcode.test.测试页面切换ViewModel

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TestChangePage() {
	Column(modifier = Modifier.fillMaxSize()) {
		// 测试三个页面来回切换
		var nowState by remember { mutableIntStateOf(0) }
		Box(
			modifier = Modifier
				.fillMaxWidth()
				.weight(1f)
		) {

			when (nowState) {
				0 -> {
					SubPageOne()
				}

				1 -> {
					SubPageTwo()
				}

				2 -> {
					SubPageThree()
				}

				3 -> {
					SubPageFour()
				}
			}
		}
		Row(
			modifier = Modifier
				.fillMaxWidth()
				.height(50.dp)
		) {
			Button(
				{
					nowState = 0
				}, modifier = Modifier
					.fillMaxHeight()
					.weight(1f)
			) {
				Text("页面1 ")
			}
			Button(
				{
					nowState = 1
				}, modifier = Modifier
					.fillMaxHeight()
					.weight(1f)
			) {
				Text("页面2 ")
			}
			Button(
				{
					nowState = 2
				}, modifier = Modifier
					.fillMaxHeight()
					.weight(1f)
			) {
				Text("页面3 ")
			}
			Button(
				{
					nowState = 3
				}, modifier = Modifier
					.fillMaxHeight()
					.weight(1f)
			) {
				Text("页面4 ")
			}

		}
	}
}