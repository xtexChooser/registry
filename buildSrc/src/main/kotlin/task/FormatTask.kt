package task

import kotlinx.serialization.KSerializer
import net.mamoe.yamlkt.Yaml
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.InputFiles
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.TaskAction
import java.io.File
import javax.inject.Inject

open class FormatTask<T> @Inject constructor(
    @get:InputFiles val path: File,
    @get:Internal val serializer: KSerializer<T>
) : DefaultTask() {

    init {
        group = "format"
        project.tasks.maybeCreate("format").dependsOn(this)
    }

    @TaskAction
    fun format() {
        path.listFiles()!!.forEach { file ->
            file.writeText(Yaml.encodeToString(serializer, Yaml.decodeFromString(serializer, file.readText())))
        }
    }

}