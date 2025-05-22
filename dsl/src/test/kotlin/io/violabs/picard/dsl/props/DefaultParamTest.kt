package io.violabs.picard.dsl.props

import com.squareup.kotlinpoet.asClassName
import com.squareup.kotlinpoet.asTypeName
import io.violabs.geordi.SimulationGroup
import io.violabs.geordi.UnitSim
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestTemplate

class DefaultParamTest : UnitSim() {
    val propTypeName = String::class.asTypeName()

    @TestTemplate
    fun `toPropertySpec - happy path - #scenario`(expected: String, nullableProp: Boolean) = test {
        given {
            val param = DefaultPropSchema("test", propTypeName, nullableProp = nullableProp)

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
            val param = DefaultPropSchema("test", propTypeName, true)

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


        private val testResponseClassName = String::class.asClassName()
        private val propertyString = "public var test: $testResponseClassName"
        val SCENARIO_GROUP = SimulationGroup
            .vars("scenario", "expected", "nullableProp")
            .with("nullable", "$propertyString? = null", true)
            .with("non-null", propertyString, false)
    }
}