package io.violabs.picard.metaDsl.schema

import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.STRING
import com.squareup.kotlinpoet.TypeName
import io.violabs.picard.metaDsl.builder.kotlinPoet
import io.violabs.picard.metaDsl.builder.kpListOf

class ListPropSchema(
    override val propName: String,
    val collectionType: TypeName = STRING,
    override val nullableAssignment: Boolean = true,
    override val nullableProp: Boolean = true
) : DslPropSchema {
    override val propTypeName: TypeName = kpListOf(collectionType, nullable = nullableAssignment)

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

    // Example for a list setter (could be more sophisticated, e.g., vararg)
    override fun accessors(): List<FunSpec> = kotlinPoet {
        function {
            add {
                funName = functionName
                varargParam {
                    type(collectionType, nullable = false)
                }
                statements {
                    addLine("this.%N = items.toList()", propName)
                }
            }
        }
    }
}