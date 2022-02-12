package com.fexle.upennys

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Build
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fexle.upennys.data.AppData
import com.fexle.upennys.data.source.RemoteDataSource
import com.fexle.upennys.data.source.remote.NetworkConnectionInterceptor
import com.fexle.upennys.utils.SingleLiveEvent
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response


abstract class BaseViewModel (var baseApplication: Application, var remoteDataSource: RemoteDataSource): ViewModel() {
    val showToast = MutableLiveData<String>()
    val showSnackbar = MutableLiveData<String>()
    val showProgress = MutableLiveData<Boolean>()
    val sessionExpired = SingleLiveEvent<Void>()
    lateinit var appData : AppData

    abstract fun create()
    abstract fun start()
    abstract fun stop()
    abstract fun destroy()

    fun isInternetConnected(): Boolean {
        var result = false
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val connectivityManager = baseApplication.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
            connectivityManager?.let {
                it.getNetworkCapabilities(connectivityManager.activeNetwork)?.apply {
                    result = when {
                        hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                        else -> false
                    }
                }
            }
        }
        else{
            val cm = baseApplication.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
            result = activeNetwork?.isConnectedOrConnecting == true
        }

        return result
    }

    suspend fun <T : Any> apiRequest(call: suspend () -> Response<T>): T? {
        val response = call.invoke()
        if (response.isSuccessful) {
            return response.body()
        } else {
            val message = StringBuilder()
            val error = response.errorBody()?.string()
            error?.let {
                try {
                    val errorMsg: String? = JSONObject(it).optString("message")
                    if (!errorMsg.isNullOrEmpty()) {
                        message.append(errorMsg)
                    } else {
                        message.append("Session Expired.")
                    }
                    if (response.code() == 401 || response.code() == 403) {
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }
            throw NetworkConnectionInterceptor.ApiException(message.toString())
        }
    }
}