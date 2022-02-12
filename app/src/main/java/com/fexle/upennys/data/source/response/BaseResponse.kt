package com.fexle.upennys.data.source.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

open class BaseResponse<T>{

    @SerializedName("status")
    @Expose
    var status: String = ""

    @SerializedName("message")
    @Expose
    var message: String = ""

    @SerializedName("validation_error")
    @Expose
    var validationError: Int = 0

    @SerializedName("error")
    @Expose
    var error: Any? = null

    @SerializedName(value="data", alternate=["user", "requests"])
    @Expose
    var data: T? = null
}