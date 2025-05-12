package io.violabs.picard.cmd.dsl.k8s

import io.violabs.picard.cmd.dsl.CommandLineProcessor
import io.violabs.picard.cmd.dsl.k8s.tasks.*

/**
 * Available commands:
 * * [Kubectl.applyPod]
 * * [Kubectl.describePod]
 * * [Kubectl.deletePod]
 * * [Kubectl.deleteDeployment]
 * * [Kubectl.getPods]
 * * [Kubectl.logs]
 */
fun kubectl(scope: Kubectl.() -> Unit) {
    Kubectl().apply(scope)
}

class Kubectl : CommandLineProcessor<KubeTask>(KubeTask::class) {
    fun applyPod(scope: ApplyPodKubeTask.() -> Unit) = processTask(ApplyPodKubeTask(), scope)
    fun describePod(scope: DescribePodKubeTask.() -> Unit) = processTask(DescribePodKubeTask(), scope)
    fun deletePod(scope: DeletePodKubeTask.() -> Unit) = processTask(DeletePodKubeTask(), scope)
    fun deleteDeployment(scope: DeleteDeploymentKubeTask.() -> Unit) = processTask(DeleteDeploymentKubeTask(), scope)
    fun getPods(scope: GetPodsKubeTask.() -> Unit) = processTask(GetPodsKubeTask(), scope)
    fun logs(scope: LogsKubeTask.() -> Unit) = processTask(LogsKubeTask(), scope)
}
