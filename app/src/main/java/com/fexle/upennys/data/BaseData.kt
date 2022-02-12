package com.fexle.upennys.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

open class BaseData: Serializable {
    @SerializedName("id")
    @Expose
    var id: String = ""

    @SerializedName("_id")
    @Expose
    var _id: String = ""

    @SerializedName("is_active")
    @Expose
    var isActive: Int = 0

    @SerializedName("is_deleted")
    @Expose
    var isDeleted: Int = 0

    @SerializedName("createdAt")
    @Expose
    var createdAt: String = ""

    @SerializedName("updatedAt")
    @Expose
    var updatedAt: String = ""
}