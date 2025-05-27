package io.violabs.picard.starCharts.loki

import io.violabs.picard.starCharts.StarChartsYamlBuilder
import io.violabs.picard.starCharts.loki.utils.LokiDsl

@LokiDsl
class LokiRunner(private val yamlBuilder: StarChartsYamlBuilder) {
    private val resources: MutableList<LokiConfig> = mutableListOf()
    var fileLocation: String? = null

    fun addResource(resource: LokiConfig) {
        resources.add(resource)
    }

    fun addResource(block: LokiConfigDslBuilder.() -> Unit) {
        val builder = LokiConfigDslBuilder()
        builder.block()
        resources.add(builder.build())
    }

    fun buildYaml(): String {
        return yamlBuilder.build(resources)
    }
}