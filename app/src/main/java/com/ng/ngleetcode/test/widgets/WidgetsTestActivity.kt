package com.ng.ngleetcode.test.widgets

import android.os.Build
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

data class PhoneNumberConfig(
    val rules: List<PhoneNumberRule>
)

data class PhoneNumberRule(val area: String, val len: Int)

@RequiresApi(Build.VERSION_CODES.R)
class WidgetsTestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MockCompose()
        }
    }

}

@Preview
@Composable
fun PhoneNumberInputPreview() {
    MockCompose()
}

@Composable
fun MockCompose() {
    MaterialTheme {
        Surface(Modifier.padding(10.dp)) {
            var isDropdownVisible by remember { mutableStateOf(false) }

            val config = PhoneNumberConfig(
                listOf(
                    PhoneNumberRule(area = "+86", len = 11),
                    PhoneNumberRule(area = "+852", len = 8),
                    PhoneNumberRule(area = "+1", len = 10),
                    PhoneNumberRule(area = "+81", len = 10),
                    PhoneNumberRule(area = "+65", len = 8),
                    PhoneNumberRule(area = "+60", len = 9),
                    PhoneNumberRule(area = "+91", len = 10)
                )
            )
            PhoneInputWidget(config, isDropdownVisible) {
                isDropdownVisible = it
            }
        }
    }
}
