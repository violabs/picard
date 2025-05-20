package io.violabs.picard.dsl.process

import com.google.devtools.ksp.symbol.KSAnnotation
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSPropertyDeclaration
import com.google.devtools.ksp.symbol.KSType
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.ParameterizedTypeName
import com.squareup.kotlinpoet.TypeName
import com.squareup.kotlinpoet.ksp.toClassName
import com.squareup.kotlinpoet.ksp.toTypeName
import io.violabs.picard.dsl.annotation.GeneratedDSL
import io.violabs.picard.dsl.annotation.SingleEntryTransformDSL

class DefaultParameterFactoryAdapter(
    prop: KSPropertyDeclaration,
    singleEntryTransform: KSClassDeclaration?,
) : ParameterFactoryAdapter {
    override val propName: String = prop.simpleName.asString()
    override val actualPropTypeName: TypeName = prop.type.toTypeName()
    override val hasSingleEntryTransform: Boolean = singleEntryTransform != null

    constructor(propertyAdapter: DefaultPropertyAdapter) : this(
        propertyAdapter.prop,
        propertyAdapter.singleEntryTransform()
    )

    private val singleEntryTransformAnnotation = singleEntryTransform
        ?.annotations
        ?.find { it.shortName.asString() == SingleEntryTransformDSL::class.simpleName }

    override val transformTemplate = singleEntryTransformAnnotation
        ?.arguments
        ?.firstOrNull { it.name?.asString() == SingleEntryTransformDSL<*>::transformTemplate.name }
        ?.value
        ?.toString()
        ?.takeIf { it.isNotBlank() }

    override val transformType = singleEntryTransformAnnotation
        ?.arguments
        ?.firstOrNull { it.name?.asString() == SingleEntryTransformDSL<*>::inputType.name }
        ?.let { it.value as? KSType }
        ?.toTypeName()

    private val resolvedPropKSType: KSType = prop.type.resolve()

    override val hasNullableAssignment: Boolean = resolvedPropKSType.isMarkedNullable

    private val classDeclarationInternal = resolvedPropKSType.declaration as? KSClassDeclaration

    override val propertyNonNullableClassName: ClassName? = classDeclarationInternal?.toClassName()

    override val hasGeneratedDSLAnnotation: Boolean = classDeclarationInternal?.annotations?.any {
        it.shortName.asString() == GeneratedDSL::class.simpleName
    } ?: false

    override val propertyClassDeclarationQualifiedName: String? = classDeclarationInternal?.qualifiedName?.asString()
    override val propertyClassDeclaration: KSClassDeclaration? = classDeclarationInternal

    // list only
    private val collectionFirstElementClassDecl = resolvedPropKSType
        .arguments
        .firstOrNull()
        ?.type
        ?.resolve()
        ?.declaration as? KSClassDeclaration

    // value in map
    private val collectionSecondElementClassDecl = resolvedPropKSType
        .arguments
        .lastOrNull()
        ?.type
        ?.resolve()
        ?.declaration as? KSClassDeclaration

    override val isGroupElement: Boolean = collectionFirstElementClassDecl
        ?.annotations
        ?.filter { it.shortName.asString() == GeneratedDSL::class.simpleName }
        ?.any { annotation ->
            annotation
                .arguments
                .firstOrNull { it.name?.asString() == GeneratedDSL::withGroup.name }
                ?.value == true
        }
        ?: false

    override val groupElementClassName: ClassName? = collectionFirstElementClassDecl?.toClassName()
    override val groupElementClassDeclaration: KSClassDeclaration? = collectionFirstElementClassDecl

    private val dslAnnotations: List<KSAnnotation>? = collectionSecondElementClassDecl
        ?.annotations
        ?.filter { it.shortName.asString() == GeneratedDSL::class.simpleName }
        ?.toList()

    private fun mapGroupType(): GeneratedDSL.MapGroupType? {
        val arguments = dslAnnotations?.flatMap(KSAnnotation::arguments)
        val mapGroup = arguments
            ?.firstOrNull { it.name?.asString() == GeneratedDSL::withMapGroup.name }
            ?: return null

        return GeneratedDSL.MapGroupType.valueOf(mapGroup.value.toString().uppercase())
    }

    override var mapDetails: ParameterFactoryAdapter.MapDetails? = null
    override val mapValueClassDeclaration: KSClassDeclaration? = collectionSecondElementClassDecl

    override fun mapDetails(): ParameterFactoryAdapter.MapDetails? {
        if (mapDetails != null) return mapDetails

        val groupType = mapGroupType() ?: return null

        if (actualPropTypeName !is ParameterizedTypeName) return null

        val typeRefs = actualPropTypeName.typeArguments
        return MapDetails(groupType, typeRefs.first(), typeRefs.last()).also { mapDetails = it }
    }

    class MapDetails(
        override val mapGroupType: GeneratedDSL.MapGroupType = GeneratedDSL.MapGroupType.SINGLE,
        override val keyType: TypeName,
        override val valueType: TypeName
    ) : ParameterFactoryAdapter.MapDetails
}