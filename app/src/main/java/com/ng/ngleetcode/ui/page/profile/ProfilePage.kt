package com.ng.ngleetcode.ui.page.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ScaffoldState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@Composable
fun ProfilePage(
  navCtrl: NavHostController,
  scaffoldState: ScaffoldState,
) {
  Box(modifier = Modifier.fillMaxSize()) {
    Text(text = "My Screen", modifier = Modifier.align(Alignment.Center))
  }
}