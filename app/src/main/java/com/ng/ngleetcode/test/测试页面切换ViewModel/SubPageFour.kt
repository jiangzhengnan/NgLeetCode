package com.ng.ngleetcode.test.测试页面切换ViewModel

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ng.base.utils.MLog


class SubPageFourViewModelFactory(
	private val params1: PrepareUiDelegate, private val params2: String
) : ViewModelProvider.Factory {

	override fun <T : ViewModel> create(modelClass: Class<T>): T {
		if (modelClass.isAssignableFrom(SubPageFourViewModel::class.java)) {
			@Suppress("UNCHECKED_CAST")
			return SubPageFourViewModel(
				params1,
				params2
			) as T
		}
		throw IllegalArgumentException("Unknown ViewModel class")
	}
}


class SubPageFourViewModel(private val params1: PrepareUiDelegate, private val params2: String) :
	ViewModel() {
	init {
		MLog.d("SubPageFourViewModel init params1:$params1 params2:$params2")
	}

	override fun onCleared() {
		super.onCleared()
		MLog.d("SubPageFourViewModel onCleared params1:$params1 params2:$params2")
	}
}

@Composable
fun SubPageFour() {
	val uiDelegate = remember {
		object
			: PrepareUiDelegate {
			override fun requestPermission(onSuccess: () -> Unit, onFailed: () -> Unit) {
			}

		}
	}
	createAndManageViewModel2 {
		val subViewModel: SubPageFourViewModel =
			viewModel(factory = SubPageFourViewModelFactory
				(object : PrepareUiDelegate {
				override fun requestPermission(onSuccess: () -> Unit, onFailed: () -> Unit) {

				}
			}, "bbb")
			)
		Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
			Text(
				text = "测试页面 SubPageFour",
			)
		}
		MLog.d("SubPageFour  重组 $subViewModel")
		DisposableEffect(Unit) {
			onDispose {
				MLog.d("SubPageFour onDispose")
			}
		}
	}
}


/**
 * 1.创建vm
 * 2.管理vm生命周期，在onDispose中清除
 */
@Composable
fun createAndManageViewModel2(
	content: @Composable () -> Unit
) {
	val independentViewModelStoreOwner = remember { IndependentViewModelStoreOwner() }
	CompositionLocalProvider(LocalViewModelStoreOwner provides independentViewModelStoreOwner,
		content = {
			content()
		}
	)
	DisposableEffect(Unit) {
		onDispose {
			independentViewModelStoreOwner.viewModelStore.clear()
		}
	}
}


