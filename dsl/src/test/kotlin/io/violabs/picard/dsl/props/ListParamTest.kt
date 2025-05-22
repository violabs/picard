package io.violabs.picard.dsl.props

import com.squareup.kotlinpoet.INT
import com.squareup.kotlinpoet.STRING
import com.squareup.kotlinpoet.TypeName
import io.violabs.geordi.SimulationGroup
import io.violabs.geordi.UnitSim
import io.violabs.picard.metaDsl.schema.ListPropSchema
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestTemplate

class ListParamTest : UnitSim() {

    @TestTemplate
    fun `toPropertySpec - happy path - #scenario`(expected: String, collectionTypeName: TypeName, nullable: Boolean) =
        test {
            given {
                val param = ListPropSchema("test", collectionTypeName, nullable)

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
            val param = ListPropSchema("test", nullableAssignment = true)

            expect {
                """
                    |public fun test(vararg items: kotlin.String) {
                    |  this.test = items.toList()
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
            ListParamTest::class,
            SCENARIO_GROUP to { this::`toPropertySpec - happy path - #scenario` }
        )

        val SCENARIO_GROUP = SimulationGroup
            .vars("scenario", "expected", "typeName", "nullable")
            .with(
                "nullable",
                "private var test: kotlin.collections.List<kotlin.String>? = null",
                STRING,
                true
            )
            .with(
                "non-null",
                "private var test: kotlin.collections.List<kotlin.Int>",
                INT,
                false
            )
    }
}