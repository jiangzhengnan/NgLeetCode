package com.ng.ngleetcode.old.composedemo.mvi

sealed class UserIntent {
  data class Login(val username: String, val password: String) : UserIntent()
  object Logout: UserIntent()
}
