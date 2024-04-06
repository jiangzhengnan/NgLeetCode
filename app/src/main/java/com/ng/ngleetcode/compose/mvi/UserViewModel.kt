package com.ng.ngleetcode.compose.mvi

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class UserViewModel(private val userModel: UserModel): ViewModel() {
  private val _state = mutableStateOf(UserViewState())
  val state: State<UserViewState> = _state

  fun onIntent(intent: UserIntent) {
    when(intent) {
      is UserIntent.Login -> {
        val isSuccessful = userModel.login(intent.username, intent.password)
        if (isSuccessful) {
          _state.value = UserViewState(success = true)
        } else {
          _state.value = UserViewState(error = "Invalid username or password")
        }
      }
      is UserIntent.Logout -> {
        userModel.logout()
        _state.value = UserViewState(success = true)
      }
    }
  }
}

data class UserViewState(val success: Boolean = false, val error: String = "")