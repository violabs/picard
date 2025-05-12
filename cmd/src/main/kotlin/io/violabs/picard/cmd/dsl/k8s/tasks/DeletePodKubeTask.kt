package io.violabs.picard.cmd.dsl.k8s.tasks

import io.violabs.picard.cmd.KubectlConstants

//kubectl delete deployment <deployment-name> -n <namespace>
class DeletePodKubeTask : KubeTask() {
    var name: String? = null

    override fun cmd(): Array<String> {
        return arrayOf(KubectlConstants.KUBECTL, KubectlConstants.DELETE, KubectlConstants.POD, name!!)
    }
}