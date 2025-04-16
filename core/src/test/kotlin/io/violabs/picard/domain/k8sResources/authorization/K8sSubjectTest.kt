package io.violabs.picard.domain.k8sResources.authorization


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class K8sSubjectTest : FailureBuildSim<K8sSubject, K8sSubject.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            K8sSubjectTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<K8sSubject, K8sSubject.Builder> {
            requireScenario("kind") {
                given(K8sSubject.Builder())
            }

            requireScenario("name") {
                given(K8sSubject.Builder()) {
                    kind = PLACEHOLDER
                }
            }
        }
    }
}