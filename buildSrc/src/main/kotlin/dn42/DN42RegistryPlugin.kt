package dn42

import dn42.model.DN42Inetnum
import inet.ipaddr.IPAddressString
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.create
import task.CheckInetnumRangeTask
import task.FormatTask

class DN42RegistryPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        target.run {
            val ranges = setOf(
                IPAddressString("172.20.206.64/26").sequentialRange,
                IPAddressString("fd10:78d4:da31::/48").sequentialRange,
            )
            tasks.create<CheckInetnumRangeTask>(
                "checkInetnumRange",
                file("inetnum"),
                ranges.filter { it.lower.isIPv4 }
                )
            tasks.create<CheckInetnumRangeTask>(
                "checkInet6numRange",
                file("inet6num"),
                ranges.filter { it.lower.isIPv6 }
            )
            tasks.create<FormatTask<DN42Inetnum>>(
                "formatInetnum",
                file("inetnum"),
                DN42Inetnum.serializer()
            )
            tasks.create<FormatTask<DN42Inetnum>>(
                "formatInet6num",
                file("inet6num"),
                DN42Inetnum.serializer()
            )
        }
    }

}