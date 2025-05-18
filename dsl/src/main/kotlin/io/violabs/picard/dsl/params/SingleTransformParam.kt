package io.violabs.picard.dsl.params

import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.TypeName
import io.violabs.picard.dsl.utils.kotlinPoet

class SingleTransformParam(
    override val propName: String,
    val inputTypeName: TypeName,
    actualPropTypeName: TypeName,
    val transformTemplate: String? = null,
    override val nullableAssignment: Boolean = true,
    override val nullableProp: Boolean = true
) : DSLParam {
    override val propTypeName: TypeName = actualPropTypeName.copy(nullable = nullableAssignment)

    override fun accessors(): List<FunSpec> = kotlinPoet {
        functionSpecs {
            add {
                funName = functionName
                val param = param {
                    name = propName
                    type(inputTypeName)
                }
                statements {
                    val finalTransformTemplate = transformTemplate ?: "${propTypeName.copy(nullable = false)}(%N)"
                    addLine("this.%N = $finalTransformTemplate", propName, param)
                }
            }
        }
    }
}