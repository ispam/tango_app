package com.example.tangoapp.common.live_data

import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner

fun <T: Any?, L: TaskLiveData<T>> LifecycleOwner.observeTask(liveData: L, taskObserver: TaskObserver<T>) =
    liveData.observe(if (this is Fragment) viewLifecycleOwner else this, taskObserver)