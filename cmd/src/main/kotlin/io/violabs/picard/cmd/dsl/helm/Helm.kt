package io.violabs.picard.cmd.dsl.helm

import io.violabs.picard.cmd.dsl.CommandLineProcessor
import io.violabs.picard.cmd.dsl.helm.tasks.AddRepoTask
import io.violabs.picard.cmd.dsl.helm.tasks.HelmTask

/**
 * Available commands:
 * * [Helm.addRepo]
 */
fun helm(scope: Helm.() -> Unit) {
    Helm().apply(scope)
}

class Helm : CommandLineProcessor<HelmTask>(HelmTask::class) {
    fun updateRepo() = processTask {
        object : HelmTask() {
            override fun cmd(): Array<String> = arrayOf("helm", "repo", "update")
        }
    }

    fun addRepo(block: AddRepoTask.() -> Unit) = processTask(AddRepoTask(), block)

    fun addRepos(vararg processor: (Helm) -> Unit) = processor.forEach { it(this) }
}