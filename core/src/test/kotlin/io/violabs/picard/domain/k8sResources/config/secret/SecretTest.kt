package io.violabs.picard.domain.k8sResources.config.secret


import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.domain.BinaryData
import io.violabs.picard.domain.TextData
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

class SecretTest : SuccessBuildSim<Secret, SecretDslBuilder>() {
    @Test
    fun `type ref check`() {
        assert(Secret.Type.OPAQUE.toString() == "Opaque")
    }

    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            SecretTest::class,
            SUCCESS_POSSIBILITIES
        )


        private val SUCCESS_POSSIBILITIES = possibilities<Secret, SecretDslBuilder> {
            scenario {
                id = "minimum"
                given(SecretDslBuilder())
                expected = Secret()
            }

            scenario {
                id = "full"
                given(SecretDslBuilder()) {
                    sharedMetadata()
                    data(PLACEHOLDER to PLACEHOLDER)
                    stringData(PLACEHOLDER to PLACEHOLDER)
                    immutable()
                    type = Secret.Type.OPAQUE
                }
                expected = Secret(
                    metadata = METADATA,
                    data = BinaryData(mutableMapOf(PLACEHOLDER to PLACEHOLDER)),
                    stringData = TextData(mutableMapOf(PLACEHOLDER to PLACEHOLDER)),
                    immutable = true,
                    type = Secret.Type.OPAQUE
                )
            }
        }
    }
}