package io.violabs.picard.domain.k8sResources.policy


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class AuditAnnotationTest : FailureBuildSim<AuditAnnotation, AuditAnnotation.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            AuditAnnotationTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<AuditAnnotation, AuditAnnotation.Builder> {
            requireScenario("key") {
                given(AuditAnnotation.Builder())
            }

            requireScenario("valueExpression") {
                given(AuditAnnotation.Builder()) {
                    key = PLACEHOLDER
                }
            }
        }
    }
}