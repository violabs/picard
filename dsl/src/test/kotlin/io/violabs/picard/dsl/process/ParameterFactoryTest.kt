package io.violabs.picard.dsl.process

import com.google.devtools.ksp.processing.KSPLogger
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.LIST
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.STRING
import com.squareup.kotlinpoet.TypeName
import com.squareup.kotlinpoet.asTypeName
import io.violabs.geordi.UnitSim
import io.violabs.picard.common.Logger
import io.violabs.picard.dsl.annotation.GeneratedGroupDSL
import org.junit.jupiter.api.Test

class ParameterFactoryTest : UnitSim() {
    val parameterFactory = DefaultParameterFactory2(Logger("TEST"))

    @Test
    fun `determineParam will create a default param`() = test {
        given {
            val adapter = TestAdapter()

            expect {
                TestResponse(
                    "public var test: kotlin.String? = null\n",
                    ""
                )
            }

            whenever {
                val param = parameterFactory.determineParam(adapter)

                TestResponse(
                    param.toPropertySpec().toString(),
                    param.accessors().firstOrNull()?.toString() ?: ""
                )
            }
        }
    }

    @Test
    fun `determineParam will create a group param`() = test {
        given {
            val adapter = TestAdapter(
                LIST.parameterizedBy(Example::class.asTypeName()),
                isGroup = true
            )

            expect {
                TestResponse(
                    "private var test: kotlin.collections.List<test.Example>? = null\n",
                    """
                        |public fun test(block: test.ExampleBuilder.Group.() -> kotlin.Unit) {
                        |  this.test = test.ExampleBuilder.Group().apply(block).items()
                        |}
                        |
                    """.trimMargin()
                )
            }

            whenever {
                val param = parameterFactory.determineParam(adapter)

                TestResponse(
                    param.toPropertySpec().toString(),
                    param.accessors().firstOrNull()?.toString() ?: ""
                )
            }
        }
    }

    class TestAdapter(
        override val actualPropTypeName: TypeName = STRING,
        val isGroup: Boolean = false
    ) : ParameterFactoryAdapter {
        override val propName: String = "test"
        override val hasSingleEntryTransform: Boolean = false
        override val transformTemplate: String? = null
        override val transformType: TypeName? = null
        override val hasNullableAssignment: Boolean = false
        override val propertyNonNullableClassName: ClassName? = null
        override val hasGeneratedDSLAnnotation: Boolean = false
        override val propertyClassDeclarationQualifiedName: String? = null
        override val isGroupElement: Boolean = isGroup
        override val groupElementClassName: ClassName? = ClassName("test", "Example")
    }

    data class TestResponse(
        val propertyContent: String,
        val functionContent: String
    )

    @GeneratedGroupDSL
    class Example
}