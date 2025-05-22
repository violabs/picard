package io.violabs.picard.dsl.process

import io.violabs.picard.dsl.annotation.GeneratedDsl
import io.violabs.picard.dsl.builder.AnnotationDecorator
import io.violabs.picard.dsl.builder.kpListOf
import io.violabs.picard.dsl.builder.kpMutableListOf
import io.violabs.picard.dsl.process.GroupGenerator.Config
import io.violabs.picard.dsl.process.GroupGenerator.Namespace

private val LIST_GROUP_GENERATOR_CONFIG = Config(
    namespace = Namespace(
        checkName = "isListGroup",
        typeName = "Group"
    ),
    property = GeneratedDsl::withListGroup,
    templates = GroupGenerator.Templates(
        prop = "mutableListOf()",
        itemsReturn = "return items.toList()",
        builderAdd = "items.add(%T().apply(block).build())"
    ),
    { it.value.toString() == "true" },
    propertyTypeAssigner = { _, className -> kpMutableListOf(className, nullable = false) },
    builtTypeAssigner = { _, nullable -> kpListOf(nullable) },
)

class ListGroupGenerator(
    annotationDecorator: AnnotationDecorator = AnnotationDecorator()
) : GroupGenerator<Boolean>(
    LIST_GROUP_GENERATOR_CONFIG,
    annotationDecorator
) {
    override fun logId(): String? = "LIST_GRP_GENERATOR"
}