package io.violabs.picard.starCharts.loki

import io.violabs.picard.starCharts.StarChartsYamlBuilder
import io.violabs.picard.starCharts.loki.utils.LokiDsl

@LokiDsl
class LokiRunner(private val yamlBuilder: StarChartsYamlBuilder) {
    private val resources: MutableList<Loki> = mutableListOf()
    var fileLocation: String? = null

    fun addResource(resource: Loki) {
        resources.add(resource)
    }

    fun addResource(block: LokiDslBuilder.() -> Unit) {
        val builder = LokiDslBuilder()
        builder.block()
        resources.add(builder.build())
    }

    fun buildYaml(): String {
        return yamlBuilder.build(resources)
    }
}