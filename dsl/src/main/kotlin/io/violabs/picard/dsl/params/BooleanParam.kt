package io.violabs.picard.dsl.params

import com.squareup.kotlinpoet.*
import io.violabs.picard.dsl.builder.kotlinPoet

class BooleanParam(
    override val propName: String,
    override val nullableAssignment: Boolean = true,
    override val nullableProp: Boolean = true
) : DSLParam {
    override val propTypeName: TypeName = BOOLEAN.copy(nullable = nullableAssignment) // Correctly use constructor arg

    override fun accessors(): List<FunSpec> = kotlinPoet {
        function {
            add {
                funName = propName
                val param = param {
                    booleanType()
                    defaultValue(true)
                }
                statements {
                    addLine("this.%N = %N", propName, param)
                }
            }
        }
    }
}