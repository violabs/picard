package io.violabs.picard.dsl

import io.violabs.geordi.SimulationGroup
import io.violabs.geordi.UnitSim
import io.violabs.picard.dsl.param.BooleanParam
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestTemplate

class BooleanParamTest : UnitSim() {

    @TestTemplate
    fun `toPropertySpec - happy path - #scenario`(expected: String, nullable: Boolean) = test {
        given {
            val param = BooleanParam("test", nullable)

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
            val param = BooleanParam("test", true)

            expect {
                """
                    |public fun test(on: kotlin.Boolean = true) {
                    |  this.test = on
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
            BooleanParamTest::class,
            SCENARIO_GROUP to { this::`toPropertySpec - happy path - #scenario` }
        )

        val SCENARIO_GROUP = SimulationGroup
            .vars("scenario", "expected", "nullable")
            .with("nullable", "private var test: kotlin.Boolean? = null", true)
            .with("non-null", "private var test: kotlin.Boolean", false)
    }
}