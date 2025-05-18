package io.violabs.picard.dsl.builder

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeName
import com.squareup.kotlinpoet.TypeSpec

@PicardDSLMarker
internal class KPTypeSpecBuilder : DefaultKotlinPoetSpec() {
    private var superInterface: TypeName? = null
    private val annotationNames: MutableList<ClassName> = mutableListOf()
    private var properties: MutableList<PropertySpec> = mutableListOf()
    private var functions: MutableList<FunSpec> = mutableListOf()
    private var nested: MutableList<TypeSpec> = mutableListOf()

    fun superInterface(superInterface: TypeName) {
        this.superInterface = superInterface
    }

    fun annotation(packageName: String, annotationSimpleName: String) {
        annotationNames.add(ClassName(packageName, annotationSimpleName))
    }

    fun annotation(provider: () -> ClassName?) {
        provider()?.let { annotationNames.add(it) }
    }

    fun properties(block: KPPropertySpecBuilder.Group.() -> Unit) {
        properties = KPPropertySpecBuilder.Group().apply(block).items
    }

    fun properties(properties: List<PropertySpec>) {
        this.properties = properties.toMutableList()
    }

    fun functions(block: KPFunSpecBuilder.Group.() -> Unit) {
        functions = KPFunSpecBuilder.Group().apply(block).items
    }

    fun nested(block: Group.() -> Unit) {
        nested = Group().apply(block).items
    }

    fun build(): TypeSpec {
        var typeBuilder = TypeSpec
            .classBuilder(requireNotNull(name) { "Type - name must be set" })

        for (annotation in annotationNames) {
            typeBuilder = typeBuilder.addAnnotation(annotation)
        }

        for (property in properties) {
            typeBuilder = typeBuilder.addProperty(property)
        }

        for (function in functions) {
            typeBuilder = typeBuilder.addFunction(function)
        }

        for (nestedType in nested) {
            typeBuilder = typeBuilder.addType(nestedType)
        }

        return typeBuilder.build()
    }

    class Group {
        val items: MutableList<TypeSpec> = mutableListOf()

        fun addType(block: KPTypeSpecBuilder.() -> Unit) {
            items.add(KPTypeSpecBuilder().apply(block).build())
        }
    }
}