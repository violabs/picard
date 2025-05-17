package io.violabs.picard.dsl.params

import com.squareup.kotlinpoet.asClassName
import com.squareup.kotlinpoet.asTypeName
import io.violabs.geordi.SimulationGroup
import io.violabs.geordi.UnitSim
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestTemplate


class SingleTransformParamTest : UnitSim() {
    val propTypeName = TestCase::class.asTypeName()
    val inputTypeName = String::class.asTypeName()

    @TestTemplate
    fun `toPropertySpec - happy path - #scenario`(expected: String, nullableProp: Boolean) = test {
        given {
            val param = SingleTransformParam(
                "test",
                inputTypeName,
                propTypeName,
                nullableProp = nullableProp
            )

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
            val param = SingleTransformParam(
                "test",
                inputTypeName,
                propTypeName,
                nullableAssignment = true
            )

            expect {
                """
                    |public fun test(test: kotlin.String) {
                    |  this.test = $testResponseClassName(test)
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
            DefaultParamTest::class,
            SCENARIO_GROUP to { this::`toPropertySpec - happy path - #scenario` }
        )

        private val testResponseClassName = TestCase::class.asClassName()
        private val propertyString = "private var test: $testResponseClassName"

        val SCENARIO_GROUP = SimulationGroup
            .vars("scenario", "expected", "nullableProp")
            .with("nullable", "$propertyString? = null", true)
            .with("non-null", propertyString, false)
    }
}

private class TestCase(val scenario: String)