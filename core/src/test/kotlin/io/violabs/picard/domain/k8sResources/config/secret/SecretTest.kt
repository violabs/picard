package io.violabs.picard.domain.k8sResources.config.secret


import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.domain.BinaryData
import io.violabs.picard.domain.TextData
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

class SecretTest : SuccessBuildSim<Secret, Secret.Builder>() {
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


        private val SUCCESS_POSSIBILITIES = possibilities<Secret, Secret.Builder> {
            scenario {
                id = "minimum"
                given(Secret.Builder())
                expected = Secret()
            }

            scenario {
                id = "full"
                given(Secret.Builder()) {
                    sharedMetadata()
                    data(PLACEHOLDER to BYTES)
                    stringData(PLACEHOLDER to PLACEHOLDER)
                    immutable()
                    type = Secret.Type.OPAQUE
                }
                expected = Secret(
                    metadata = METADATA,
                    data = BinaryData(mutableMapOf(PLACEHOLDER to BYTES)),
                    stringData = TextData(mutableMapOf(PLACEHOLDER to PLACEHOLDER)),
                    immutable = true,
                    type = Secret.Type.OPAQUE
                )
            }
        }
    }
}