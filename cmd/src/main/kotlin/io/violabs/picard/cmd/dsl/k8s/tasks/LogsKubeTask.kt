package io.violabs.picard.cmd.dsl.k8s.tasks

import io.violabs.picard.cmd.KubectlConstants

//kubectl logs myapp-pod -c init-myservice
class LogsKubeTask : KubeTask() {
    var name: String? = null
    var container: String? = null
    var namespace: String? = null
    var follow: Boolean = false

    override fun cmd(): Array<String> {
        val cmdParts = mutableListOf(KubectlConstants.KUBECTL, KubectlConstants.LOGS, name!!)
        if (container != null) {
            cmdParts.add("-c")
            cmdParts.add(container!!)
        }
        if (follow) {
            cmdParts.add("-f")
        }
        return cmdParts.toTypedArray()
    }
}