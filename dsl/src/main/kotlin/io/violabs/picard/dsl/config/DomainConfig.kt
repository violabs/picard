package io.violabs.picard.dsl.config

import com.google.devtools.ksp.processing.Dependencies
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.ksp.toClassName

class DomainConfig(
    val builderConfig: BuilderConfig,
    val singleEntryTransformByClassName: Map<String, KSClassDeclaration>,
    var domain: KSClassDeclaration,
) {
    val packageName = domain.packageName.asString()
    val typeName = domain.simpleName.asString()
    val domainClassName: ClassName = domain.toClassName()
    val builderName = "${typeName}DSLBuilder"
    val builderClassName = ClassName(packageName, builderName)
    val dslBuilderInterface = ClassName(builderConfig.dslBuilderClasspath, "DSLBuilder")
    val parameterizedDslBuilder = dslBuilderInterface.parameterizedBy(domainClassName)

    val fileClassName = ClassName(packageName, "${typeName}Dsl")
    val dependencies = Dependencies(aggregating = false, sources = listOfNotNull(domain.containingFile).toTypedArray())
}