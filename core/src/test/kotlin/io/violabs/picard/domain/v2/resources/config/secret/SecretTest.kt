package io.violabs.picard.domain.v2.resources.config.secret


import io.violabs.picard.Common
import io.violabs.picard.Common.sharedObjectMeta
import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.domain.BinaryData
import io.violabs.picard.domain.TextData
import io.violabs.picard.domain.k8sResources.config.secret.Secret
import io.violabs.picard.possibilities
import io.violabs.picard.v2.resources.config.secret.SecretType
import io.violabs.picard.v2.resources.config.secret.SecretV2
import io.violabs.picard.v2.resources.config.secret.SecretV2DslBuilder
import io.violabs.picard.v2.resources.config.secret.data
import io.violabs.picard.v2.resources.config.secret.stringData
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

class SecretTest : SuccessBuildSim<SecretV2, SecretV2DslBuilder>() {
    @Test
    fun `type ref check`() {
        assert(SecretType.OPAQUE.toString() == "Opaque")
    }

    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            SecretTest::class,
            SUCCESS_POSSIBILITIES
        )


        private val SUCCESS_POSSIBILITIES = possibilities<SecretV2, SecretV2DslBuilder> {
            scenario {
                id = "minimum"
                given(SecretV2DslBuilder())
                expected = SecretV2()
            }

            scenario {
                id = "full"
                given(SecretV2DslBuilder()) {
                    metadata {
                        sharedObjectMeta()
                    }

                    data(PLACEHOLDER to PLACEHOLDER)
                    stringData(PLACEHOLDER to PLACEHOLDER)
                    immutable()
                    type = SecretType.OPAQUE
                }
                expected = SecretV2(
                    metadata = Common.OBJECT_META,
                    data = BinaryData(mutableMapOf(PLACEHOLDER to PLACEHOLDER)),
                    stringData = TextData(mutableMapOf(PLACEHOLDER to PLACEHOLDER)),
                    immutable = true,
                    type = SecretType.OPAQUE
                )
            }
        }
    }
}