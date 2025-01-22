package com.ng.ngleetcode.test.测试页面切换ViewModel

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ng.base.utils.MLog

@Composable
fun SubPageOne() {
	val independentViewModelStoreOwner = remember { IndependentViewModelStoreOwner() }

	val subViewModel: SubPageOneViewModel = viewModel(
		independentViewModelStoreOwner,
		factory = SubPageOneViewModelFactory("params")
	)

	Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
		Text(
			text = "测试页面 SubPageOne",
		)
	}

	MLog.d("SubPageOne  重组 $subViewModel")

	DisposableEffect(Unit) {
		onDispose {
			MLog.d("SubPageOne onDispose")
			independentViewModelStoreOwner.viewModelStore.clear()
			// 清理资源以防止内存泄漏
			//independentViewModelStoreOwner.getViewModelStore().clear()
		}
	}
}

class IndependentViewModelStoreOwner : ViewModelStoreOwner {
	private val _viewModelStore = ViewModelStore()
	override val viewModelStore: ViewModelStore
		get() = _viewModelStore
}

class SubPageOneViewModel(private val params: String) : ViewModel() {

	init {
		MLog.d("SubPageOneViewModel init")
	}

	override fun onCleared() {
		super.onCleared()
		MLog.d("SubPageOneViewModel onCleared")
	}
}


class SubPageOneViewModelFactory(
	private val params: String,
) : ViewModelProvider.Factory {

	override fun <T : ViewModel> create(modelClass: Class<T>): T {
		if (modelClass.isAssignableFrom(SubPageOneViewModel::class.java)) {
			@Suppress("UNCHECKED_CAST")
			return SubPageOneViewModel(params) as T
		}
		throw IllegalArgumentException("Unknown ViewModel class")
	}
}