package io.violabs.picard.dsl.params

import com.squareup.kotlinpoet.BOOLEAN
import com.squareup.kotlinpoet.INT
import com.squareup.kotlinpoet.STRING
import com.squareup.kotlinpoet.TypeName
import io.violabs.geordi.SimulationGroup
import io.violabs.geordi.UnitSim
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestTemplate

class MapParamTest : UnitSim() {

    @TestTemplate
    fun `toPropertySpec - happy path - #scenario`(
        expected: String,
        keyType: TypeName, valueType: TypeName,
        nullable: Boolean
    ) = test {
        given {
            val param = MapParam("test", keyType, valueType, nullable)

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
            val param = MapParam("test", STRING, INT, nullableAssignment = true)

            expect {
                """
                    |public fun test(vararg items: kotlin.Pair<kotlin.String, kotlin.Int>) {
                    |  this.test = items.toMap()
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
            MapParamTest::class,
            SCENARIO_GROUP to { this::`toPropertySpec - happy path - #scenario` }
        )

        val SCENARIO_GROUP = SimulationGroup
            .vars("scenario", "expected", "keyType", "valueType", "nullable")
            .with(
                "nullable",
                "private var test: kotlin.collections.Map<kotlin.String, kotlin.Int>? = null",
                STRING,
                INT,
                true
            )
            .with(
                "non-null",
                "private var test: kotlin.collections.Map<kotlin.Int, kotlin.Boolean>",
                INT,
                BOOLEAN,
                false
            )
    }
}