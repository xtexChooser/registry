package dn42.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DN42Inetnum(
    val desc: String,
    @SerialName("mnt-by") val mntBy: String,
    val server: String? = null,
    val hostname: String? = null,
)
