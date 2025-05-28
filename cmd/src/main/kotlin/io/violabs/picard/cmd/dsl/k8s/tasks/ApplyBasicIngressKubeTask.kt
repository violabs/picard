package io.violabs.picard.cmd.dsl.k8s.tasks

const val INGRESS_DEFAULT_FILE_LOCATION =
    "https://raw.githubusercontent.com/kubernetes/ingress-nginx/controller-v1.8.2/deploy/static/provider/cloud/deploy.yaml"

class ApplyBasicIngressKubeTask : KubeTask() {
    var fileLocation: String = INGRESS_DEFAULT_FILE_LOCATION

    override fun cmd(): Array<String> {
        return arrayOf(
            "kubectl",
            "apply",
            "-f", fileLocation
        )
    }
}