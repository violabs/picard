package io.violabs.picard.cmd.dsl.helm.tasks

class UpgradeTask : HelmTask() {
    var optionKey: String? = null
    var optionValue: String? = null
    var releaseName: String? = null
    var chartReference: String? = null

    override fun cmd(): Array<String> {
        val releaseName = requireNotNull(releaseName) { "release name must be provided" }
        val chartReference = requireNotNull(chartReference) { "chart reference must be provided" }

        return sequenceOf("helm", "upgrade", optionKey, optionValue, releaseName, chartReference)
            .filterNotNull()
            .toList()
            .toTypedArray()
    }
}