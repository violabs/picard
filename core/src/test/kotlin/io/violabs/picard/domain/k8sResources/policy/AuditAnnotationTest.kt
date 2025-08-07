package io.violabs.picard.domain.k8sResources.policy


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class AuditAnnotationTest : FailureBuildSim<AuditAnnotation, AuditAnnotationDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            AuditAnnotationTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<AuditAnnotation, AuditAnnotationDslBuilder> {
            requireScenario("key") {
                given(AuditAnnotationDslBuilder())
            }

            requireScenario("valueExpression") {
                given(AuditAnnotationDslBuilder()) {
                    key = PLACEHOLDER
                }
            }
        }
    }
}