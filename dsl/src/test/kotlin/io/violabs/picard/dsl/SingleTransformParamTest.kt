package io.violabs.picard.dsl

import com.squareup.kotlinpoet.asTypeName
import io.violabs.geordi.SimulationGroup
import io.violabs.geordi.UnitSim
import io.violabs.picard.dsl.param.SingleTransformParam
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestTemplate


class SingleTransformParamTest : UnitSim() {
    val propTypeName = TestCase::class.asTypeName()
    val inputTypeName = String::class.asTypeName()

    @TestTemplate
    fun `toPropertySpec - happy path - #scenario`(expected: String, nullable: Boolean) = test {
        given {
            val param = SingleTransformParam(
                "test",
                inputTypeName,
                propTypeName,
                nullable = true
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
                nullable = true
            )

            expect {
                """
                    |public fun test(test: kotlin.String) {
                    |  this.test = io.violabs.picard.dsl.TestCase(test)
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

        val SCENARIO_GROUP = SimulationGroup
            .vars("scenario", "expected", "nullable")
            .with("nullable", "public var test: io.violabs.picard.dsl.TestCase? = null", true)
            .with("non-null", "public var test: io.violabs.picard.dsl.TestCase", false)
    }
}

private class TestCase(val scenario: String)