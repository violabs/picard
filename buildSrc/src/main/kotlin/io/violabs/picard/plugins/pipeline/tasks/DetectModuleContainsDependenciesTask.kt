package io.violabs.picard.plugins.pipeline.tasks

import io.violabs.picard.plugins.pipeline.hasLibrary
import org.gradle.api.DefaultTask
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Optional
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.options.Option

abstract class DetectModuleContainsDependenciesTask : DefaultTask() {
    @get:Option(
        option = "pattern",
        description = "a pattern to check for in the module dependencies",
    )
    @get:Input
    @get:Optional
    abstract val pattern: Property<String>

    @get:Option(
        option = "depGroup",
        description = "The group of the dependency to check for in the module"
    )
    @get:Input
    @get:Optional
    abstract val depGroup: Property<String>

    @get:Option(
        option = "artifactId",
        description = "The artifactId of the dependency to check for in the module"
    )
    @get:Input
    @get:Optional
    abstract val artifactId: Property<String>


    @TaskAction
    fun detectDependencies() {
        println("Detecting changed modules...")

        val check = if (pattern.isPresent)
            project.hasLibrary(pattern.get())
        else
            checkIdentifiers()

        println("HAS_DEPENDENCY=$check")
    }

    private fun checkIdentifiers(): Boolean {
        if (depGroup.isPresent && !artifactId.isPresent) return project.hasLibrary(group = depGroup.get())
        if (artifactId.isPresent && !depGroup.isPresent) return project.hasLibrary(name = artifactId.get())
        if (depGroup.isPresent && artifactId.isPresent) return project.hasLibrary(depGroup.get(), artifactId.get())
        return false
    }
}