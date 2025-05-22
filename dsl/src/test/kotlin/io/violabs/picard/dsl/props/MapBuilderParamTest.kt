package io.violabs.picard.dsl.props

import com.squareup.kotlinpoet.*
import io.violabs.geordi.SimulationGroup
import io.violabs.geordi.UnitSim
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestTemplate

class MapBuilderParamTest : UnitSim() {

    @TestTemplate
    fun `toPropertySpec - happy path - #scenario`(
        expected: String,
        keyType: TypeName, valueType: TypeName,
        nullable: Boolean
    ) = test {
        given {
            val param = MapGroupProp("test", keyType, valueType, nullable)

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
            val param = MapGroupProp(
                "test",
                STRING, TestObj::class.asTypeName() as TypeName,
                false
            )

            expect {
                """
                    |public fun test(block: io.violabs.picard.dsl.props.TestObjDSLBuilder.MapGroup<kotlin.String>.() -> kotlin.Unit) {
                    |  this.test = io.violabs.picard.dsl.props.TestObjDSLBuilder.MapGroup<kotlin.String>().apply(block).items().toMap()
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
            MapBuilderParamTest::class,
            SCENARIO_GROUP to { this::`toPropertySpec - happy path - #scenario` }
        )

        val SCENARIO_GROUP = SimulationGroup
            .vars("scenario", "expected", "keyType", "valueType", "nullable")
            .with(
                "nullable",
                "private var test: kotlin.collections.Map<kotlin.String, io.violabs.picard.dsl.props.MapBuilderParamTest.TestObj>? = null",
                STRING,
                TestObj::class.asTypeName() as TypeName,
                true
            )
            .with(
                "non-null",
                "private var test: kotlin.collections.Map<kotlin.Int, io.violabs.picard.dsl.props.MapBuilderParamTest.TestObj>",
                INT,
                TestObj::class.asTypeName() as TypeName,
                false
            )
    }

    class TestObj
    class TestObjBuilder
}