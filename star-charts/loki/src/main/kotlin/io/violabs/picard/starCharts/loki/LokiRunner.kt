package io.violabs.picard.starCharts.loki

import io.violabs.picard.starCharts.StarChartsYamlBuilder
import io.violabs.picard.starCharts.loki.utils.LokiDsl

@LokiDsl
class LokiRunner(private val yamlBuilder: StarChartsYamlBuilder) {
    private val resources: MutableList<LokiDeployment> = mutableListOf()
    var fileLocation: String? = null

    fun addResource(resource: LokiDeployment) {
        resources.add(resource)
    }

    fun addResource(block: LokiDeploymentDslBuilder.() -> Unit) {
        val builder = LokiDeploymentDslBuilder()
        builder.block()
        resources.add(builder.build())
    }

    fun buildYaml(): String {
        return yamlBuilder.build(resources)
    }
}