package io.violabs.picard.metaDsl.builder

import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeName

@PicardDSLMarker
class KPFunSpecBuilder : DefaultParamSpecEnabled() {
    var funName: String? = null
    var returns: TypeName? = null
    var kdoc: String? = null
    private var overridden: Boolean = false
    private var statements: MutableList<KPStatement> = mutableListOf()

    fun override(on: Boolean = true) {
        overridden = on
    }

    fun kdoc(text: String) {
        kdoc = text
    }

    fun statements(block: KPStatement.Group.() -> Unit) {
        statements = KPStatement.Group().apply(block).items
    }

    fun build(): FunSpec {
        val name = requireNotNull(funName) { "Fun - funName must be set" }
        var spec = FunSpec.builder(name)

        for (param in params) {
            spec = spec.addParameter(param)
        }

        spec = returns?.let { spec.returns(it) } ?: spec

        if (overridden) spec = spec.addModifiers(KModifier.OVERRIDE)
        if (kdoc != null) spec = spec.addKdoc(kdoc!!)

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

        fun addAll(specs: List<FunSpec>) {
            items.addAll(specs)
        }

        /**
         * Will use the [transformFn] on each item in the [list] and add the resulting
         * [PropertySpec] to the list.
         * You can use the extension of [List.addForEach] within this scope.
         */
        fun <T> addForEachIn(list: List<T>, transformFn: (T) -> List<FunSpec>) {
            list.forEach { addAll(transformFn(it)) }
        }

        /**
         * Will use the [transformFn] on each item int this list and add the resulting
         * [PropertySpec] to the items list.
         */
        fun <T> List<T>.addForEach(transformFn: (T) -> List<FunSpec>) = this.forEach { addAll(transformFn(it)) }
    }
}