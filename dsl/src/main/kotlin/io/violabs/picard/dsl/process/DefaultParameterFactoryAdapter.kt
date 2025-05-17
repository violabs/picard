package io.violabs.picard.dsl.process

import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSPropertyDeclaration
import com.google.devtools.ksp.symbol.KSType
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.TypeName
import com.squareup.kotlinpoet.ksp.toClassName
import com.squareup.kotlinpoet.ksp.toTypeName
import io.violabs.picard.dsl.annotation.GeneratedDSL
import io.violabs.picard.dsl.annotation.GeneratedGroupDSL
import io.violabs.picard.dsl.annotation.SingleEntryTransformDSL

class DefaultParameterFactoryAdapter(
    prop: KSPropertyDeclaration,
    singleEntryTransform: KSClassDeclaration?
) : ParameterFactoryAdapter {
    override val propName: String = prop.simpleName.asString()
    override val actualPropTypeName: TypeName = prop.type.toTypeName()
    override val hasSingleEntryTransform: Boolean = singleEntryTransform != null

    private val singleEntryTransformAnnotation = singleEntryTransform
        ?.annotations
        ?.find { it.shortName.asString() == SingleEntryTransformDSL::class.simpleName }

    override val transformTemplate = singleEntryTransformAnnotation
        ?.arguments
        ?.firstOrNull { it.name?.asString() == "transformTemplate" }
        ?.value
        ?.toString()
        ?.takeIf { it.isNotBlank() }

    override val transformType = singleEntryTransformAnnotation
        ?.arguments
        ?.firstOrNull { it.name?.asString() == "inputType" }
        ?.let { it.value as? KSType }
        ?.toTypeName()

    private val resolvedPropKSType: KSType = prop.type.resolve()

    override val hasNullableAssignment: Boolean = resolvedPropKSType.isMarkedNullable

    private val propertyClassDeclaration = resolvedPropKSType.declaration as? KSClassDeclaration

    override val propertyNonNullableClassName: ClassName? = propertyClassDeclaration?.toClassName()

    override val hasGeneratedDSLAnnotation: Boolean = propertyClassDeclaration?.annotations?.any {
        it.shortName.asString() == GeneratedDSL::class.simpleName
    } ?: false

    override val propertyClassDeclarationQualifiedName: String? = propertyClassDeclaration?.qualifiedName?.asString()

    private val listElementClassDecl = resolvedPropKSType
        .arguments
        .firstOrNull()
        ?.type
        ?.resolve()
        ?.declaration as? KSClassDeclaration

    override val isGroupElement: Boolean = listElementClassDecl
        ?.annotations
        ?.any { it.shortName.asString() == GeneratedGroupDSL::class.simpleName }
        ?: false

    override val groupElementClassName: ClassName? = listElementClassDecl?.toClassName()
}