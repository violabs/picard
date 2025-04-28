package io.violabs.picard.domain.k8sResources.policy.subject


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ServiceAccountSubjectTest : FailureBuildSim<ServiceAccountSubject, ServiceAccountSubject.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ServiceAccountSubjectTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<ServiceAccountSubject, ServiceAccountSubject.Builder> {
            requireScenario("name") {
                given(ServiceAccountSubject.Builder())
            }

            requireScenario("namespace") {
                given(ServiceAccountSubject.Builder()) {
                    name = PLACEHOLDER
                }
            }
        }
    }
}