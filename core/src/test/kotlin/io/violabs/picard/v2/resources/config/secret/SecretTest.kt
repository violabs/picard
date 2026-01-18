package io.violabs.picard.v2.resources.config.secret


import io.violabs.picard.Common
import io.violabs.picard.Common.sharedObjectMeta
import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.domain.BinaryData
import io.violabs.picard.domain.TextData
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

class SecretTest : SuccessBuildSim<Secret, SecretDslBuilder>() {
    @Test
    fun `type ref check`() {
        assert(SecretType.OPAQUE.toString() == "Opaque")
    }

    @Test
    fun `yaml serialization uses ref value for SecretType`() {
        val secret = Secret(type = SecretType.OPAQUE)
        val yaml = io.violabs.picard.YamlBuilder.singleBuild(secret)

        assert(yaml.contains("type: Opaque")) {
            "Expected 'type: Opaque' but got: $yaml"
        }
    }

    @Test
    fun `yaml serialization uses ref value for complex SecretType`() {
        val secret = Secret(type = SecretType.SERVICE_ACCOUNT_TOKEN)
        val yaml = io.violabs.picard.YamlBuilder.singleBuild(secret)

        assert(yaml.contains("type: kubernetes.io/service-account-token")) {
            "Expected 'type: kubernetes.io/service-account-token' but got: $yaml"
        }
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
                    metadata {
                        sharedObjectMeta()
                    }

                    data(PLACEHOLDER to PLACEHOLDER)
                    stringData(PLACEHOLDER to PLACEHOLDER)
                    immutable()
                    type = SecretType.OPAQUE
                }
                expected = Secret(
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