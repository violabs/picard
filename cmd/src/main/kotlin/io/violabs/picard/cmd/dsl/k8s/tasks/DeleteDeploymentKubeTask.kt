package io.violabs.picard.cmd.dsl.k8s.tasks

import io.violabs.picard.cmd.KubectlConstants

class DeleteDeploymentKubeTask : KubeTask() {
    var name: String? = null
    var namespace: String? = null

    override fun cmd(): Array<String> {
        val cmd = mutableListOf(KubectlConstants.KUBECTL, KubectlConstants.DELETE, KubectlConstants.DEPLOYMENT, name!!)
        if (namespace != null) {
            cmd.add("-n")
            cmd.add(namespace!!)
        }
        return cmd.toTypedArray()
    }
}