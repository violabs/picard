package io.violabs.picard.dsl.params

import com.squareup.kotlinpoet.*
import io.violabs.picard.dsl.builder.kotlinPoet

class BuilderParam(
    override val propName: String,
    originalPropertyType: TypeName,
    private val nestedBuilderClassName: ClassName,
    override val nullableAssignment: Boolean = true,
    override val nullableProp: Boolean = true,
    private val kdoc: String? = null
) : DSLParam {
    override val propTypeName: TypeName = originalPropertyType

    override fun accessors(): List<FunSpec> = kotlinPoet {
        function {
            add {
                funName = functionName
                kdoc?.let { kdoc(it) }
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