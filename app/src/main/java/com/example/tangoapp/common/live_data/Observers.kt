package com.example.tangoapp.common.live_data

import androidx.lifecycle.Observer

open class EventObserver<T>(private val onEventChanged: (T) -> Unit) : Observer<Event<T>> {
    override fun onChanged(event: Event<T>?) {
        event?.consume()?.let { onEventChanged(it) }
    }
}

open class TaskObserver<T>(
    private val onLoading: () -> Unit = {},
    private val onSuccess: (T?) -> Unit,
    private val onError: (exception: Throwable?) -> Unit = {}
) : EventObserver<Resource<T>>(
    {
        when (it) {
            is Resource.Loading -> onLoading()
            is Resource.Success -> onSuccess(it.data)
            is Resource.Error -> onError(it.throwable)
        }
    }
)