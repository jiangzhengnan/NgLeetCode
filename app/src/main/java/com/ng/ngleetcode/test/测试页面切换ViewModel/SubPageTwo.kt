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
import com.ng.base.utils.MLog

@Composable
fun SubPageTwo() {
	val subViewModel: SubPageTwoViewModel =
		rememberAndManageViewModel(createViewModelFactory2 { "params" })

	Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
		Text(
			text = "测试页面 SubPageTwo",
		)
	}

	MLog.d("SubPageTwo  重组 $subViewModel")

	DisposableEffect(Unit) {
		onDispose {
			MLog.d("SubPageTwo onDispose")

		}
	}
}

class SubPageTwoViewModel(private val params: String) : ViewModel() {

	init {
		MLog.d("SubPageTwoViewModel init")
	}

	override fun onCleared() {
		super.onCleared()
		MLog.d("SubPageTwoViewModel onCleared")
	}
}

@Composable
inline fun <reified T : ViewModel> rememberAndManageViewModel(
	factory: ViewModelProvider.Factory,
	key: String? = null,
): T {
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


inline fun createViewModelFactory2(noinline constructorArg: () -> Any): ViewModelProvider.Factory {
	return object : ViewModelProvider.Factory {
		override fun <T : ViewModel> create(modelClass: Class<T>): T {
			val constructor = modelClass.getConstructor(String::class.java)
			return constructor.newInstance(constructorArg()) as T
		}
	}
}
