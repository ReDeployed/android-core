package com.redeploy.coreViewer.network

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

data class LoginRequest (
    val url: String,
    val key: String
) {
    val token = ""
}

data class AddRequest (
    val ip: String,
)

@Serializable
data class LoginResponse (
    var type: String,
    var msg: String,
)

@Serializable
data class GenericResponse(
    var type: String,
    var msg: String
)

@Serializable
data class ListResponse(
    var type: String,
    var msg: List<Message>
)

data class Message(
    val diaCPU: DiaCPU,
    val diaMEM: DiaMEM,
    val id: String,
    val interfaces: Interfaces,
    val ip: String,
    val updated_at: String,
    val version: Version
)

data class DiaCPU(
    val from: Int,
    val objects: List<CPUObject>,
    val to: Int,
    val total: Int
)

data class CPUObject(
    val idle: Int,
    val interrupt: Int,
    val `io-wait`: Int,
    val system: Int,
    val user: Int
)

data class DiaMEM(
    val from: Int,
    val objects: List<MemoryObject>,
    val to: Int,
    val total: Int
)

data class MemoryObject(
    val free: String,
    val total: String,
    val type: String,
    val used: String
)

data class Interfaces(
    val objects: List<Interface>
)

data class Interface(
    val comments: String,
    val enabled: Boolean,
    val `ipv4-address`: String,
    val `ipv4-mask-length`: String,
    val `ipv6-address`: String,
    val `ipv6-autoconfig`: String,
    val `ipv6-local-link-address`: String,
    val `ipv6-mask-length`: String,
    val name: String,
    val type: String
)

data class Version(
    val `os-build`: String,
    val `os-edition`: String,
    val `os-kernel-version`: String,
    val `product-version`: String
)
