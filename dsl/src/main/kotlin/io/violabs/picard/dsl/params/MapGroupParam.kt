package io.violabs.picard.dsl.params

import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import io.violabs.picard.dsl.builder.kotlinPoet
import io.violabs.picard.dsl.builder.kpMapOf

class MapGroupParam(
    override val propName: String,
    val mapKeyType: TypeName = STRING,
    val mapValueType: TypeName,
    override val nullableAssignment: Boolean = true,
    override val nullableProp: Boolean = true
) : DSLParam {
    override val propTypeName: TypeName = kpMapOf(mapKeyType, mapValueType, nullable = nullableAssignment)

    override val verifyNotNull: Boolean = false
    override val verifyNotEmpty: Boolean = true

    override fun toPropertySpec(): PropertySpec = kotlinPoet {
        property {
            private()
            variable()
            name = propName
            type(propTypeName)

            if (nullableAssignment) initNullValue()
        }
    }

    override fun accessors(): List<FunSpec> = kotlinPoet {
        val valueClassName = mapValueType.copy(nullable = false) as ClassName
        val mapGroupClass = ClassName(
            valueClassName.packageName,
            valueClassName.simpleName + "Builder",
            "MapGroup"
        ).parameterizedBy(mapKeyType.copy(nullable = false))

        function {
            add {
                funName = functionName
                param {
                    lambdaType {
                        receiver = mapGroupClass
                    }
                }

                statements {
                    addLine("this.%N = %T().apply(block).items().toMap()", propName, mapGroupClass)
                }
            }
        }
    }
}