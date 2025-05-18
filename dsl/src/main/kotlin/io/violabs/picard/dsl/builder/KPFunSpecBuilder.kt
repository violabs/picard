package io.violabs.picard.dsl.builder

import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.TypeName

@PicardDSLMarker
internal class KPFunSpecBuilder : DefaultParamSpecEnabled() {
    var funName: String? = null
    var returns: TypeName? = null
    private var statements: MutableList<KPStatement> = mutableListOf()

    fun statements(block: KPStatement.Group.() -> Unit) {
        statements = KPStatement.Group().apply(block).items
    }

    fun build(): FunSpec {
        val name = requireNotNull(funName) { "name must be set" }
        var spec = FunSpec.builder(name)

        spec = param?.let { spec.addParameter(it) } ?: spec

        spec = returns?.let { spec.returns(it) } ?: spec

        for (statement in statements) {
            spec = spec.addStatement(statement.statement, *statement.args.toTypedArray())
        }

        return spec.build()
    }

    @PicardDSLMarker
    internal class Group {
        val items: MutableList<FunSpec> = mutableListOf()

        fun add(block: KPFunSpecBuilder.() -> Unit) {
            items.add(KPFunSpecBuilder().apply(block).build())
        }
    }
}