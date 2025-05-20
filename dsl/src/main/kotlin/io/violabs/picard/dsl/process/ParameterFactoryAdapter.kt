package io.violabs.picard.dsl.process

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.TypeName
import io.violabs.picard.dsl.annotation.GeneratedDSL

interface ParameterFactoryAdapter {
    val propName: String
    val actualPropTypeName: TypeName
    val hasSingleEntryTransform: Boolean
    val transformTemplate: String?
    val transformType: TypeName?
    val hasNullableAssignment: Boolean
    val propertyNonNullableClassName: ClassName?
    val hasGeneratedDSLAnnotation: Boolean
    val propertyClassDeclarationQualifiedName: String?
    val isGroupElement: Boolean
    val groupElementClassName: ClassName?
    var mapDetails: MapDetails?

    fun mapDetails(): MapDetails? = null

    fun nonNullablePropTypeName(): TypeName = actualPropTypeName.copy(nullable = false)

    interface MapDetails {
        val mapGroupType: GeneratedDSL.MapGroupType
        val keyType: TypeName
        val valueType: TypeName

        fun valueClass(): ClassName = valueType.copy(nullable = false) as ClassName
    }
}