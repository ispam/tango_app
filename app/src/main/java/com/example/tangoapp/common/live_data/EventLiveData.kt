package com.example.tangoapp.common.live_data

import androidx.lifecycle.LiveData

open class EventLiveData<T> : LiveData<Event<T>>() {
    protected open fun setData(data: T) {
        value = Event(data)
    }
}

open class MutableEventLiveData<T> : EventLiveData<T>() {
    public override fun setData(data: T) {
        super.setData(data)
    }
}