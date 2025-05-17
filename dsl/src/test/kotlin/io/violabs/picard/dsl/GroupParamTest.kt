package io.violabs.picard.dsl

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.LIST
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.TypeName
import com.squareup.kotlinpoet.asTypeName
import io.violabs.geordi.UnitSim
import io.violabs.picard.dsl.param.GroupParam
import org.junit.jupiter.api.Test

class GroupParamTest : UnitSim() {
    val propTypeName = LIST.parameterizedBy(TestObj::class.asTypeName() as TypeName).copy(nullable = true)
    val groupBuilderName = ClassName("test", "TestObjBuilder.Group")

    @Test
    fun `toPropertySpec - happy path`() = test {
        given {
            val param = GroupParam("test", propTypeName, groupBuilderName)

            expect {
                "private var test: kotlin.collections.List<io.violabs.picard.dsl.GroupParamTest.TestObj>? = null"
            }

            whenever {
                val propSpec = param.toPropertySpec()

                propSpec.toString().trimIndent()
            }
        }
    }

    @Test
    fun `accessors - happy path`() = test {
        given {
            val param = GroupParam("test", propTypeName, groupBuilderName)

            expect {
                """
                    |public fun test(block: test.TestObjBuilder.Group.() -> kotlin.Unit) {
                    |  this.test = test.TestObjBuilder.Group().apply(block).items()
                    |}
                """.trimMargin()
            }

            whenever { param.accessors().first().toString().trimIndent() }
        }
    }

    class TestObj
}