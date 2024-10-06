package com.ng.ngleetcode.ui.page.code

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ScaffoldState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@Composable
fun CodePage(
  navCtrl: NavHostController,
  scaffoldState: ScaffoldState,
) {
  Box(modifier = Modifier.fillMaxSize()) {
    Text(text = "Code Screen", modifier = Modifier.align(Alignment.Center))
  }
}