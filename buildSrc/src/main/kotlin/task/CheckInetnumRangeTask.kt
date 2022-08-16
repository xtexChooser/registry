package task

import inet.ipaddr.IPAddressSeqRange
import inet.ipaddr.IPAddressString
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.InputFiles
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.TaskAction
import util.unwrapInetnumRange
import java.io.File
import javax.inject.Inject

open class CheckInetnumRangeTask @Inject constructor(
    @get:InputFiles val path: File,
    @get:Internal val ranges: Set<IPAddressSeqRange>
) : DefaultTask() {

    init {
        group = "check"
        project.tasks.maybeCreate("check").dependsOn(this)
    }

    @TaskAction
    fun check() {
        path.listFiles()!!.forEach { file ->
            val inetnum = file.name.unwrapInetnumRange()
            val range = IPAddressString(inetnum).sequentialRange
            if (range == null)
                error("inetnum ${file.name} is not a valid address range")
            if (ranges.all { range.subtract(it).isNotEmpty() }) {
                error("inetnum ${file.name} is in any valid range")
            } else {
                logger.lifecycle("inetnum ${file.name} is valid")
            }
        }
    }

}