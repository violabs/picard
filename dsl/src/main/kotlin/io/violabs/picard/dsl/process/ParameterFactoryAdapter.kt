package io.violabs.picard.dsl.process

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.TypeName

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
}