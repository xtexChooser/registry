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
        target.tasks.create<CheckInetnumRangeTask>(
            "checkInetnumRange", target.file("inetnum"),
            setOf(IPAddressString("172.20.206.64/26").sequentialRange)
        )
        target.tasks.create<CheckInetnumRangeTask>(
            "checkInet6numRange", target.file("inet6num"),
            setOf(IPAddressString("fd10:78d4:da31::/48").sequentialRange)
        )
        target.tasks.create<FormatTask<DN42Inetnum>>(
            "formatInetnum",
            target.file("inetnum"),
            DN42Inetnum.serializer()
        )
        target.tasks.create<FormatTask<DN42Inetnum>>(
            "formatInet6num",
            target.file("inet6num"),
            DN42Inetnum.serializer()
        )
    }

}