package com.daniil.core.data.tools

abstract class Result< out T :Any?> {

    data class Success<out T:Any?>(val data: T) : Result<T>()

    data class Error(val exception: Exception) : Result<Nothing>()
}