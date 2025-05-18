package io.violabs.picard.dsl.builder

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.LIST
import com.squareup.kotlinpoet.MAP
import com.squareup.kotlinpoet.MUTABLE_LIST
import com.squareup.kotlinpoet.MUTABLE_MAP
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeName
import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.asTypeName

internal fun <R> kotlinPoet(block: KotlinPoetBuilder.() -> R): R {
    return KotlinPoetBuilder().block()
}

internal fun kpMapOf(key: TypeName, value: TypeName, nullable: Boolean = true): TypeName =
    MAP.parameterizedBy(key, value).copy(nullable = nullable)

internal fun kpMutableMapOf(key: TypeName, value: TypeName, nullable: Boolean = true): TypeName =
    MUTABLE_MAP.parameterizedBy(key, value).copy(nullable = nullable)

internal fun kpListOf(type: TypeName, nullable: Boolean = true): TypeName =
    LIST.parameterizedBy(type).copy(nullable = nullable)

internal fun kpMutableListOf(type: TypeName, nullable: Boolean = true): TypeName =
    MUTABLE_LIST.parameterizedBy(type).copy(nullable = nullable)

@PicardDSLMarker
internal class KotlinPoetBuilder : DefaultParamSpecEnabled() {
    fun ClassName.nestedClass(
        extensionName: String,
        nestedClassName: String? = null
    ): ClassName = nestedClassName?.let {
        ClassName(this.packageName, simpleName + extensionName, nestedClassName)
    } ?: ClassName(this.packageName, simpleName + extensionName)

    fun listTypeOf(
        parameterClassName: ClassName,
        nullable: Boolean = true
    ): TypeName = kpListOf(parameterClassName, nullable = nullable)

    fun mutableListOf(parameterClassName: ClassName): TypeName =
        kpMutableListOf(parameterClassName, nullable = false)

    fun pairTypeOf(
        firstType: TypeName,
        secondType: TypeName,
        nullable: Boolean = true
    ): TypeName =
        Pair::class
            .asTypeName()
            .parameterizedBy(firstType, secondType)
            .copy(nullable = nullable)

    fun property(
        block: KPPropertySpecBuilder.() -> Unit
    ): PropertySpec {
        return KPPropertySpecBuilder().apply(block).build()
    }

    fun function(block: KPFunSpecBuilder.Group.() -> Unit): List<FunSpec> {
        return KPFunSpecBuilder.Group().apply(block).items
    }

    fun type(block: KPTypeBuilder.() -> Unit): TypeSpec {
        return KPTypeBuilder().apply(block).build()
    }
}