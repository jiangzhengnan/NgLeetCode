package com.ng.ngleetcode.test.compose嵌套频繁重组问题

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun MainPage() {

	var nowPageIndex by remember { mutableIntStateOf(0) }

	var topOperationRow by remember {
		mutableStateOf<@Composable (RowScope.() -> Unit)?>(
			null
		)
	}

	Column(modifier = Modifier.fillMaxSize()) {
		Box(
			modifier = Modifier
				.fillMaxWidth()
				.weight(1f)
		) {
			if (nowPageIndex == 0) {
				PageOne {
					topOperationRow = it
				}
			} else {
				PageTwo()
			}
		}

		BottomController({ nowPageIndex = it }, topOperationRow)
	}

}

@Composable
fun PageOne(
	registerTopOperationRow: ((@Composable (RowScope.() -> Unit)?) -> Unit)?,
) {
	Column(modifier = Modifier.fillMaxSize()) {
		Text(text = "Page One")
	}

	registerTopOperationRow?.let {
		it {
			Row(
				Modifier
					.fillMaxWidth()
					.height(50.dp)
			) {
				Text("Page One 操作1")
				Text("Page One 操作2")
				Text("Page One 操作3")
			}
		}
	}
}

@Composable
fun PageTwo() {
	Column(modifier = Modifier.fillMaxSize()) {
		Text(text = "Page Two")
	}
}

@Composable
fun BottomController(
	onTabSelect: (Int) -> Unit = {},
	topOperationRow: @Composable (RowScope.() -> Unit)?
) {

	var nowPageIndex by remember { mutableIntStateOf(0) }

	val topOperation by remember {
		derivedStateOf { nowPageIndex }
	}
	Column(
		modifier = Modifier
			.fillMaxWidth()
	) {
		Row(
			modifier = Modifier
				.height(50.dp)
				.fillMaxWidth()
				.padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 12.dp),
			verticalAlignment = Alignment.CenterVertically
		) {
			// 这里有问题
			if (topOperation == 0 && topOperationRow != null) {
				topOperationRow()
			}
		}
		Row(
			modifier = Modifier
				.height(50.dp)
				.fillMaxWidth()
		) {
			Button(
				{
					nowPageIndex = 0
				},
				modifier = Modifier
					.fillMaxHeight()
					.weight(1f),
			) {
				Text(text = "Page One")
			}
			Button(
				{
					nowPageIndex = 1
				},
				modifier = Modifier
					.fillMaxHeight()
					.weight(1f),
			) {
				Text(text = "Page Two")
			}
		}
	}

	LaunchedEffect(nowPageIndex) {
		onTabSelect.invoke(nowPageIndex)
	}
}
