package io.violabs.picard.dsl.params

import com.squareup.kotlinpoet.*
import io.violabs.picard.dsl.utils.kotlinPoet

class BuilderParam(
    override val propName: String,
    originalPropertyType: TypeName,
    private val nestedBuilderClassName: ClassName,
    override val nullableAssignment: Boolean = true,
    override val nullableProp: Boolean = true
) : DSLParam {
    override val propTypeName: TypeName = originalPropertyType

    override fun accessors(): List<FunSpec> = kotlinPoet {
        functionSpecs {
            add {
                funName = functionName
                param {
                    lambdaType {
                        receiver = nestedBuilderClassName
                    }
                }

                statements {
                    addLine("val builder = %T()", nestedBuilderClassName)
                    addLine("builder.block()")
                    addLine("this.%N = builder.build()", propName)
                }
            }
        }
    }
}