package com.ng.ngleetcode.test.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ng.ngleetcode.test.clickableX

/**
 * 支持区号选择的电话号码输入组件，包括输入框与下一步按钮
 */
@Composable
fun PhoneInputWidget(
    config: PhoneNumberConfig,
    isDropdownVisible: Boolean = true,
    dropDownController: (Boolean) -> Unit
) {
    Box(
        Modifier
            .fillMaxWidth()
            .clickableX { dropDownController.invoke(false) }
    ) {
        Box(Modifier.padding(top = 72.dp)) {
            Text(
                text = "下一步",
                Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFBDBDBD), RoundedCornerShape(8.dp))
                    .height(48.dp)
                    .wrapContentHeight(Alignment.CenterVertically),
                textAlign = TextAlign.Center,
            )
        }

        PhoneNumberInput(config, isDropdownVisible, dropDownController)

    }

}

@Composable
fun PhoneNumberInput(
    config: PhoneNumberConfig,
    isDropdownVisible: Boolean = true,
    dropDownController: (Boolean) -> Unit
) {
    var selectedRule by remember { mutableStateOf(config.rules.first()) }
    var phoneNumber by remember { mutableStateOf("") }
    var containerSize by remember { mutableStateOf(0.dp) }
    val density = LocalDensity.current // 正确获取方式
    val containerHeight = 48 // 容器高度

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .onGloballyPositioned { coordinates ->
                containerSize = with(density) {
                    coordinates.size.width.toDp()
                }
            }
    ) {
        // 主输入区域
        InputLayout(
            containerHeight = containerHeight,
            isDropdownVisible = isDropdownVisible,
            selectedRule = selectedRule,
            phoneNumber = phoneNumber,
            onPhoneNumberChange = { phoneNumber = it.take(selectedRule.len) },
            onAreaClick = { dropDownController.invoke(true) }
        )

        // 自定义下拉菜单
        if (isDropdownVisible) {
            AreaCodeDropdown(
                containerWidth = containerSize,
                rules = config.rules,
                selectedRule = selectedRule,
                onRuleSelected = {
                    selectedRule = it
                    dropDownController.invoke(false)
                }
            )
        }
    }
}

@Composable
private fun InputLayout(
    containerHeight: Int,
    isDropdownVisible: Boolean,
    selectedRule: PhoneNumberRule,
    phoneNumber: String,
    onPhoneNumberChange: (String) -> Unit,
    onAreaClick: () -> Unit
) {
    val colorContainer = if (isDropdownVisible) Color.White else Color(0x0A000000)
    val colorBorder = if (isDropdownVisible) Color.Red else Color(0x0A000000)
    val cornerBorder = if (isDropdownVisible) RoundedCornerShape(
        topStart = 8.dp,
        topEnd = 8.dp
    ) else RoundedCornerShape(8.dp)
    Box(Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(containerHeight.dp)
                .background(colorContainer, RoundedCornerShape(8.dp))
                .border(1.dp, colorBorder, cornerBorder),
            verticalAlignment = Alignment.CenterVertically
        ) {

            // 区号选择区域
            AreaCodeSelector(
                modifier = Modifier.height(containerHeight.dp),
                areaCode = selectedRule.area,
                onClick = onAreaClick,
            )

            // 手机号输入
            PhoneInputField(
                value = phoneNumber,
                onValueChange = onPhoneNumberChange,
                modifier = Modifier.weight(1f)
            )

        }

        // 覆盖底部边框
        if (isDropdownVisible) {
            Box(
                Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .height(1.dp)
                    .padding(start = 1.dp, end = 1.dp)
            ) {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(colorContainer)
                )
            }
        }

    }
}

@Composable
private fun AreaCodeSelector(
    areaCode: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .clickableX { onClick.invoke() }
            .padding(start = 16.dp, end = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = areaCode,
            style = MaterialTheme.typography.bodyMedium,
            fontSize = 16.sp,
            fontWeight = FontWeight(500),
            color = Color.Black,
        )
        Spacer(modifier = Modifier.width(4.dp))
        Icon(
            imageVector = Icons.Default.ArrowDropDown,
            contentDescription = "选择区号",
            modifier = Modifier
                .size(16.dp)
                .padding(1.dp)
        )
    }
}

@Composable
private fun PhoneInputField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    // 自动获取焦点并弹键盘
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
        keyboardController?.show()
    }

    BasicTextField(
        value = value,
        onValueChange = { newValue ->
            if (newValue.all { it.isDigit() }) {
                onValueChange(newValue)
            }
        },
        // 设置光标颜色为绿色
        cursorBrush = SolidColor(Color.Green),
        modifier = modifier.focusRequester(focusRequester),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
        textStyle = LocalTextStyle.current.copy(
            color = Color.Black,
            fontSize = 16.sp
        ),
        decorationBox = { innerTextField ->
            Box {
                if (value.isEmpty()) {
                    Text(
                        text = "请输入手机号",
                        color = Color.Gray,
                        fontWeight = FontWeight(400),
                        fontSize = 16.sp,
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                            .padding(start = 2.dp)
                    )
                }
                innerTextField()
            }
        }
    )
}

@Composable
private fun AreaCodeDropdown(
    containerWidth: Dp,
    rules: List<PhoneNumberRule>,
    selectedRule: PhoneNumberRule,
    onRuleSelected: (PhoneNumberRule) -> Unit
) {
    val itemHeight = 48.dp
    val maxHeight = itemHeight * rules.size.coerceAtMost(4)

    Box(
        modifier = Modifier
            .width(containerWidth)
            .heightIn(max = maxHeight),
    ) {
        Box(
            Modifier
                .fillMaxWidth()
                .background(Color.White)
                .border(
                    1.dp,
                    Color.Red,
                    RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp)
                )
        ) {
            val listState = rememberLazyListState(
                initialFirstVisibleItemIndex = calculateStartIndex(
                    items = rules,
                    selected = selectedRule,
                    maxVisibleItems = 4
                )
            )

            LazyColumn(
                state = listState,
                modifier = Modifier
                    .heightIn(max = maxHeight)
                    .fillMaxWidth()
            ) {
                items(rules) { rule ->
                    DropdownItem(
                        rule = rule,
                        isSelected = rule == selectedRule,
                        onClick = { onRuleSelected(rule) },
                        itemHeight = itemHeight
                    )
                }
            }
        }
        Spacer(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth()
                .padding(start = 1.dp, end = 1.dp)
                .background(Color.White)
                .height(1.dp),
        )
    }
}

@Composable
private fun DropdownItem(
    rule: PhoneNumberRule,
    isSelected: Boolean,
    onClick: () -> Unit,
    itemHeight: Dp
) {

    val bgColor = if (isSelected) Color(0xFFF8F8F8) else Color.White
    val shape = if (isSelected) RoundedCornerShape(6.dp) else RoundedCornerShape(0.dp)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(itemHeight)
            .clickable(onClick = onClick)
            .padding(horizontal = 8.dp)
            .background(bgColor, shape),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = rule.area,
            fontSize = 16.sp,
            fontWeight = FontWeight(500),
            color = if (isSelected) MaterialTheme.colorScheme.primary else Color.Black,
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .weight(1f)
        )
        if (isSelected) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = "已选择",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
        }
    }
}

private fun calculateStartIndex(
    items: List<PhoneNumberRule>,
    selected: PhoneNumberRule,
    maxVisibleItems: Int
): Int {
    val selectedIndex = items.indexOf(selected)
    return when {
        items.size <= maxVisibleItems -> 0
        selectedIndex <= items.size - maxVisibleItems -> selectedIndex
        else -> items.size - maxVisibleItems
    }
}