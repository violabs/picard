package io.violabs.picard.dsl.params

import com.squareup.kotlinpoet.*
import io.violabs.picard.dsl.utils.kotlinPoet
import io.violabs.picard.dsl.utils.kpListOf

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
        propertySpec {
            private()
            variable()
            name = propName
            type(propTypeName)

            if (nullableAssignment) initNullValue()
        }
    }

    // Example for a list setter (could be more sophisticated, e.g., vararg)
    override fun accessors(): List<FunSpec> = kotlinPoet {
        functionSpecs {
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