package com.ng.base.event

class Message @JvmOverloads constructor(
    var code: Int = 0,
    var msg: Any ,
    var arg1: Int = 0,
    var arg2: Int = 0,
    var obj: Any? = null
)