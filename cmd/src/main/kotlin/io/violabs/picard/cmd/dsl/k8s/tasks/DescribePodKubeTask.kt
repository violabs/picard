package io.violabs.picard.cmd.dsl.k8s.tasks

import io.violabs.picard.cmd.KubectlConstants

class DescribePodKubeTask : KubeTask() {
    var fileLocation: String? = null
    var namespace: String? = null

    override fun cmd(): Array<String> {
        val loc = requireNotNull(fileLocation) { "file location must be provided" }
        val cmd = mutableListOf(KubectlConstants.KUBECTL, KubectlConstants.DESCRIBE, KubectlConstants.FILE_FLAG, loc)

        if (namespace != null) {
            cmd.add("-n")
            cmd.add(namespace!!)
        }

        return cmd.toTypedArray()
    }
}