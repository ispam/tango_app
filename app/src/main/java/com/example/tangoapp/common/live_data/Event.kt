package com.example.tangoapp.common.live_data

data class Event<out T>(private val data: T) {
    private var hasBeenConsumed = false

    fun consume(): T? =
        if (hasBeenConsumed) {
            null
        } else {
            hasBeenConsumed = true
            data
        }
}
