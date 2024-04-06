package com.ng.ngleetcode.compose.mvi

class UserModel() {

  fun login(username: String, password: String): Boolean {
    return "123" == username && "456" == password
  }

  fun logout() {
    //清空本地账号密码
  }
}