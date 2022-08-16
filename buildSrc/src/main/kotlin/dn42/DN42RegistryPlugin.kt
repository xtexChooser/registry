package dn42

import dn42.model.DN42Inetnum
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.create
import task.FormatTask

class DN42RegistryPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        target.tasks.create<FormatTask<DN42Inetnum>>("formatInetnum", target.file("inetnum"), DN42Inetnum.serializer())
        target.tasks.create<FormatTask<DN42Inetnum>>("formatInet6num", target.file("inet6num"), DN42Inetnum.serializer())
    }

}