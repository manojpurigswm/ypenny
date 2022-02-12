package com.fexle.upennys.ui.onboard

import android.app.Application
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.liveData
import com.fexle.upennys.BaseViewModel
import com.fexle.upennys.data.source.RemoteDataSource
import com.fexle.upennys.utils.Resource
import com.fexle.upennys.utils.SingleLiveEvent
import kotlinx.coroutines.Dispatchers

class OnboardViewModel(baseApplication: Application, remoteDataSource: RemoteDataSource): BaseViewModel(baseApplication, remoteDataSource) {


    fun userLogin(request: LinkedHashMap<String, String>) = liveData(Dispatchers.Main) {
        showProgress.value = true
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = apiRequest { remoteDataSource.loginUser(request) }))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    override fun create() {
    }

    override fun start() {
    }

    override fun stop() {
    }

    override fun destroy() {
    }

}