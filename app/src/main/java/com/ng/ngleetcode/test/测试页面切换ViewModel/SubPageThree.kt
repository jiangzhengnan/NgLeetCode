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
import com.ng.base.utils.MLog

interface PrepareUiDelegate {
	fun requestPermission(onSuccess: () -> Unit, onFailed: () -> Unit)
}

class SubPageThreeViewModel(private val params1: String, private val params2: String) :
	ViewModel() {
	init {
		MLog.d("SubPageThreeViewModel init params1:$params1 params2:$params2")
	}

	override fun onCleared() {
		super.onCleared()
		MLog.d("SubPageThreeViewModel onCleared params1:$params1 params2:$params2")
	}
}

@Composable
fun SubPageThree() {
	val uiDelegate = remember {
		object
			: PrepareUiDelegate {
			override fun requestPermission(onSuccess: () -> Unit, onFailed: () -> Unit) {
			}

		}
	}
	val subViewModel: SubPageThreeViewModel = createAndManageViewModel("aaa", "bbb")
	Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
		Text(
			text = "测试页面 SubPageThree",
		)
	}
	MLog.d("SubPageThree  重组 $subViewModel")
	DisposableEffect(Unit) {
		onDispose {
			MLog.d("SubPageThree onDispose")
		}
	}
}

/**
 * 1.创建vm
 * 2.管理vm生命周期，在onDispose中清除
 */
@Composable
inline fun <reified T : ViewModel> createAndManageViewModel(
	vararg args: Any,
	key: String? = null,
): T {
	val factory = createViewModelFactory3(*args)
	val independentViewModelStoreOwner = remember { IndependentViewModelStoreOwner() }
	val viewModel = remember(key) {
		ViewModelProvider(independentViewModelStoreOwner, factory)[T::class.java]
	}
	DisposableEffect(Unit) {
		onDispose {
			independentViewModelStoreOwner.viewModelStore.clear()
		}
	}
	return viewModel
}



/**
 * 反射创建viewModel，缺点是参数错误无法被ide提示。
 */
fun createViewModelFactory3(vararg args: Any): ViewModelProvider
.Factory {
	return object : ViewModelProvider.Factory {
		override fun <T : ViewModel> create(modelClass: Class<T>): T {
			val constructor = modelClass.getConstructor(*args.map { it::class.java }.toTypedArray())
			return constructor.newInstance(*args) as T
		}
	}
}





