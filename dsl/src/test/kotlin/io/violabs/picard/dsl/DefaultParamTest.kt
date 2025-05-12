package io.violabs.picard.dsl

import com.squareup.kotlinpoet.asTypeName
import io.violabs.geordi.SimulationGroup
import io.violabs.geordi.UnitSim
import io.violabs.picard.dsl.param.DefaultParam
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestTemplate

class DefaultParamTest : UnitSim() {
    val propTypeName = String::class.asTypeName()

    @TestTemplate
    fun `toPropertySpec - happy path - #scenario`(expected: String, nullable: Boolean) = test {
        given {
            val param = DefaultParam("test", propTypeName, nullable)

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
            val param = DefaultParam("test", propTypeName, true)

            expect { true }

            whenever { param.accessors().isEmpty() }
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
            .with("nullable", "public var test: kotlin.String? = null", true)
            .with("non-null", "public var test: kotlin.String", false)
    }
}