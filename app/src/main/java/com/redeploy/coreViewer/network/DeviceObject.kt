package com.redeploy.coreViewer.network

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

data class LoginRequest (
    val url: String,
    val key: String
)

@Serializable
data class ApiResponse (
    @SerialName("type")
    var type: String,

    @SerialName("msg")
    var msg: String,
)
