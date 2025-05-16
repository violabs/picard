package io.violabs.picard.dsl.process

import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSDeclaration
import com.google.devtools.ksp.symbol.KSName
import com.google.devtools.ksp.symbol.KSPropertyDeclaration
import com.google.devtools.ksp.symbol.KSReferenceElement
import com.google.devtools.ksp.symbol.KSType
import com.google.devtools.ksp.symbol.KSTypeReference
import io.violabs.geordi.UnitSim
import org.junit.jupiter.api.Test

class ParameterFactoryTest : UnitSim() {
    val parameterFactory = DefaultParameterFactory()

    val mockDeclaration = mock<KSPropertyDeclaration>()
    val singleEntryTransform = mock<KSClassDeclaration>()
    val declaration = mock<KSDeclaration>()
    val logger = mock<KSPLogger>()
    val name = mock<KSName>()
    val typeReference = mock<KSTypeReference>()
    val type = mock<KSType>()
    val refElement = mock<KSReferenceElement>()

    @Test
    fun `determineParam will create a group`() = test {
        given {
            expect { "test" }

            setupMocks {
                every { name.asString() } returns "test"
                every { mockDeclaration.simpleName } returns name
                every { type.toString() } returns "String"
                every { type.arguments } returns emptyList()
                every { type.isError } returns false
                every { type.declaration } returns declaration
                every { typeReference.resolve() } returns type
                every { typeReference.element } returns refElement
                every { mockDeclaration.type } returns typeReference
            }

            whenever {
                parameterFactory.determineParam(
                    mockDeclaration,
                    null,
                    logger
                )
            }
        }
    }


}