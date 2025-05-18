package io.violabs.picard.dsl.process

import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSPropertyDeclaration
import com.squareup.kotlinpoet.TypeName
import com.squareup.kotlinpoet.ksp.toTypeName

interface PropertyAdapter {
    val type: TypeName?

    fun simpleName(): String
    fun continueBranch(): Boolean
    fun singleEntryTransformString(): String?
}

data class DefaultPropertyAdapter(
    val index: Int,
    val lastIndex: Int,
    val prop: KSPropertyDeclaration,
    val singleEntryTransformMap: Map<String, KSClassDeclaration>
) : PropertyAdapter {
    override val type: TypeName? = prop.type.toTypeName().copy(nullable = false)

    override fun simpleName(): String {
        return prop.simpleName.asString()
    }

    override fun continueBranch(): Boolean = index != lastIndex

    fun singleEntryTransform(): KSClassDeclaration? = singleEntryTransformMap[type.toString()]

    override fun singleEntryTransformString(): String? = singleEntryTransform()?.toString()
}