package com.ng.ngleetcode.compose.mvi

sealed class UserIntent {
  data class Login(val username: String, val password: String) : UserIntent()
  object Logout: UserIntent()
}
