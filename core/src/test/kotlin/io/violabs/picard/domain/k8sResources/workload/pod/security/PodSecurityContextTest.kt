package io.violabs.picard.domain.k8sResources.workload.pod.security


import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class PodSecurityContextTest : SuccessBuildSim<PodSecurityContext, PodSecurityContextDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            PodSecurityContextTest::class,
            SUCCESS_POSSIBILITIES
        )


        private val SUCCESS_POSSIBILITIES = possibilities<PodSecurityContext, PodSecurityContextDslBuilder> {
            scenario {
                id = "minimum"
                given(PodSecurityContextDslBuilder())
                expected = PodSecurityContext()
            }

            scenario {
                idForFalseBooleanValues()
                given(PodSecurityContextDslBuilder()) {
                    runAsNonRoot(false)
                }
                expected = PodSecurityContext(
                    runAsNonRoot = false
                )
            }
        }
    }
}