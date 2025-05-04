package io.violabs.picard.domain.manifest


import io.violabs.picard.FullBuildSim
import io.violabs.picard.domain.k8sResources.authentication.tokenRequest.TokenRequest
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ManifestTest : FullBuildSim<Manifest, Manifest.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ManifestTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<Manifest, Manifest.Builder> {
            scenario {
                id = "full"
                given(Manifest.Builder()) {
                    authentication {
                        tokenRequest { }
                    }
                }
                expected = Manifest(
                    resources = listOf(
                        AuthenticationResourceSection(
                            resources = listOf(TokenRequest())
                        )
                    )
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<Manifest, Manifest.Builder> {
            requireNotEmptyScenario("resources") {
                given(Manifest.Builder())
            }
        }
    }
}