package io.violabs.picard.v2.resources.policy.schema.subject

import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ServiceAccountSubjectTest : FailureBuildSim<ServiceAccountSubject, ServiceAccountSubjectDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ServiceAccountSubjectTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<ServiceAccountSubject, ServiceAccountSubjectDslBuilder> {
            requireScenario("name") {
                given(ServiceAccountSubjectDslBuilder())
            }

            requireScenario("namespace") {
                given(ServiceAccountSubjectDslBuilder()) {
                    name = PLACEHOLDER
                }
            }
        }
    }
}