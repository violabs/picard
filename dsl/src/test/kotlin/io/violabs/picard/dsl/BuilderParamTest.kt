package io.violabs.picard.dsl

import com.squareup.kotlinpoet.asClassName
import com.squareup.kotlinpoet.asTypeName
import io.violabs.geordi.SimulationGroup
import io.violabs.geordi.UnitSim
import io.violabs.picard.dsl.param.BuilderParam
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestTemplate

class BuilderParamTest : UnitSim() {
    val typeName = TestResponse::class.asTypeName()
    val buildClassName = TestBuilder::class.asClassName()

    @TestTemplate
    fun `toPropertySpec - happy path - #scenario`(expected: String, nullable: Boolean) = test {
        given {
            val param = BuilderParam("test", typeName, buildClassName, nullable)

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
            val param = BuilderParam("test", typeName, buildClassName, true)

            expect {
                """
                    |public fun test(block: io.violabs.picard.dsl.BuilderParamTest.TestBuilder.() -> kotlin.Unit) {
                    |  val builder = io.violabs.picard.dsl.BuilderParamTest.TestBuilder()
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

        val SCENARIO_GROUP = SimulationGroup
            .vars("scenario", "expected", "nullable")
            .with("nullable", "private var test: io.violabs.picard.dsl.BuilderParamTest.TestResponse? = null", true)
            .with("non-null", "private var test: io.violabs.picard.dsl.BuilderParamTest.TestResponse", false)
    }

    class TestResponse
    class TestBuilder
}