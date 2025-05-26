package io.violabs.picard.plugins.pipeline

import io.violabs.picard.plugins.pipeline.tasks.DetectChangeModulesTask
import io.violabs.picard.plugins.pipeline.tasks.DetectModuleContainsDependenciesTask
import io.violabs.picard.plugins.pipeline.tasks.PrintModulesTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.register

class PipelinePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        val extension = target.extensions.create("pipeline", PipelineExtension::class.java)

        target.tasks.register<PrintModulesTask>("printModules") {
            group = "pipeline"
            hasSrc = extension.hasSrc
        }

        target.tasks.register<DetectChangeModulesTask>("detectChangedModules") {
            group = "pipeline"
        }

        target.tasks.register<DetectModuleContainsDependenciesTask>("detectModuleContainsDependencies") {
            group = "pipeline"
            pattern.set(extension.dependencyLookUps()?.pattern ?: project.findProperty("pattern") as? String)
            depGroup.set(extension.dependencyLookUps()?.group ?: project.findProperty("depGroup") as? String)
            artifactId.set(extension.dependencyLookUps()?.name ?: project.findProperty("artifactId") as? String)
        }
    }
}