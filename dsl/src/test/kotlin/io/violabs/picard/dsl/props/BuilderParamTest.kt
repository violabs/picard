package io.violabs.picard.dsl.props

import com.squareup.kotlinpoet.asClassName
import com.squareup.kotlinpoet.asTypeName
import io.violabs.geordi.SimulationGroup
import io.violabs.geordi.UnitSim
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestTemplate

class BuilderParamTest : UnitSim() {
    val typeName = TestResponse::class.asTypeName()
    val buildClassName = TestBuilder::class.asClassName()

    @TestTemplate
    fun `toPropertySpec - happy path - #scenario`(expected: String, nullableProp: Boolean) = test {
        given {
            val param = BuilderPropSchema("test", typeName, buildClassName, nullableProp = nullableProp)

            expect { expected }

            whenever {
                val propSpec = param.toPropertySpec()

                propSpec.toString().trimIndent()
            }
        }
    }

    @Test
    fun `accessors - happy path`() = test {
        given {
            val param = BuilderPropSchema("test", typeName, buildClassName, true)

            expect {
                """
                    |public fun test(block: io.violabs.picard.dsl.props.BuilderParamTest.TestBuilder.() -> kotlin.Unit) {
                    |  val builder = io.violabs.picard.dsl.props.BuilderParamTest.TestBuilder()
                    |  builder.block()
                    |  this.test = builder.build()
                    |}
                """.trimMargin()
            }

            whenever { param.accessors().first().toString().trimIndent() }
        }
    }

    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = setup(
            BuilderParamTest::class,
            SCENARIO_GROUP to { this::`toPropertySpec - happy path - #scenario` }
        )

        private val testResponseClassName = TestResponse::class.asClassName()
        private val propertyString = "private var test: $testResponseClassName"
        val SCENARIO_GROUP = SimulationGroup
            .vars("scenario", "expected", "nullableProp")
            .with("nullable", "$propertyString? = null", true)
            .with("non-null", propertyString, false)
    }

    class TestResponse
    class TestBuilder
}