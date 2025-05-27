package io.violabs.picard.starCharts

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.JsonMappingException
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.registerKotlinModule

class StarChartsYamlBuilder(
    yamlFactoryBuilder: YAMLFactory.() -> Unit = {},
    objectMapperBuilder: ObjectMapper.() -> Unit = {}
) {
    private val yamlFactory = YAMLFactory()
        .apply(yamlFactoryBuilder)
        .apply {
            disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER)
            enable(YAMLGenerator.Feature.MINIMIZE_QUOTES)
            enable(YAMLGenerator.Feature.INDENT_ARRAYS_WITH_INDICATOR)
        }

    private val listYamlFactory = YAMLFactory()
        .apply(yamlFactoryBuilder)
        .apply {
            enable(YAMLGenerator.Feature.MINIMIZE_QUOTES)
            enable(YAMLGenerator.Feature.INDENT_ARRAYS_WITH_INDICATOR)
        }

    private val singleOm = ObjectMapper(yamlFactory)
        .apply(objectMapperBuilder)
        .registerKotlinModule()
        .registerModule(JavaTimeModule())
        .setSerializationInclusion(JsonInclude.Include.NON_NULL)

    private val listOm = ObjectMapper(listYamlFactory)
        .apply(objectMapperBuilder)
        .registerKotlinModule()
        .registerModule(JavaTimeModule())
        .setSerializationInclusion(JsonInclude.Include.NON_NULL)

    fun singleBuild(o: Any): String = singleOm.writeValueAsString(o)

    fun <T> build(items: List<T>): String = try {
        if (items.size > 1)
            items.joinToString("\n", transform = listOm::writeValueAsString)
        else {
            singleOm.writeValueAsString(items.firstOrNull())
        }
    } catch (e: JsonMappingException) {
        if (e.message?.contains("cannot be cast to class io.violabs.picard.common.YAMLMap") == true) {
            throw IllegalArgumentException(
                "Missing YAMLMap implementation for ${e.pathReference}"
            )
        } else {
            throw e
        }
    }
}