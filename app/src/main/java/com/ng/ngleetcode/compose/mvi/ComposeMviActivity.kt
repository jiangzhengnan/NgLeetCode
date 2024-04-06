package com.ng.ngleetcode.compose.mvi

import android.app.Application
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ComposeMviActivity : ComponentActivity() {
  private lateinit var userViewModel: UserViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    userViewModel =
      ViewModelProvider(this, UserViewModelFactory(application)).get(UserViewModel::class.java)

    setContent {
      val userState = userViewModel.state.value

      val username = remember { mutableStateOf(TextFieldValue("")) }
      val password = remember { mutableStateOf(TextFieldValue("")) }


      Column(
        modifier = Modifier.padding(16.dp)
      ) {
        Text(text = "Welcome")

        TextField(
          value = username.value,
          onValueChange = { username.value = it }
        )

        TextField(
          value = password.value,
          onValueChange = { password.value = it },
          visualTransformation = if (password.value.text.isNotEmpty()) PasswordVisualTransformation() else VisualTransformation.None,
          keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        )

        Button(
          onClick = {
            userViewModel.onIntent(
              UserIntent.Login(
                username.value.text,
                password.value.text
              )
            )
          },
          modifier = Modifier.padding(top = 8.dp)
        ) {
          Text(text = "Login")
        }

        Button(
          onClick = { userViewModel.onIntent(UserIntent.Logout) },
          modifier = Modifier.padding(top = 8.dp)
        ) {
          Text(text = "Logout")
        }

        if (userState.success) {
          Text(text = "登录成功")
        } else {
          Text(text = "未登录:" + userState.error)
        }
      }
    }
  }

}

class UserViewModelFactory(private val application: Application) :
  ViewModelProvider.AndroidViewModelFactory(application) {
  override fun <T : ViewModel> create(modelClass: Class<T>): T {
    //val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(application)
    val userModel = UserModel()
    return UserViewModel(userModel) as T
  }
}