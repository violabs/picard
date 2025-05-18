package io.violabs.picard.dsl.params

import com.squareup.kotlinpoet.*
import io.violabs.picard.dsl.builder.kotlinPoet
import io.violabs.picard.dsl.builder.kpListOf

class ListParam(
    override val propName: String,
    val collectionType: TypeName = STRING,
    override val nullableAssignment: Boolean = true,
    override val nullableProp: Boolean = true
) : DSLParam {
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