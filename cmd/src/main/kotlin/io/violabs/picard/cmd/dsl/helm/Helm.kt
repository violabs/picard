package io.violabs.picard.cmd.dsl.helm

import io.violabs.picard.cmd.dsl.CommandLineProcessor
import io.violabs.picard.cmd.dsl.helm.tasks.AddRepoTask
import io.violabs.picard.cmd.dsl.helm.tasks.HelmTask
import io.violabs.picard.cmd.dsl.helm.tasks.InstallTask
import io.violabs.picard.cmd.dsl.helm.tasks.UpgradeTask

/**
 * Available commands:
 * - [Helm.addRepo]
 * - [Helm.addRepos]
 * - [Helm.install]
 * - [Helm.updateRepo]
 * - [Helm.upgrade]
 */
fun helm(scope: Helm.() -> Unit) {
    Helm().apply(scope)
}

class Helm : CommandLineProcessor<HelmTask>(HelmTask::class) {
    fun addRepo(block: AddRepoTask.() -> Unit) = processTask(AddRepoTask(), block)
    fun addRepos(vararg processor: (Helm) -> Unit) = processor.forEach { it(this) }

    fun install(block: InstallTask.() -> Unit) = processTask(InstallTask(), block)
    fun upgrade(block: UpgradeTask.() -> Unit) = processTask(UpgradeTask(), block)

    fun updateRepo() = processTask {
        object : HelmTask() {
            override fun cmd(): Array<String> = arrayOf("helm", "repo", "update")
        }
    }
}