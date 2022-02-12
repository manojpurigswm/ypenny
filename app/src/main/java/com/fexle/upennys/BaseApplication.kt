package com.fexle.upennys

import android.app.Application
import androidx.multidex.MultiDexApplication
import com.fexle.upennys.data.source.RemoteDataRepository
import com.fexle.upennys.data.source.RemoteDataSource


class BaseApplication : MultiDexApplication(){

    val remoteDataSource: RemoteDataSource
        get() = RemoteDataRepository(this).getRemoteDataSource()

    val getApplication: Application
        get() = this

    override fun onCreate() {
        super.onCreate()
    }
}