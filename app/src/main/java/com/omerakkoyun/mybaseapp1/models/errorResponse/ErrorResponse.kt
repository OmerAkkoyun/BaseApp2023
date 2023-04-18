package com.omerakkoyun.mybaseapp1.models.errorResponse

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("status")
    val status: Status?
)
