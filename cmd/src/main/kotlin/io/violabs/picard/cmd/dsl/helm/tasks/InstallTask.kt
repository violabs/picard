package io.violabs.picard.cmd.dsl.helm.tasks

class InstallTask : HelmTask() {
    var optionKey: String? = null
    var optionValue: String? = null
    var releaseName: String? = null
    var chartReference: String? = null

    override fun cmd(): Array<String> {
        val releaseName = requireNotNull(releaseName) { "release name must be provided" }
        val chartReference = requireNotNull(chartReference) { "chart reference must be provided" }

        return sequenceOf("helm", "install", optionKey, optionValue, releaseName, chartReference)
            .filterNotNull()
            .toList()
            .toTypedArray()
    }
}