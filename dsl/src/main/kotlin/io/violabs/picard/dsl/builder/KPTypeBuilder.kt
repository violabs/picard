package io.violabs.picard.dsl.builder

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeName
import com.squareup.kotlinpoet.TypeSpec

@PicardDSLMarker
internal class KPTypeBuilder : DefaultKotlinPoetSpec() {
    private var superInterface: TypeName? = null
    private val annotationNames: MutableList<ClassName> = mutableListOf()
    private var properties: MutableList<PropertySpec> = mutableListOf()
    private var functions: MutableList<FunSpec> = mutableListOf()

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

    fun functions(block: KPFunSpecBuilder.Group.() -> Unit) {
        functions = KPFunSpecBuilder.Group().apply(block).items
    }

    fun build(): TypeSpec {
        var typeBuilder = TypeSpec
            .classBuilder(requireNotNull(name) { "name must be set" })

        for (annotation in annotationNames) {
            typeBuilder = typeBuilder.addAnnotation(annotation)
        }

        for (property in properties) {
            typeBuilder = typeBuilder.addProperty(property)
        }

        for (function in functions) {
            typeBuilder = typeBuilder.addFunction(function)
        }

        return typeBuilder.build()
    }
}