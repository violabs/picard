package io.violabs.picard.dsl.process

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.LIST
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.STRING
import com.squareup.kotlinpoet.TypeName
import com.squareup.kotlinpoet.asTypeName
import com.google.devtools.ksp.symbol.KSClassDeclaration
import io.violabs.geordi.UnitSim
import io.violabs.picard.dsl.annotation.GeneratedDSL
import org.junit.jupiter.api.Test

class ParameterFactoryTest : UnitSim() {
    val parameterFactory = object : AbstractPropertyFactory<TestParamFactoryAdaptor, TestPropAdapter>() {
        override fun createPropertyFactoryAdapter(propertyAdapter: TestPropAdapter): TestParamFactoryAdaptor {
            return TestParamFactoryAdaptor(propertyAdapter.type, propertyAdapter.isGroup)
        }
    }

    @Test
    fun `determineParam will create a default param`() = test {
        given {
            val adapter = TestParamFactoryAdaptor()

            expect {
                TestResponse(
                    "public var test: kotlin.String? = null\n",
                    ""
                )
            }

            whenever {
                val param = parameterFactory.determineProperty(adapter)

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
            val adapter = TestParamFactoryAdaptor(
                LIST.parameterizedBy(Example::class.asTypeName()),
                isGroup = true
            )

            expect {
                TestResponse(
                    "private var test: kotlin.collections.List<test.Example>? = null\n",
                    """
                        |public fun test(block: test.ExampleDSLBuilder.Group.() -> kotlin.Unit) {
                        |  this.test = test.ExampleDSLBuilder.Group().apply(block).items()
                        |}
                        |
                    """.trimMargin()
                )
            }

            whenever {
                val param = parameterFactory.determineProperty(adapter)

                TestResponse(
                    param.toPropertySpec().toString(),
                    param.accessors().firstOrNull()?.toString() ?: ""
                )
            }
        }
    }

    class TestParamFactoryAdaptor(
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
        override val propertyClassDeclaration: KSClassDeclaration? = null
        override val isGroupElement: Boolean = isGroup
        override val groupElementClassName: ClassName? = ClassName("test", "Example")
        override val groupElementClassDeclaration: KSClassDeclaration? = null
        override var mapDetails: ParameterFactoryAdapter.MapDetails? = null
        override val mapValueClassDeclaration: KSClassDeclaration? = null
    }

    class TestPropAdapter(
        override val type: TypeName = STRING,
        val isGroup: Boolean = false
    ) : PropertyAdapter {
        override fun simpleName(): String = "test"
        override fun continueBranch(): Boolean = false
        override fun singleEntryTransformString(): String? = null
    }

    data class TestResponse(
        val propertyContent: String,
        val functionContent: String
    )

    @GeneratedDSL(
        withGroup = true
    )
    class Example
}