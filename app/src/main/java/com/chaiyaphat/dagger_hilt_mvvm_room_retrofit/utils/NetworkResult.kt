package com.chaiyaphat.dagger_hilt_mvvm_room_retrofit.utils

//คล้าย enum แต่ return เป็น  class
sealed class NetworkResult<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : NetworkResult<T>(data)
    class Error<T>(message: String?, data: T? = null) : NetworkResult<T>(data, message)
    class Loading<T> : NetworkResult<T>()

}