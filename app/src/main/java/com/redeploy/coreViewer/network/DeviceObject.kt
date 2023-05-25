package com.redeploy.coreViewer.network

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

data class LoginRequest (
    val key: String
)

@Serializable
data class ApiResponse (
    @SerialName("msg")
    var msg: String,
)
