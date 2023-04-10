package com.ng.base.exception

interface IBaseResponse<T> {
    fun code(): Int
    fun msg(): String?
    fun data(): T?
    fun suc(): Boolean
}