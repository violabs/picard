package io.violabs.picard.dsl.utils

import com.squareup.kotlinpoet.BOOLEAN
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.LIST
import com.squareup.kotlinpoet.LambdaTypeName
import com.squareup.kotlinpoet.MAP
import com.squareup.kotlinpoet.ParameterSpec
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeName
import com.squareup.kotlinpoet.UNIT
import com.squareup.kotlinpoet.asTypeName

@DslMarker
annotation class PicardDSLMarker

fun <R> kotlinPoet(block: KotlinPoetBuilder.() -> R): R {
    return KotlinPoetBuilder().block()
}

fun kpMapOf(key: TypeName, value: TypeName, nullable: Boolean = true): TypeName =
    MAP.parameterizedBy(key, value).copy(nullable = nullable)

fun kpListOf(type: TypeName, nullable: Boolean = true): TypeName =
    LIST.parameterizedBy(type).copy(nullable = nullable)

@PicardDSLMarker
class KotlinPoetBuilder : ParamSpecEnabled() {
    fun ClassName.nestedClass(
        extensionName: String,
        nestedClassName: String? = null
    ): ClassName = nestedClassName?.let {
        ClassName(this.packageName, simpleName + extensionName, nestedClassName)
    } ?: ClassName(this.packageName, simpleName + extensionName)

    fun listTypeOf(
        parameterClassName: ClassName,
        nullable: Boolean = true
    ): TypeName = LIST.parameterizedBy(parameterClassName).copy(nullable = nullable)

    fun pairTypeOf(
        firstType: TypeName,
        secondType: TypeName,
        nullable: Boolean = true
    ): TypeName =
        Pair::class
            .asTypeName()
            .parameterizedBy(firstType, secondType)
            .copy(nullable = nullable)

    fun propertySpec(
        block: KPPropertySpecBuilder.() -> Unit
    ): PropertySpec {
        return KPPropertySpecBuilder().apply(block).build()
    }

    fun functionSpecs(block: KPFunSpecBuilder.Group.() -> Unit): List<FunSpec> {
        return KPFunSpecBuilder.Group().apply(block).items
    }
}

abstract class ParamSpecEnabled {
    protected var param: ParameterSpec? = null

    fun param(
        block: KPParameterSpecBuilder.() -> Unit
    ): ParameterSpec {
        return KPParameterSpecBuilder().apply(block).build().also { param = it }
    }

    fun varargParam(
        block: KPParameterSpecBuilder.() -> Unit
    ): ParameterSpec {
        return KPParameterSpecBuilder()
            .apply {
                name = "items"
            }
            .apply(block)
            .apply {
                modifiers.add(KModifier.VARARG)
            }
            .build()
            .also { param = it }
    }
}

@PicardDSLMarker
class KPFunSpecBuilder : ParamSpecEnabled() {
    var funName: String? = null
    private var statements: MutableList<KPStatement> = mutableListOf()

    fun statements(block: KPStatement.Group.() -> Unit) {
        statements = KPStatement.Group().apply(block).items
    }

    fun build(): FunSpec {
        val name = requireNotNull(funName) { "name must be set" }
        var spec = FunSpec.builder(name)

        spec = param?.let { spec.addParameter(it) } ?: spec

        for (statement in statements) {
            spec = spec.addStatement(statement.statement, *statement.args.toTypedArray())
        }

        return spec.build()
    }

    @PicardDSLMarker
    class Group {
        val items: MutableList<FunSpec> = mutableListOf()

        fun add(block: KPFunSpecBuilder.() -> Unit) {
            items.add(KPFunSpecBuilder().apply(block).build())
        }
    }
}

@PicardDSLMarker
class KPStatement(
    var statement: String,
    var args: MutableList<Any> = mutableListOf()
) {

    @PicardDSLMarker
    class Group {
        val items: MutableList<KPStatement> = mutableListOf()

        fun addLine(statement: String, vararg args: Any) {
            items.add(KPStatement(statement, args.toMutableList()))
        }
    }
}

@PicardDSLMarker
class KPLambdaTypeNameBuilder : ParamSpecEnabled() {
    var receiver: TypeName? = null
    private var parameters: MutableList<ParameterSpec> = mutableListOf()
    var returnType: TypeName = UNIT

    fun build(): TypeName {
        return LambdaTypeName.get(
            receiver = receiver,
            parameters = parameters,
            returnType = returnType
        )
    }
}

@PicardDSLMarker
class KPParameterSpecBuilder {
    var name: String? = null
    private var type: TypeName? = null
    var modifiers: MutableList<KModifier> = mutableListOf()
    private var defaultValue: String? = null

    fun defaultValue(value: Any?) {
        defaultValue = value?.toString()
    }

    fun lambdaType(block: KPLambdaTypeNameBuilder.() -> Unit) {
        typeCheck()
        if (name == null) name = "block"
        type = KPLambdaTypeNameBuilder().apply(block).build()
    }

    fun booleanType() {
        typeCheck()
        if (name == null) name = "on"
        type = BOOLEAN
    }

    fun type(typeName: TypeName) {
        typeCheck()
        type = typeName
    }

    fun type(typeName: TypeName, nullable: Boolean) {
        typeCheck()
        type = typeName.copy(nullable = nullable)
    }

    private fun typeCheck() {
        if (type != null) throw IllegalArgumentException("type already set: $type")
    }

    fun build(): ParameterSpec {
        var spec = ParameterSpec
            .builder(
                requireNotNull(name) { "name must be set" },
                requireNotNull(type) { "type must be set" },
                *modifiers.toTypedArray()
            )

        if (defaultValue != null) spec = spec.defaultValue(defaultValue!!)

        return spec.build()
    }
}

@PicardDSLMarker
class KPPropertySpecBuilder {
    var name: String? = null
    private var type: TypeName? = null
    var modifiers: MutableList<KModifier> = mutableListOf()
    private var mutable: Boolean = true
    var initializer: String? = null

    fun mutable(on: Boolean = true) {
        mutable = on
    }

    fun variable() {
        mutable = true
    }

    fun value() {
        mutable = false
    }

    fun initNullValue() {
        initializer = "null"
    }

    fun accessModifier(modifier: KModifier) {
        if(ALL_ACCESS_MODIFIERS.any { it in modifiers }) throw IllegalArgumentException("access modifier already set")

        modifiers.add(modifier)
    }

    fun private() {
        modifiers.add(KModifier.PRIVATE)
    }

    fun type(typeName: TypeName) {
        typeCheck()
        type = typeName
    }

    fun type(typeName: TypeName, nullable: Boolean) {
        typeCheck()
        type = typeName.copy(nullable = nullable)
    }

    private fun typeCheck() {
        if (type != null) throw IllegalArgumentException("type already set: $type")
    }

    fun build(): PropertySpec {
        var spec = PropertySpec
            .builder(
                requireNotNull(name) { "name must be set" },
                requireNotNull(type) { "type must be set" },
                *modifiers.toTypedArray()
            )
            .mutable(mutable)

        spec = initializer?.let { spec.initializer(it) } ?: spec

        return spec.build()
    }

    companion object {
        val ALL_ACCESS_MODIFIERS = listOf(KModifier.PUBLIC, KModifier.PROTECTED, KModifier.INTERNAL, KModifier.PRIVATE)
    }
}