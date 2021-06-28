package com.example.tangoapp.common.live_data

open class TaskLiveData<T> : EventLiveData<Resource<T>>() {
    protected open fun setLoading() {
        setData(Resource.Loading)
    }

    protected open fun setSuccess(data: T) {
        setData(Resource.Success(data))
    }

    protected open fun setError(throwable: Throwable?) {
        setData(Resource.Error(throwable))
    }
}

open class MutableTaskLiveData<T> : TaskLiveData<T>() {
    public override fun setLoading() {
        super.setLoading()
    }

    public override fun setSuccess(data: T) {
        super.setSuccess(data)
    }

    public override fun setError(throwable: Throwable?) {
        super.setError(throwable)
    }
}