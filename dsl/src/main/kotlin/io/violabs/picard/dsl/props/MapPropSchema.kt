package io.violabs.picard.dsl.props

import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.STRING
import com.squareup.kotlinpoet.TypeName
import io.violabs.picard.dsl.builder.kotlinPoet
import io.violabs.picard.dsl.builder.kpMapOf

class MapPropSchema(
    override val propName: String,
    val mapKeyType: TypeName = STRING,
    val mapValueType: TypeName = STRING,
    override val nullableAssignment: Boolean = true,
    override val nullableProp: Boolean = true
) : DslPropSchema {
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
        val pairType = pairTypeOf(mapKeyType, mapValueType, nullable = false)

        function {
            add {
                funName = functionName
                varargParam {
                    type(pairType)
                }
                statements {
                    addLine("this.%N = items.toMap()", propName)
                }
            }
        }
    }
}