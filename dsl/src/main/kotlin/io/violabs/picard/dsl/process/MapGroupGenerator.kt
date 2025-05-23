package io.violabs.picard.dsl.process

import io.violabs.picard.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.dsl.process.GroupGenerator.Config
import io.violabs.picard.dsl.process.GroupGenerator.Namespace
import io.violabs.picard.metaDsl.builder.AnnotationDecorator
import io.violabs.picard.metaDsl.builder.kpMapOf
import io.violabs.picard.metaDsl.builder.kpMutableMapOf

private val MAP_GROUP_GENERATOR_CONFIG = Config(
    namespace = Namespace(
    checkName = "isMapGroup", typeName = "MapGroup", typeVariable = "T"
), property = GeneratedDsl::withMapGroup, templates = GroupGenerator.Templates(
    prop = "mutableMapOf()",
    itemsReturn = "return items.toMap()",
    builderAdd = "items[key] = %T().apply(block).build()"
), { argument ->
    val activeTypes = GeneratedDsl.MapGroupType.ACTIVE_TYPES.map { it?.name }
    argument.value?.toString() in activeTypes
}, propertyTypeAssigner = { typeVar, className ->
    val typeVariable = requireNotNull(typeVar) { "Parameterized Type required for MapGroup" }
    kpMutableMapOf(typeVariable, className, nullable = false)
}, builtTypeAssigner = { typeVar, className ->
    val typeVariable = requireNotNull(typeVar) { "Parameterized Type required for MapGroup" }
    kpMapOf(typeVariable, className, nullable = false)
})

class MapGroupGenerator(
    annotationDecorator: AnnotationDecorator = AnnotationDecorator()
) : GroupGenerator<String>(MAP_GROUP_GENERATOR_CONFIG, annotationDecorator) {
    override fun logId(): String? = this::class.simpleName
}