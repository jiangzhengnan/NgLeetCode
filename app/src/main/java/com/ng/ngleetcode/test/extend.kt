package com.ng.ngleetcode.test

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.ui.Modifier

fun Modifier.clickableX(onClick: () -> Unit): Modifier {
    return this.clickable(
        indication = null,
        interactionSource = MutableInteractionSource()
    ) {
        onClick.invoke()
    }
}