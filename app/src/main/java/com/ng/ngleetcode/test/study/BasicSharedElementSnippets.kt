//@file:OptIn(ExperimentalSharedTransitionApi::class, ExperimentalSharedTransitionApi::class)
//

// //demo效果不错的

//package com.ng.ngleetcode.study
//
//
//import androidx.compose.animation.*
//import androidx.compose.animation.SharedTransitionScope.ResizeMode.Companion.RemeasureToBounds
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.border
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.derivedStateOf
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.layout.onSizeChanged
//import androidx.compose.ui.platform.LocalDensity
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.res.stringResource
//import androidx.compose.ui.text.style.TextOverflow
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.*
//import com.ng.ngleetcode.R
//import com.ng.ngleetcode.app.theme.LavenderLight
//import com.ng.ngleetcode.app.theme.RoseLight
//
//
//@OptIn(ExperimentalSharedTransitionApi::class)
//@Preview(showBackground = true)
//@Composable
//fun BasicSharedElementDemo() {
//  var showDetails by remember { mutableStateOf(false) }
//  SharedTransitionLayout {
//    AnimatedContent(targetState = showDetails) { inDetails ->
//      if (inDetails) {
//        DetailsScreen(
//          onBack = { showDetails = false },
//          animatedVisibilityScope = this@AnimatedContent
//        )
//      } else {
//        MainScreen(
//          onShowDetails = { showDetails = true },
//          animatedVisibilityScope = this@AnimatedContent
//        )
//      }
//    }
//  }
//}
//
//
//@Composable
//private fun SharedTransitionScope.MainScreen(
//  animatedVisibilityScope: AnimatedVisibilityScope,
//  onShowDetails: () -> Unit,
//  modifier: Modifier = Modifier,
//) {
//  Box(
//    modifier = Modifier
//      .fillMaxSize()
//      .systemBarsPadding()
//  ) {
//    Row(
//      modifier = modifier
//        .padding(16.dp)
//        .sharedBounds(
//          sharedContentState = rememberSharedContentState(key = "bounds"),
//          animatedVisibilityScope = animatedVisibilityScope,
//          resizeMode = RemeasureToBounds
//        )
//        .border(
//          width = 1.dp,
//          color = Color.Gray.copy(alpha = 0.5f),
//          shape = RoundedCornerShape(8.dp)
//        )
//        .background(color = LavenderLight, shape = RoundedCornerShape(8.dp))
//        .clickable { onShowDetails() }
//        .padding(8.dp)
//    ) {
//      Image(
//        painter = painterResource(id = R.mipmap.pumpkin_icon),
//        contentDescription = null,
//        modifier = Modifier
//          .sharedElement(
//            state = rememberSharedContentState(key = "image"),
//            animatedVisibilityScope = animatedVisibilityScope
//          )
//          .size(100.dp)
//      )
//      Text(
//        text = "bqliang", fontSize = 21.sp,
//        modifier = Modifier
//          .sharedBounds(
//            sharedContentState = rememberSharedContentState(key = "title"),
//            animatedVisibilityScope = animatedVisibilityScope
//          )
//      )
//    }
//  }
//}
//
//
//@Composable
//private fun SharedTransitionScope.DetailsScreen(
//  animatedVisibilityScope: AnimatedVisibilityScope,
//  onBack: () -> Unit,
//  modifier: Modifier = Modifier,
//) {
//  Box(
//    modifier = Modifier
//      .fillMaxSize()
//      .systemBarsPadding(),
//    contentAlignment = Alignment.Center
//  ) {
//    Column(
//      modifier = modifier.run {
//        padding(16.dp)
//          .sharedBounds(
//            sharedContentState = rememberSharedContentState(key = "bounds"),
//            animatedVisibilityScope = animatedVisibilityScope,
//            resizeMode = RemeasureToBounds
//          )
//          .border(
//            width = 1.dp,
//            color = Color.Gray.copy(alpha = 0.5f),
//            shape = RoundedCornerShape(8.dp)
//          )
//          .background(color = RoseLight, shape = RoundedCornerShape(8.dp))
//          .clickable {
//            onBack()
//          }
//          .padding(8.dp)
//      }
//    ) {
//      Image(
//        painter = painterResource(id = R.mipmap.pumpkin_icon),
//        contentDescription = null,
//        modifier = Modifier
//          .sharedElement(
//            state = rememberSharedContentState(key = "image"),
//            animatedVisibilityScope = animatedVisibilityScope
//          )
//          .size(200.dp)
//      )
//      Text(
//        text = "bqliang", fontSize = 28.sp,
//        modifier = Modifier
//          .sharedBounds(
//            sharedContentState = rememberSharedContentState(key = "title"),
//            animatedVisibilityScope = animatedVisibilityScope
//          )
//      )
//      Text(
//        text = stringResource(R.string.lorem_ipsum),
//        maxLines = 7,
//        overflow = TextOverflow.Ellipsis
//      )
//    }
//  }
//}
//
//
//@Preview(showBackground = true)
//@Composable
//private fun ImageInTransition() {
//  Column {
//    Image(
//      painter = painterResource(id = R.mipmap.pumpkin_icon),
//      contentDescription = null,
//      modifier = Modifier
//        .size(200.dp)
//        .border(width = 2.dp, color = Color.Yellow)
//        .requiredSize(100.dp)
//    )
//
//    Image(
//      painter = painterResource(id = R.mipmap.pumpkin_icon),
//      contentDescription = null,
//      modifier = Modifier
//        .size(200.dp)
//        .border(width = 2.dp, color = Color.Green)
//        .requiredSize(150.dp)
//    )
//
//    Image(
//      painter = painterResource(id = R.mipmap.pumpkin_icon),
//      contentDescription = null,
//      modifier = Modifier
//        .size(200.dp)
//        .border(width = 2.dp, color = Color.Red)
//        .requiredSize(200.dp)
//    )
//  }
//}
//
//
//@Preview(showBackground = true)
//@Composable
//private fun TextInTransition() {
//  val localDensity: Density = LocalDensity.current
//  var startPxSize by remember { mutableStateOf(IntSize.Zero) }
//  var endPxSize by remember { mutableStateOf(IntSize.Zero) }
//  val destinationDpSize: DpSize by remember {
//    derivedStateOf {
//      with(localDensity) {
//        val width = ((endPxSize.width - startPxSize.width) / 2 + startPxSize.width).toDp()
//        val height =
//          ((endPxSize.height - startPxSize.height) / 2 + startPxSize.height).toDp()
//        DpSize(width, height)
//      }
//    }
//  }
//
//  Column {
//    Text(
//      text = "bqliang", fontSize = 21.sp,
//      modifier = Modifier
//        .background(Color.Yellow.copy(alpha = 0.5f))
//        .onSizeChanged { startPxSize = it }
//    )
//
//    Text(
//      text = "bqliang", fontSize = 28.sp,
//      modifier = Modifier
//        .background(Color.Green.copy(alpha = 0.5f))
//        .requiredSize(destinationDpSize)
//    )
//
//    Text(
//      text = "bqliang", fontSize = 28.sp,
//      modifier = Modifier
//        .background(Color.Red.copy(alpha = 0.5f))
//        .onSizeChanged { endPxSize = it }
//    )
//  }
//}