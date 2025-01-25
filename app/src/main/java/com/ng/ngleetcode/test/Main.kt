package com.ng.ngleetcode.test

fun main() {
	val result: Result<Boolean>
	result = runCatching {
		true
	}.onFailure {
	}
	if (result.isSuccess && result.getOrNull() == true) {
		println("resuklt!")
	}
	println("resuklt:" + result)
}