package io.violabs.picard.cmd.dsl.k8s.tasks

import io.violabs.picard.cmd.KubectlConstants

class ApplyPodKubeTask : KubeTask() {
    var fileLocation: String? = null

    override fun cmd(): Array<String> {
        val loc = requireNotNull(fileLocation) { "file location must be provided" }
        return arrayOf(KubectlConstants.KUBECTL, KubectlConstants.APPLY, KubectlConstants.FILE_FLAG, loc)
    }
}